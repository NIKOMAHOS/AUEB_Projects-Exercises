#pragma once
#include "Widget.h"
#include "config.h"

class Gallery : public Widget
{

public:
	Gallery();

	~Gallery();

	void draw() override;

	void update() override;

	float getEdgeRight() {
		return edge_right;
	}

	float getEdgeLeft() {
		return edge_left;
	}

	float getEdgeTop() {
		return edge_top;
	}

	float getEdgeBottom() {
		return edge_bottom;
	}

	bool displacementSign(float x1, float x2);

};