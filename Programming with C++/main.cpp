#include "graphics.h"
#include "app.h"
#include "config.h"
#include "Film.h"

void update(float ms) {
	// Update the app state
	App* my_app = reinterpret_cast <App*> (graphics::getUserData());
	my_app->update();

}

void draw() {
	// Draw the app state
	App* my_app = reinterpret_cast <App*> (graphics::getUserData());
	my_app->draw();

}

int main() {

	// Draw the App Window
	graphics::createWindow(WINDOW_WIDTH, WINDOW_HEIGHT, "Film Browser");
	graphics::setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
	graphics::setCanvasScaleMode(graphics::CANVAS_SCALE_FIT);

	// Create the App
	App* app = new App();

	// Set Up graphics system
	graphics::setUserData(app);

	graphics::setUpdateFunction(update);
	graphics::setDrawFunction(draw);

	//my_app.init();

	graphics::startMessageLoop();
	graphics::destroyWindow();

	delete app;

	return 0;

}
