#pragma once
#include "Gallery.h"

void Gallery::update() {
	// Check if the mouse is on the area of the widget
	graphics::MouseState ms;
	graphics::getMouseState(ms);

	float start_mx = 0.0f;
	float start_my = 0.0f;
	start_mx = graphics::windowToCanvasX(ms.prev_pos_x);
	start_my = graphics::windowToCanvasY(ms.prev_pos_y);

	float end_mx = 0.0f;
	float end_my = 0.0f;
	if (ms.button_left_released) { // The dragging movement has concluded
		end_mx = graphics::windowToCanvasX(ms.cur_pos_x);
		end_my = graphics::windowToCanvasY(ms.cur_pos_y);
	}

	//Local Variables
	Film* toBe_rightmost_film = nullptr;
	Film* toBe_right_film = nullptr;
	Film* toBe_leftmost_film = nullptr;
	Film* toBe_left_film = nullptr;
	Film* toBe_non_visible_film = nullptr;

	if (ms.dragging && this->contains(end_mx, end_my) && this->getFocused()) {

		if (this->displacementSign(start_mx, end_mx)) // The movies change to the right ->
		{
			// Get the now rightmost movie - Soon to be non-visible
			toBe_non_visible_film = films.Next(films.GetIndex(films.Next(films.GetIndex(active_film))));
			// If it's visible put it to the back
			if (toBe_non_visible_film->isFront()) {
				toBe_non_visible_film->setBack();
			}

			// Get the now right movie - Soon to be rightmost
			toBe_rightmost_film = films.Next(films.GetIndex(active_film));
			toBe_rightmost_film->setFront();
			toBe_rightmost_film->setPosX(pos_x_right2);
			toBe_rightmost_film->setPosY(pos_y_right2);


			// Get the now leftmost movie - Soon to be left of the active movie
			toBe_left_film = films.Prev(films.GetIndex(films.Prev(films.GetIndex(active_film))));
			toBe_left_film->setFront();
			toBe_left_film->setPosX(pos_x_left1);
			toBe_left_film->setPosY(pos_y_left1);

			// Update the coordinates of the soon to be previously active movie - Soon to be to the right of the active movie
			toBe_right_film = active_film;
			toBe_right_film->setFront();
			toBe_right_film->setPosX(pos_x_right1);
			toBe_right_film->setPosY(pos_y_right1);


			// Update the active movie and set it to the middle - Previous Left _ Now Middle
			active_film = films.Prev(films.GetIndex(active_film));
			active_film->setFront();
			active_film->setPosX(pos_x_active);
			active_film->setPosY(pos_y_active);

			// Update the coordinates of the previously leftmost non-visible movie - Soon to be the leftmost movie (now visible)
			toBe_leftmost_film = films.Prev(films.GetIndex(films.Prev(films.GetIndex(active_film))));
			// If it's not visible, put it to the front
			if (toBe_leftmost_film->isBack()) {
				toBe_leftmost_film->setFront();
			}
			//Update it's coordinates
			toBe_leftmost_film->setPosX(pos_x_left2);
			toBe_leftmost_film->setPosY(pos_y_left2);
		}
		else  // The movies change to the left <-
		{
			// Get the now leftmost movie - Soon to be non-visible
			toBe_non_visible_film = films.Prev(films.GetIndex(films.Prev(films.GetIndex(active_film))));
			// If it's visible put it to the back
			if (toBe_non_visible_film->isFront()) {
				toBe_non_visible_film->setBack();
			}

			// Get the now left movie - Soon to be leftmost
			toBe_leftmost_film = films.Prev(films.GetIndex(active_film));
			toBe_leftmost_film->setFront();
			toBe_leftmost_film->setPosX(pos_x_left2);
			toBe_leftmost_film->setPosY(pos_y_left2);


			// Get the now rightmost movie - Soon to be right of the active movie
			toBe_right_film = films.Next(films.GetIndex(films.Next(films.GetIndex(active_film))));
			toBe_right_film->setFront();
			toBe_right_film->setPosX(pos_x_right1);
			toBe_right_film->setPosY(pos_y_right1);


			// Update the coordinates of the soon to be previously active movie - Soon to be to the left of the active movie
			toBe_left_film = active_film;
			toBe_left_film->setFront();
			toBe_left_film->setPosX(pos_x_left1);
			toBe_left_film->setPosY(pos_y_left1);


			// Update the active movie and set it to the middle - Previous right _ Now Middle
			active_film = films.Next(films.GetIndex(active_film));
			active_film->setFront();
			active_film->setPosX(pos_x_active);
			active_film->setPosY(pos_y_active);


			// Update the coordinates of the previously rightmost non-visible movie - Soon to be the rightmost movie (now visible)
			toBe_rightmost_film = films.Next(films.GetIndex(films.Next(films.GetIndex(active_film))));
			// If it's not visible, put it to the front
			if (toBe_rightmost_film->isBack()) {
				toBe_rightmost_film->setFront();
			}
			//Update it's coordinates
			toBe_rightmost_film->setPosX(pos_x_right2);
			toBe_rightmost_film->setPosY(pos_y_right2);
		}

	}

}

