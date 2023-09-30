#pragma once
#include "Button.h"

Button::Button() :Widget() {

}

Button::~Button() {

}

void Button::draw() {

	float h = 0.6 * this->highlighted; // if highlighted == false then h = 0.0f, else h = 0.6f

	graphics::Brush br;
	br.outline_opacity = 1.0f;
	SETCOLOR(br.outline_color, 1.0f, 1.0f, 1.0f);
	//Button Color - Genre Button - grayish , Filter Button - yellowish, Search Boxes - black
	SETCOLOR(br.fill_color, this->color_r + h, this->color_g + h, this->color_b + h);

	graphics::drawRect(this->getPosX(), this->getPosY(), this->buttonWidth, this->buttonHeight, br);

	graphics::Brush br1;
	//Text Color and font
	SETCOLOR(br1.fill_color, this->text_color_r, this->text_color_g, this->text_color_g);
	graphics::setFont(std::string(ASSET_PATH) + "EuropeanTypeWriter.ttf");

	if (dynamic_cast<SearchButton*>(this) == nullptr) { // If the button is not a SearchButton
		graphics::drawText(this->getButtonTextWidth(), this->getButtonTextHeight(), this->getButtonTextSize(), this->getButtonText(), br1);
	}
};

// GENRE BUTTON
GenreButton::GenreButton() : Button() {
	this->buttonWidth = 50;
	this->buttonHeight = 15;
	this->color_r = 0.4f;
	this->color_g = 0.4f;
	this->color_b = 0.4f;
	this->buttonTextSize = 10.0f;
}
GenreButton::~GenreButton() {

}

void GenreButton::update() {

	graphics::MouseState ms;
	graphics::getMouseState(ms);
	float mx = graphics::windowToCanvasX(ms.cur_pos_x);
	float my = graphics::windowToCanvasY(ms.cur_pos_y);

	if (this->contains(mx, my)) {
		this->highlighted = true;
		if (ms.button_left_pressed && this->highlighted) {
			graphics::playSound(std::string(ASSET_PATH) + std::string("button.wav"), 1.0f);
			this->filterByGenre(this->getButtonText());
		}
	}
	else {
		this->highlighted = false;
	}
}

void GenreButton::filterByGenre(std::string genre_name) {
	for (Film* film : films) {
		std::string genres = film->getGenres();
		if (genres.find(genre_name) != std::string::npos) {
			if (film->getYearFiltered() == false || film->getTextFiltered() == false) { // This film has already failed another filter
				film->setGenreFiltered(false);
			}
			else {
				film->setGenreFiltered(true);
			}
		}
		else {
			film->setGenreFiltered(false); // Failed this filter
		}
	}
	this->pressed = true;
}

// FILTER BUTTON
FilterButton::FilterButton() : Button() {
	this->buttonWidth = 100;
	this->buttonHeight = 25;
	this->color_r = 0.6f;
	this->color_g = 0.6f;
	this->color_b = 0.1f;
	this->buttonTextSize = 16.0f;
}
FilterButton::~FilterButton() {

}

void FilterButton::update() {
	graphics::MouseState ms;
	graphics::getMouseState(ms);
	float mx = graphics::windowToCanvasX(ms.cur_pos_x);
	float my = graphics::windowToCanvasY(ms.cur_pos_y);

	if (this->contains(mx, my)) {
		this->highlighted = true;
		if (ms.button_left_pressed && highlighted) {
			graphics::playSound(std::string(ASSET_PATH) + std::string("button.wav"), 1.0f);
			this->clearFilters();
		}
	}
	else {
		this->highlighted = false;
	}
}

void FilterButton::clearFilters() {
	for (Film* film : films) {
		film->setGenreFiltered(true);
		film->setYearFiltered(true);
		film->setTextFiltered(true);
	}// All Filters have been reset
	this->pressed = true;
}

