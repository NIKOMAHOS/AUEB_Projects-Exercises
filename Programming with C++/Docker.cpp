#pragma once
#include "Docker.h"
#include "config.h"

Docker::Docker() :Widget()
{
	this->setFocused(false);

	this->edge_right = CANVAS_WIDTH / 2 + CANVAS_WIDTH / 4;
	this->edge_left = CANVAS_WIDTH / 2 - CANVAS_WIDTH / 4;
	this->edge_top = 0.0f;
	this->edge_bottom = 11.0f;

	this->_isOpen = false;

	genre_button1 = new GenreButton();
	genre_button1->setPosX(300.0f);
	genre_button1->setPosY(45.0f);
	genre_button1->setButtonText("Adventure");
	genre_button1->setButtonTextWidth(277.0f);
	genre_button1->setButtonTextHeight(48.0f);
	buttons.push_back(genre_button1);

	genre_button2 = new GenreButton();
	genre_button2->setPosX(355.0f);
	genre_button2->setPosY(45.0f);
	genre_button2->setButtonText("Drama");
	genre_button2->setButtonTextWidth(334.0f);
	genre_button2->setButtonTextHeight(48.0f);
	buttons.push_back(genre_button2);

	genre_button3 = new GenreButton();
	genre_button3->setPosX(410.0f);
	genre_button3->setPosY(45.0f);
	genre_button3->setButtonText("Crime");
	genre_button3->setButtonTextWidth(390.0f);
	genre_button3->setButtonTextHeight(48.0f);
	buttons.push_back(genre_button3);

	genre_button4 = new GenreButton();
	genre_button4->setPosX(300.0f);
	genre_button4->setPosY(65.0f);
	genre_button4->setButtonText("Superhero");
	genre_button4->setButtonTextWidth(277.0f);
	genre_button4->setButtonTextHeight(68.0f);
	buttons.push_back(genre_button4);

	genre_button5 = new GenreButton();
	genre_button5->setPosX(355.0f);
	genre_button5->setPosY(65.0f);
	genre_button5->setButtonText("Action");
	genre_button5->setButtonTextWidth(334.0f);
	genre_button5->setButtonTextHeight(68.0f);
	buttons.push_back(genre_button5);

	genre_button6 = new GenreButton();
	genre_button6->setPosX(410.0f);
	genre_button6->setPosY(65.0f);
	genre_button6->setButtonText("Sci-Fi");
	genre_button6->setButtonTextWidth(390.0f);
	genre_button6->setButtonTextHeight(68.0f);
	buttons.push_back(genre_button6);

	genre_button7 = new GenreButton();
	genre_button7->setPosX(300.0f);
	genre_button7->setPosY(85.0f);
	genre_button7->setButtonText("Thriller");
	genre_button7->setButtonTextWidth(277.0f);
	genre_button7->setButtonTextHeight(88.0f);
	buttons.push_back(genre_button7);

	genre_button8 = new GenreButton();
	genre_button8->setPosX(355.0f);
	genre_button8->setPosY(85.0f);
	genre_button8->setButtonText("Fantasy");
	genre_button8->setButtonTextWidth(334.0f);
	genre_button8->setButtonTextHeight(88.0f);
	buttons.push_back(genre_button8);

	genre_button9 = new GenreButton();
	genre_button9->setPosX(410.0f);
	genre_button9->setPosY(85.0f);
	genre_button9->setButtonText("Family");
	genre_button9->setButtonTextWidth(390.0f);
	genre_button9->setButtonTextHeight(88.0f);
	buttons.push_back(genre_button9);

	filter_button = new FilterButton();
	filter_button->setPosX(670.0f);
	filter_button->setPosY(30.0f);
	filter_button->setButtonText("Clear Filters");
	filter_button->setButtonTextWidth(630.0f);
	filter_button->setButtonTextHeight(35.0f);
	buttons.push_back(filter_button);

	search_button1 = new SearchButton();
	search_button1->setPosX(675.0f);
	search_button1->setPosY(148.0f);
	search_button1->setButtonText("Title");
	buttons.push_back(search_button1);

	search_button2 = new SearchButton();
	search_button2->setPosX(675.0f);
	search_button2->setPosY(178.0f);
	search_button2->setButtonText("Director");
	buttons.push_back(search_button2);

	search_button3 = new SearchButton();
	search_button3->setPosX(675.0f);
	search_button3->setPosY(208.0f);
	search_button3->setButtonText("Star");
	buttons.push_back(search_button3);

	slider = new Slider();
	slider->setPosX(300.0f);
	slider->setPosY(150.0f);
	buttons.push_back(slider);

}

Docker::~Docker()
{
	for (auto button : buttons)
	{
		delete button;
	}
	buttons.clear();
}

void Docker::update()
{
	graphics::MouseState ms;
	graphics::getMouseState(ms);
	// Change the mouse state from checking window to checking canvas
	float mx = graphics::windowToCanvasX(ms.cur_pos_x);
	float my = graphics::windowToCanvasY(ms.cur_pos_y);

	if (!isOpen()) {
		if (this->contains(mx, my)) {
			setOpen(true);
			this->setFocused(true);
		}
	}
	else {
		for (auto button : buttons) {
			button->update();
		}

		if (filter_button->pressed) { // The Clear Filters button was pressed
			slider->upSliderValue = slider->min_year;
			slider->downSliderValue = slider->max_year;
			search_button1->setSearchText("");
			search_button2->setSearchText("");
			search_button3->setSearchText("");
		}

		if (!new_contains(mx, my)) {
			setOpen(false);
			this->setFocused(false);
		}

	}

}
void Docker::draw()
{
	graphics::Brush br;

	// Draw docker background
	br.texture = std::string(ASSET_PATH) + "dock.png";
	br.outline_opacity = 0.0f;

	if (isOpen()) {

		graphics::drawRect(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 4, CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, br); // Open Docker

		// Docker text color and font
		SETCOLOR(br.fill_color, 1.0f, 1.0f, 1.0f);
		graphics::setFont(std::string(ASSET_PATH) + "EuropeanTypewriter.ttf");

		graphics::drawText(270, 25, 16.0f, "Filter by Genre: ", br);

		graphics::drawText(270, 120, 16.0f, "Filter by Year:", br);

		graphics::drawText(260, 155, 14.0f, "From: ", br);
		graphics::drawText(290, 172, 13.0f, "1972", br);
		graphics::drawText(520, 172, 13.0f, "2019", br);

		graphics::drawText(270, 205, 14.0f, "To: ", br);
		graphics::drawText(290, 222, 13.0f, "1972", br);
		graphics::drawText(520, 222, 13.0f, "2019", br);

		graphics::drawText(540, 120, 18.0f, "Filter by: ", br);
		graphics::drawText(568, 152, 14.0f, "Title :", br);
		graphics::drawText(550, 182, 14.0f, "Director :", br);
		graphics::drawText(573, 212, 14.0f, "Star :", br);

		// Draw the buttons
		for (auto button : buttons) {
			button->draw();
		}

	}

	else {
		graphics::drawRect(CANVAS_WIDTH / 2, -110, CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, br); // Closed Docker
		graphics::drawText(CANVAS_WIDTH / 2 + 12, 12, 13.0f, " Filter Movies ", br);
	}

}

bool Docker::new_contains(float x, float y)
{
	if (this->edge_left < x && this->edge_right > x && this->edge_top < y && this->new_edge_bottom > y) {
		return true;
	}
	else {
		return false;
	}
}
