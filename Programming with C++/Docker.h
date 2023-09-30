#pragma once
#include "Widget.h"
#include "Button.h"

class Docker : public Widget
{
public:

	float new_edge_bottom = 250.0f;
	bool _isOpen;

	std::list<Button*> buttons;

	GenreButton* genre_button1 = nullptr;
	GenreButton* genre_button2 = nullptr;
	GenreButton* genre_button3 = nullptr;
	GenreButton* genre_button4 = nullptr;
	GenreButton* genre_button5 = nullptr;
	GenreButton* genre_button6 = nullptr;
	GenreButton* genre_button7 = nullptr;
	GenreButton* genre_button8 = nullptr;
	GenreButton* genre_button9 = nullptr;

	FilterButton* filter_button = nullptr;

	SearchButton* search_button1 = nullptr;
	SearchButton* search_button2 = nullptr;
	SearchButton* search_button3 = nullptr;

	Slider* slider = nullptr;

public:

	Docker();
	~Docker();

	void draw() override;
	void update() override;

	bool new_contains(float x, float y);

	bool isOpen() const { return _isOpen; }
	void setOpen(bool bOpen) { _isOpen = bOpen; }

};
