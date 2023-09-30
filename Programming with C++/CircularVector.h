#pragma once
#include "Film.h"
#include <vector>
#include <algorithm>

class CircularVector {
public:
	// Inserts an element at the back of the list
	void PushBack(Film* value) {
		vector_.push_back(value);
	}

	// Removes the last element from the list
	void PopBack() {
		vector_.pop_back();
	}

	// Inserts an element at the front of the list
	void PushFront(Film* value) {
		vector_.insert(vector_.begin(), value);
	}

	// Removes the first element from the list
	void PopFront() {
		vector_.erase(vector_.begin());
	}

	// Returns a reference to the first element in the list
	Film* Front() {
		return vector_.front();
	}

	// Returns a reference to the last element in the list
	Film* Back() {
		return vector_.back();
	}

	// Returns an iterator to the first element in the list
	std::vector<Film*>::iterator begin() {
		return vector_.begin();
	}

	// Returns an iterator to the element following the last element in the list
	std::vector<Film*>::iterator end() {
		return vector_.end();
	}

	// Returns the number of elements in the list
	std::size_t Size() const {
		return vector_.size();
	}

	// Returns true if the list is empty, false otherwise
	bool Empty() const {
		return vector_.empty();
	}

	// Rotates the list forward by one element
	void RotateForward() {
		vector_.insert(vector_.begin(), vector_.back());
		vector_.pop_back();
	}

	// Rotates the list backward by one element
	void RotateBackward() {
		vector_.push_back(vector_.front());
		vector_.erase(vector_.begin());
	}

	// Returns the index of the given element in the list, or -1 if the element is not found
	int GetIndex(Film* element) {
		auto it = std::find(vector_.begin(), vector_.end(), element);
		if (it == vector_.end()) return -1;
		return std::distance(vector_.begin(), it);
	}

	// Returns the element after the given element in the list
	Film* Next(int index) {
		return vector_[(index + 1) % vector_.size()];
	}

	// Returns the element before the given element in the list
	Film* Prev(int index) {
		return vector_[(index - 1 + vector_.size()) % vector_.size()];
	}

private:
	std::vector<Film*> vector_;
};

