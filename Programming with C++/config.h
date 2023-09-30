#pragma once
#include <chrono>
#include <thread>
//#include <stdlib.h>

inline void sleep(int ms) {
	std::this_thread::sleep_for(std::chrono::milliseconds(ms));
}

#define ASSET_PATH "assets\\"
#define WINDOW_WIDTH 1440.0f
#define WINDOW_HEIGHT 720.0f
#define CANVAS_WIDTH  1000.0f
#define CANVAS_HEIGHT 500.0f

#define SETCOLOR(color, r, g, b) {color[0] = r; color[1] = g; color[2] = b;}

