#pragma once
#include "Widget.h"
#include "config.h"

class Button : public Widget
{
public:
	//BUTTON INFO
	float buttonWidth = 0;
	float buttonHeight = 0;
	float buttonTextWidth = 0;
	float buttonTextHeight = 0;
	std::string buttonText = "";
	float buttonTextSize = 0;

	// Button COLOR
	float color_r = 0.0f;
	float color_g = 0.0f;
	float color_b = 0.0f;

	// Button Text Color
	float text_color_r = 0.0f;
	float text_color_g = 0.0f;
	float text_color_b = 0.0f;

	bool highlighted = false; // The button is highlighted
	bool pressed = false; // The button was just pressed

public:
	Button();

	~Button();

	void draw() override;
	virtual void update() = 0;

	void setPosX(float x) {
		this->pos_x = x;
		this->setEdgeRight(x);
		this->setEdgeLeft(x);
	}

	float getPosX() const { return this->pos_x; }

	void setPosY(float y) {
		this->pos_y = y;
		this->setEdgeTop(y);
		this->setEdgeBottom(y);
	}

	float getPosY() const { return this->pos_y; }

	void setButtonText(std::string s) {
		this->buttonText = s;
	}

	std::string getButtonText() const {
		return this->buttonText;
	}

	void setButtonTextWidth(float w) {
		this->buttonTextWidth = w;
	}

	float getButtonTextWidth() const {
		return this->buttonTextWidth;
	}

	void setButtonTextHeight(float h) {
		this->buttonTextHeight = h;
	}

	float getButtonTextHeight() const {
		return this->buttonTextHeight;
	}

	float getButtonTextSize() const {
		return this->buttonTextSize;
	}

protected:

	void setEdgeRight(float x) {
		this->edge_right = x + this->buttonWidth / 2;

	}
	void setEdgeLeft(float x) {
		this->edge_left = x - this->buttonWidth / 2;

	}
	void setEdgeTop(float y) {
		this->edge_top = y - this->buttonHeight / 2;

	}
	void setEdgeBottom(float y) {
		this->edge_bottom = y + this->buttonHeight / 2;

	}

};

class GenreButton : public Button
{
public:

	GenreButton();

	~GenreButton();

	void update() override;

	void filterByGenre(std::string genre_name);

};

class FilterButton : public Button
{

public:

	FilterButton();

	~FilterButton();

	void update() override;

	void clearFilters();

};

class SearchButton : public Button
{
public:
	std::string search_text = "";

public:

	SearchButton();

	~SearchButton();

	void update() override;
	void draw() override;

	void searchByText(std::string search_text);

	std::string getSearchText() { return this->search_text; }
	void setSearchText(std::string s) { this->search_text = s; }
};

class Slider : public Button
{
public:
	int min_year = 1972;
	int max_year = 2019;

	//Up Slider Info
	float upSliderPosX = 0.0f;
	float upSliderPosY = 0.0f;
	bool upSliderHighlighted = false;
	int upSliderValue = 0;

	//Down Slider Info
	float downSliderPosX = 0.0f;
	float downSliderPosY = 0.0f;
	bool downSliderHighlighted = false;
	int downSliderValue = 0;

public:

	Slider();

	~Slider();

	void draw() override;
	void update() override;

	void filterByYear();

	bool containsUP(float x, float y);
	bool containsDOWN(float x, float y);

	void setPosX(float x) {
		Button::setPosX(x);
		this->setUpSliderPosX(x);
		this->setDownSliderPosX(x + 235.0f);
	}

	void setPosY(float y) {
		Button::setPosY(y);
		this->setUpSliderPosY(y);
		this->setDownSliderPosY(y + 50.0f);
	}

private:

	// Methods of Up Slider
	void setUpSliderPosX(float ux) { this->upSliderPosX = ux; }
	float getUpSliderPosX() const { return this->upSliderPosX; }

	void setUpSliderPosY(float uy) { this->upSliderPosY = uy; }
	float getUpSliderPosY() const { return this->upSliderPosY; }

	void setUpSliderYear(int year) { this->upSliderValue = year; }
	int getUpSliderYear() const { return this->upSliderValue; }

	// Methods of Down Slider
	void setDownSliderPosX(float dx) { this->downSliderPosX = dx; }
	float getDownSliderPosX() const { return this->downSliderPosX; }

	void setDownSliderPosY(float dy) { this->downSliderPosY = dy; }
	float getDownSliderPosY() const { return this->downSliderPosY; }

	void setDownSliderYear(int year) { this->downSliderValue = year; }
	int getDownSliderYear() const { return this->downSliderValue; }

};