//SLIDER
Slider::Slider() : Button() {
	this->buttonTextSize = 12.0f;
	this->setUpSliderYear(min_year);
	this->setDownSliderYear(max_year);
}

Slider::~Slider() {

}

void Slider::update() {
	graphics::MouseState ms;
	graphics::getMouseState(ms);
	float mx = graphics::windowToCanvasX(ms.cur_pos_x);
	float my = graphics::windowToCanvasY(ms.cur_pos_y);
	int year = 0;
	if (this->containsUP(mx, my)) { // Moving the upper slider
		this->upSliderHighlighted = true;
		if (ms.dragging && upSliderHighlighted) {
			graphics::playSound(std::string(ASSET_PATH) + std::string("button.wav"), 0.5f);
			if (mx >= 300.0f && mx <= 535.0f) {
				this->setUpSliderPosX(mx);
				year = (this->getUpSliderPosX() - 300.0f) / 4.895833333f; // 4.895833333 canvas units equal 1 year. 300.0f is the starting x coordinate of the slider
				this->setUpSliderYear(year + min_year); // min_year = 1972
				this->filterByYear();
			}
		}
	}
	else if (this->containsDOWN(mx, my)) { // Moving the lower slider
		this->downSliderHighlighted = true;
		if (ms.dragging && downSliderHighlighted) {
			graphics::playSound(std::string(ASSET_PATH) + std::string("button.wav"), 0.5f);
			if (mx >= 300.0f && mx <= 535.0f) {
				this->setDownSliderPosX(mx);
				year = (this->getDownSliderPosX() - 300.0f) / 4.895833333f; // 4.895833333 canvas units equal 1 year. 300.0f is the starting x coordinate of the slider
				this->setDownSliderYear(year + min_year); //min_year = 1972
				this->filterByYear();
			}
		}
	}
	else {
		this->upSliderHighlighted = false;
		this->downSliderHighlighted = false;
	}

}

void Slider::draw() {
	graphics::Brush br;
	//Draw the Up Slider
	SETCOLOR(br.fill_color, 1.0f, 1.0f, 1.0f);
	br.fill_opacity = 1.0f;
	br.outline_opacity = 1.0f;
	graphics::drawLine(300.0f, 150.0f, 535.0f, 150.0f, br);
	float uh = 0.3f * this->upSliderHighlighted; //up highlighted
	SETCOLOR(br.fill_color, 0.7f + uh, 0.7f + uh, 0.7f + uh);
	graphics::drawRect(this->getUpSliderPosX(), this->getUpSliderPosY(), 10.0f, 20.0f, br);
	graphics::drawText(this->getUpSliderPosX() - 10, this->getUpSliderPosY() - 15, 12, std::to_string(this->getUpSliderYear()), br);

	//Draw the Down Slider
	SETCOLOR(br.fill_color, 1.0f, 1.0f, 1.0f);
	br.fill_opacity = 1.0f;
	br.outline_opacity = 1.0f;
	graphics::drawLine(300.0f, 200.0f, 535.0f, 200.0f, br);
	float dh = 0.3f * this->downSliderHighlighted; // down highlighted
	SETCOLOR(br.fill_color, 0.7f + dh, 0.7f + dh, 0.7f + dh);
	graphics::drawRect(this->getDownSliderPosX(), this->getDownSliderPosY(), 10.0f, 20.0f, br);
	graphics::drawText(this->getDownSliderPosX() - 10, this->getDownSliderPosY() - 15, 12, std::to_string(this->getDownSliderYear()), br);

}

void Slider::filterByYear() {
	for (Film* film : films) {
		int year = film->getYear();
		if (this->getUpSliderYear() <= year && year <= this->getDownSliderYear()) {
			if (film->getGenreFiltered() == false || film->getTextFiltered() == false) { // This film has already failed another filter
				film->setYearFiltered(false);
			}
			else {
				film->setYearFiltered(true);
			}
		}
		else {
			film->setYearFiltered(false);
		}
	}
	this->pressed = true;
}