void Gallery::draw()
{
	graphics::Brush br1;
	graphics::Brush br2;
	float width = 150;
	float height = 250;

	for (Film* film : films) {
		float h = 0.5f + 0.5f * sinf(graphics::getGlobalTime() / 50.0f);
		if (film->getYearFiltered() == true && film->getGenreFiltered() == true && film->getTextFiltered() == true && film->isFront()) {
			if (film == active_film)
			{
				// Draw the reflection of the active movie
				br1.outline_opacity = 0.0f;
				br1.outline_width = 0.0f;
				br1.texture = std::string(ASSET_PATH) + std::string(film->getImgPath());
				br1.fill_opacity = 0.3f;
				graphics::setOrientation(-180.0f);
				graphics::drawRect(film->getPosX(), film->getPosY() + 150, width + 50, height - 20, br1);
				graphics::resetPose();

				// "Active" Film - Print the film's info as well
				br1.fill_opacity = 1.0f;
				br1.outline_opacity = 0.7f * h;
				br1.outline_width = 4.0f;
				graphics::drawRect(film->getPosX(), film->getPosY(), width + 50, height + 50, br1);

				graphics::setFont(std::string(ASSET_PATH) + "EuropeanTypewriter.ttf");
				graphics::drawText(100, 380, 18, film->getTitle(), br2);
				graphics::drawText(100, 380 + 20, 18, film->getDirector(), br2);
				graphics::drawText(100, 380 + 40, 18, film->getStars(), br2);
				graphics::drawText(100, 380 + 60, 18, std::to_string(film->getYear()), br2);
				graphics::drawText(100, 380 + 80, 18, film->getGenres(), br2);
				if (film->getSummary().size() >= 95) {
					size_t pos = film->getSummary().find_first_of(' ', 95);
					std::string summary_a = (film->getSummary().substr(0, pos));
					std::string summary_b = (film->getSummary().substr(pos + 1));
					graphics::drawText(100, 380 + 100, 18, summary_a, br2);
					graphics::drawText(100, 380 + 116, 18, summary_b, br2);
				}
			}
			else {
				// Draw the reflection
				br1.outline_opacity = 0.0f;
				br1.outline_width = 0.0f;
				br1.texture = std::string(ASSET_PATH) + std::string(film->getImgPath());
				br1.fill_opacity = 0.3f;
				graphics::setOrientation(-180.0f);
				graphics::drawRect(film->getPosX(), film->getPosY() + 150, width, height - 40, br1);
				graphics::resetPose();

				//All Other Films that are in the front - Print just their Posters
				br1.fill_opacity = 1.0f;
				graphics::drawRect(film->getPosX(), film->getPosY(), width, height, br1);

			}
		}
	}
}

