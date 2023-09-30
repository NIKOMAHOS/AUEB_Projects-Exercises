#pragma once
#include "app.h"
#include "graphics.h"
#include <iostream>
#include "config.h"

App::App() {

}

void App::update()
{
	if (this->getAppState() == STATE_INIT) {
		return;
	}

	if (this->getAppState() == STATE_LOADING) {
		this->init();
		this->setAppState(STATE_IDLE);
		return;
	}

	if (this->getAppState() == STATE_IDLE) {
		if (docker && docker->getFocused()) {
			gallery->setFocused(false);
			docker->update();
		}
		else {
			if (gallery) {
				gallery->setFocused(true);
				this->gallery->update();
			}
			if (docker) {
				docker->update();
			}
		}
	}
}

void App::draw()
{
	graphics::Brush br;

	if (this->getAppState() == STATE_INIT) {
		SETCOLOR(br.fill_color, 1.0f, 1.0f, 1.0f);
		graphics::setFont(std::string(ASSET_PATH) + "Winter Animal.ttf");
		graphics::drawText(CANVAS_WIDTH / 2 - 120.0f, CANVAS_HEIGHT / 2, 30.0f, "LOADING ASSETS...", br);
		graphics::drawText(CANVAS_WIDTH / 2 - 150.0f, CANVAS_HEIGHT / 2 + 50.0f, 30.0f, "THIS MAY TAKE A MOMENT", br);
		this->setAppState(STATE_LOADING);
		return;
	}

	if (this->getAppState() == STATE_IDLE) {
		br.texture = std::string(ASSET_PATH) + std::string("background.png");
		br.outline_opacity = 0.0f;

		// Set the font
		graphics::setFont(std::string(ASSET_PATH) + "Winter Animal.ttf");

		// Draw background
		graphics::drawRect(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, CANVAS_WIDTH, CANVAS_WIDTH, br);

		// Draw the Gallery
		if (gallery)
		{
			graphics::drawText(20, 380, 20, "Title: ", br);
			graphics::drawText(20, 380 + 20, 20, "Director: ", br);
			graphics::drawText(20, 380 + 40, 20, "Stars: ", br);
			graphics::drawText(20, 380 + 60, 20, "Year: ", br);
			graphics::drawText(20, 380 + 80, 20, "Genre: ", br);
			graphics::drawText(20, 380 + 110, 20, "Summary: ", br);

			gallery->draw();
		}

		// Draw the Docker
		if (docker)
		{
			if (this->getAppState() == STATE_IDLE && docker->filter_button->pressed) {
				docker->slider->upSliderPosX = 300.0f;
				docker->slider->downSliderPosX = 535.0f;
				docker->filter_button->pressed = false;
			}
			docker->draw();
		}
	}
}

void App::init()
{
	// Load the images
	graphics::preloadBitmaps(std::string(ASSET_PATH));

	// Create The Widgets
	gallery = new Gallery();
	docker = new Docker();

	for (Button* button : docker->buttons) {
		button->films = gallery->films;
		button->active_film = gallery->active_film;
	}

	// Play Sound
	graphics::playMusic(std::string(ASSET_PATH) + "music.mp3", 0.1f, true);
}

App::~App()
{
	if (gallery) {
		delete gallery;
	}
	if (docker) {
		delete docker;
	}

	std::cout << "APP destructs !" << std::endl;
}