bool Slider::containsUP(float x, float y)
{
	if (x >= this->getUpSliderPosX() - 5.0f && x <= this->getUpSliderPosX() + 5.0f && y >= this->getUpSliderPosY() - 10.0f && y <= this->getUpSliderPosY() + 10.0f) {
		return true;
	}
	else {
		return false;
	}
}

bool Slider::containsDOWN(float x, float y)
{
	if (x >= this->getDownSliderPosX() - 5.0f && x <= this->getDownSliderPosX() + 5.0f && y >= this->getDownSliderPosY() - 10.0f && y <= this->getDownSliderPosY() + 10.0f) {
		return true;
	}
	else {
		return false;
	}
}

//SEARCH BUTTON
SearchButton::SearchButton() : Button() {
	this->buttonWidth = 140.0f;
	this->buttonHeight = 18.0f;
	this->color_r = 0.0f;
	this->color_g = 0.0f;
	this->color_b = 0.0f;
	this->text_color_r = 8.0f;
	this->text_color_g = 0.8f;
	this->text_color_b = 0.5f;
	this->buttonTextSize = 13.0f;
	this->search_text = "";

}
SearchButton::~SearchButton() {

}

void SearchButton::draw() {
	this->Button::draw();
	graphics::Brush brush;
	SETCOLOR(brush.fill_color, 1.0f, 1.0f, 1.0f);
	if (this->getSearchText().length() > 20) { // Stop displaying the added letters BUT keep searching with them
		graphics::drawText(this->getPosX() - 65.0f, this->getPosY() + 5.0f, this->getButtonTextSize(), this->getSearchText().substr(0, 20), brush);
	}
	else {
		graphics::drawText(this->getPosX() - 65.0f, this->getPosY() + 5.0f, this->getButtonTextSize(), this->getSearchText(), brush);
	}
}

void SearchButton::update()
{
	graphics::MouseState ms;
	graphics::getMouseState(ms);
	float mx = graphics::windowToCanvasX(ms.cur_pos_x);
	float my = graphics::windowToCanvasY(ms.cur_pos_y);
	char ascii = 0;
	bool caps = false;
	if (this->contains(mx, my)) {
		this->highlighted = true;
		if (this->highlighted) {
			if (graphics::getKeyState(graphics::SCANCODE_LSHIFT) || graphics::getKeyState(graphics::SCANCODE_RSHIFT)) { // Left or Right Shift Pressed
				caps = !caps;
			}
			if (graphics::getKeyState(graphics::SCANCODE_SPACE)) { // Space Pressed
				this->search_text += " ";
				sleep(150);
			}
			if (graphics::getKeyState(graphics::SCANCODE_BACKSPACE)) { // Backspace Pressed
				if (this->getSearchText().length() > 0) {
					this->search_text.pop_back();
					this->searchByText(this->getSearchText());
					sleep(150);
				}
			}
			for (int i = graphics::SCANCODE_A; i <= graphics::SCANCODE_Z; i++) {
				graphics::scancode_t code = static_cast<graphics::scancode_t>(i);
				if (graphics::getKeyState(code))
				{
					if (caps == true) { // Read and then Search and then Write with uppercase characters
						ascii = static_cast<char>(code + 61); // + 61 -> int to char (Uppercase) in ASCII (ASCII Code of A = 65)
						this->setSearchText(this->getSearchText() + ascii);
						this->searchByText(this->getSearchText());
						sleep(150);
					}
					else if (caps == false) { // Read and then Search and then Write with lowercase letters
						ascii = static_cast<char>(code + 93); // + 93 -> int to char (Lowercase) in ASCII (ASCII Code of a = 97)
						this->setSearchText(this->getSearchText() + ascii);
						this->searchByText(this->getSearchText());
						sleep(150);
					}
				}
			}
		}
	}
	else { this->highlighted = false; }
}