Gallery::Gallery() : Widget() {
	this->setFocused(true);
	this->edge_right = CANVAS_WIDTH / 2 + 330 + 75;
	this->edge_left = CANVAS_WIDTH / 5 - 30 - 75;
	this->edge_top = CANVAS_HEIGHT - 330 - 150;
	this->edge_bottom = CANVAS_HEIGHT - 330 + 150;

	films.PushBack(new Film("The Godfather", { "Drama", "Crime" }, "Francis Ford Coppola", { "Marlon Brando", "Al Pacino", "James Caan" }, "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.", 1972, "Godfather.png", pos_x_active, pos_y_active, 1));
	films.PushBack(new Film("The Lord of the Rings: The Return of the King", { "Fantasy", "Adventure", "Action", "Drama" }, "Peter Jackson", { "Elijah Wood", "Ian McKellen", "Viggo Mortensen", "Orlando Bloom" }, "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.", 2003, "LOTR_3.png", pos_x_right1, pos_y_right1, 1));
	films.PushBack(new Film("Inception", { "Action", "Sci-Fi" }, "Christopher Nolan", { "Leonardo DiCaprio", "Joseph Gordon - Levitt", "Ellen Page" }, "A thief who steals corporate secrets through use of dream - sharing technology is given the inverse task of planting an idea into the mind of a CEO.", 2010, "INCEPTION.png", pos_x_right2, pos_y_right2, 1));
	films.PushBack(new Film("The Matrix", { "Action", "Sci-Fi" }, "Lana Wachowski", { "Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss" }, "A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.", 1999, "MATRIX.png", 100.0f, 50.0f, 0));
	films.PushBack(new Film("The Lord of the Rings: The Fellowship of the Ring", { "Fantasy", "Adventure", "Action", "Drama" }, "Peter Jackson", { "Elijah Wood", "Ian McKellen", "Viggo Mortensen", "Orlando Bloom" }, "A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron.", 2001, "LOTR_1.png", 100.0f, 50.0f, 0));
	films.PushBack(new Film("The Lord of the Rings: The Two Towers", { "Fantasy", "Adventure", "Action", "Drama" }, "Peter Jackson", { "Elijah Wood", "Ian McKellen", "Viggo Mortensen", "Orlando Bloom" }, "While Frodo and Sam edge closer to Mordor with the help of the shifty Gollum, the divided fellowship makes a stand against Sauron's new ally, Saruman, and his hordes of Isengard.", 2002, "LOTR_2.png", 100.0f, 50.0f, 0));
	films.PushBack(new Film("WALL-E", { "Animation", "Family", "Sci-Fi" }, "Andrew Stanton", { "Ben Burtt", "Elissa Knight", "Jeff Garlin" }, "In the distant future, a small waste-collecting robot inadvertently embarks on a space journey that will ultimately decide the fate of mankind.", 2008, "WALL-E.png", 900.0f, 50.0f, 0));
	films.PushBack(new Film("Joker", { "Crime", "Drama", "Thriller" }, "Todd Phillips", { "Joaquin Phoenix", "Robert De Niro", "Zazie Beetz" }, "A mentally troubled stand-up comedian embarks on a downward spiral that leads to the creation of an iconic villain.", 2019, "JOKER.png", 900.0f, 50.0f, 0));
	films.PushBack(new Film("The Dark Knight", { "Adventure", "Action", "Superhero" }, "Christopher Nolan", { "Christian Bale", "Heath Ledger", "Aaron Eckhart" }, "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.", 2008, "BATMAN.png", pos_x_left2, pos_y_left2, 1));
	films.PushBack(new Film("The Shawshank Redemption", { "Drama", "Crime" }, "Frank Darabont", { "Tim Robbins", "Morgan Freeman", "Bob Gunton", "Andy Dufresne" }, "A wrongfully convicted man forms a friendship with a fellow prisoner and finds redemption through acts of common decency.", 1994, "The Shawshank Redemption.png", pos_x_left1, pos_y_left1, 1));

	active_film = films.Front(); /// The starting active film is "The Godfather"

};

// Returns true if the mouse dragging happens to the right and false if it happens to the left
bool Gallery::displacementSign(float x1, float x2) {
	float displacement = (x2 - x1); // x2 is the last mouse position and x1 is the starting mouse position of the dragging motion
	if (displacement > 0) {
		return true;
	}
	else {
		return false;
	}
}

Gallery::~Gallery() {
	for (Film* film : films) {
		delete film;
	}
	std::cout << "Gallery destructs !" << std::endl;
}