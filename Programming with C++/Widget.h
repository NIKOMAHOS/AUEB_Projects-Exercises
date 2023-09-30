#pragma once
#include "config.h"
#include "graphics.h"
#include "Film.h"
#include "CircularVector.h"
#include <list>

class Widget
{
public:
	bool focused;

	CircularVector films; // Η CircularVector δέχεται μόνο Film* items
	Film* active_film = nullptr;

protected:
	float pos_x = 0;
	float pos_y = 0;
	float edge_right = 0;
	float edge_left = 0;
	float edge_top = 0;
	float edge_bottom = 0;

	//Coordinates of the movie left of the middle one
	float pos_x_left1 = CANVAS_WIDTH / 3 - 10;
	float pos_y_left1 = CANVAS_HEIGHT - 325;

	//Coordinates of the leftmost movie
	float pos_x_left2 = CANVAS_WIDTH / 5 - 30;
	float pos_y_left2 = CANVAS_HEIGHT - 320;

	//Coordinates of the middle movie
	float pos_x_active = CANVAS_WIDTH / 2;
	float pos_y_active = CANVAS_HEIGHT - 330;

	//Coordinates of the movie right of the middle one
	float pos_x_right1 = CANVAS_WIDTH / 2 + 177.5;
	float pos_y_right1 = CANVAS_HEIGHT - 325;

	//Coordinates of the rightmost movie
	float pos_x_right2 = CANVAS_WIDTH / 2 + 330;
	float pos_y_right2 = CANVAS_HEIGHT - 320;

public:

	virtual void update() = 0;
	virtual void draw() = 0;

	bool contains(float x, float y) {
		if (this->edge_left < x && this->edge_right > x && this->edge_top < y && this->edge_bottom > y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	bool getFocused() { return this->focused; };
	void setFocused(bool b) { this->focused = b; }
};