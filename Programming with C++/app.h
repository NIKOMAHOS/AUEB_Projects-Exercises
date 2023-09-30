#pragma once
#include "Film.h"
#include "Gallery.h"
#include "Docker.h"

class App {

public:

	enum app_state_t { // APP STATE
		STATE_INIT,
		STATE_LOADING,
		STATE_IDLE,
		STATE_FILTERED
	};

protected:
	Gallery* gallery = nullptr;
	Docker* docker = nullptr;

	app_state_t a_state = STATE_INIT;


public:
	App();

	~App();

	// Setters & Getters

	void setAppState(app_state_t state) { this->a_state = state; }

	app_state_t getAppState() { return this->a_state; }

	void update();

	void draw();

	void init();

};