void SearchButton::searchByText(std::string search_text) {

	if (this->getButtonText() == "Title") { // For the Title filter
		if (std::all_of(search_text.begin(), search_text.end(), ::isspace)) { // If the specific filter is not selected , disable it
			for (Film* film : films) {
				film->setTitleFiltered(true);
			}
		}
		else {
			// Remove leading and trailing whitespaces from the search text
			size_t first = search_text.find_first_not_of(' ');
			size_t last = search_text.find_last_not_of(' ');
			search_text = search_text.substr(first, (last - first + 1));

			for (Film* film : films) {
				std::string title = film->getTitle();
				if (title.find(search_text) == 0) {
					if (film->getGenreFiltered() == false || film->getYearFiltered() == false || film->getTextFiltered() == false) { // This film has already failed another filter
						if (film->getGenreFiltered() && film->getYearFiltered() && film->getTextFiltered() == false && film->getDirectorFiltered() && film->getStarsFiltered()) { // For Backspace Filtering
							film->setTitleFiltered(true); // The only failed filter is the Title filter
						}
						else {
							film->setTitleFiltered(false);
						}
					}
					else {
						film->setTitleFiltered(true);
					}
				}
				else {
					film->setTitleFiltered(false);
				}
			}
		}
	}
	else if (this->getButtonText() == "Director") { // For the Director filter
		if (std::all_of(search_text.begin(), search_text.end(), ::isspace)) { // If the specific filter is not selected , disable it
			for (Film* film : films) {
				film->setDirectorFiltered(true);
			}
		}
		else {
			// Remove leading and trailing whitespaces from the search text
			size_t first = search_text.find_first_not_of(' ');
			size_t last = search_text.find_last_not_of(' ');
			search_text = search_text.substr(first, (last - first + 1));

			for (Film* film : films) {
				std::string director = film->getDirector();
				if (director.find(search_text) == 0) {
					if (film->getGenreFiltered() == false || film->getYearFiltered() == false || film->getTextFiltered() == false) { // This film has already failed another filter
						if (film->getGenreFiltered() && film->getYearFiltered() && film->getTextFiltered() == false && film->getTitleFiltered() && film->getStarsFiltered()) { // For Backspace Filtering
							film->setDirectorFiltered(true); // The only failed filter is the Director filter
						}
						else {
							film->setDirectorFiltered(false);
						}
					}
					else {
						film->setDirectorFiltered(true);
					}
				}
				else {
					film->setDirectorFiltered(false);
				}
			}
		}
	}
	else if (this->getButtonText() == "Star") { // For the Stars filter
		if (std::all_of(search_text.begin(), search_text.end(), ::isspace)) { // If the specific filter is not selected , disable it
			for (Film* film : films) {
				film->setStarsFiltered(true);
			}
		}
		else {
			// Remove leading and trailing whitespaces from the search text
			size_t first = search_text.find_first_not_of(' ');
			size_t last = search_text.find_last_not_of(' ');
			search_text = search_text.substr(first, (last - first + 1));

			for (Film* film : films) {
				std::string stars = film->getStars();
				stars.erase(std::remove(stars.begin(), stars.end(), ','), stars.end());
				if (stars.find(search_text) != std::string::npos) {
					if (film->getGenreFiltered() == false || film->getYearFiltered() == false || film->getTextFiltered() == false) { // This film has already failed another filter
						if (film->getGenreFiltered() && film->getYearFiltered() && film->getTextFiltered() == false && film->getTitleFiltered() && film->getDirectorFiltered()) { // For Backspace Filtering
							film->setStarsFiltered(true); // The only failed filter is the Stars filter
						}
						else {
							film->setStarsFiltered(false);
						}
					}
					else {
						film->setStarsFiltered(true);
					}
				}
				else {
					film->setStarsFiltered(false);
				}
			}
		}
	}
	this->pressed = true;
}
