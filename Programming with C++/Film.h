#pragma once
#include "config.h"
#include <vector>
#include <iostream>
#include <string>

class Film
{
public:
	std::string title;
	std::vector<std::string> genres; // list of the genres that apply to a movie
	std::string director;
	std::vector< std::string > stars; // list with the stars of a movie
	std::string summary;
	int year;
	std::string img_Path;
	float posx;
	float posy;
	int depth; // 0 -> back , 1 -> front
	bool genre_filtered; // true if it satisfies the genre filter, false otherwise
	bool year_filtered; // true if it satisfies the year filter, false otherwise
	bool title_filtered; // true if it satisfies the title filter, false otherwise
	bool director_filtered; // true if it satisfies the director filter, false otherwise
	bool stars_filtered; // true if it satisfies all of the stars filter, false otherwise

	Film() {
		this->title = "";
		this->genres = {};
		this->director = "";
		this->stars = {};
		this->summary = "";
		int year = 0;
		this->img_Path = "";
		this->posx = 0;
		this->posy = 0;
		this->depth = 0;
		this->genre_filtered = true; // by default, all movies are filtered
		this->year_filtered = true;  // by default, all movies are filtered
		this->title_filtered = true; // by default, all movies are filtered
		this->director_filtered = true; // by default, all movies are filtered
		this->stars_filtered = true; // by default, all movies are filtered
	};// default constructor

	Film(std::string t, std::vector<std::string> g, std::string dir, std::vector<std::string> s, std::string sum, int year, std::string img, float x, float y, int d) {
		this->title = t;
		this->genres = g;
		this->director = dir;
		this->stars = s;
		this->summary = sum;
		this->year = year;
		this->img_Path = img;
		this->posx = x;
		this->posy = y;
		this->depth = d;
		this->genre_filtered = true; // All films are "filtered" at first, cause there essentially are no filters selected
		this->year_filtered = true;  // All films are "filtered" at first, cause there essentially are no filters selected
		this->title_filtered = true; // All films are "filtered" at first, cause there essentially are no filters selected
		this->director_filtered = true; // All films are "filtered" at first, cause there essentially are no filters selected
		this->stars_filtered = true; // All films are "filtered" at first, cause there essentially are no filters selected
	};// overloaded constructor

	~Film() {
		std::cout << "MOVIE destructs !" << std::endl;
	};//destructor

	// Setters & Getters

	void setTitle(std::string) {
		this->title = title;
	};

	std::string getTitle() const {
		return this->title;
	};

	void setGenres(std::vector<std::string> g) {
		this->genres = g;
	};

	void setDirector(std::string d) {
		this->director = d;
	};

	std::string getDirector() const {
		return this->director;
	};

	void setStars(std::vector<std::string> s) {
		this->stars = s;
	};


	void setSummary(std::string sum) {
		this->summary = sum;
	};

	std::string getSummary() const {
		return this->summary;
	};

	void setYear(int y) {
		this->year = y;
	};

	int getYear() const {
		return this->year;
	};

	void setImgPath(std::string img) {
		this->img_Path = img;
	};

	std::string getImgPath() const {
		return this->img_Path;
	};

	void setFront() {
		this->depth = 1;
	}

	void setBack() {
		this->depth = 0;
	}

	bool isFront() const {
		return depth == 1;
	}

	bool isBack() const {
		return depth == 0;
	}

	void setPosX(float x) {
		this->posx = x;
	}

	float getPosX() const {
		return posx;
	}

	void setPosY(float y) {
		this->posy = y;
	}

	float getPosY() const {
		return posy;
	}

	std::string getStars() const {
		std::string local_stars = "";
		for (std::string star : stars) {
			local_stars += star + ", ";
		}
		local_stars.erase(local_stars.length() - 2); // Remove last comma and space
		return local_stars;
	}

	std::string getGenres() const {
		std::string local_genres = "";
		for (std::string genre : genres) {
			local_genres += genre + ", ";
		}
		local_genres.erase(local_genres.length() - 2); // Remove last comma and space
		return local_genres;
	}

	// Text Filtering
	bool getTextFiltered() const { // true if it satisfies all of the text filters, false otherwise
		return (this->title_filtered && this->director_filtered && this->stars_filtered);
	}

	void setTextFiltered(bool b) {
		this->title_filtered = b;
		this->director_filtered = b;
		this->stars_filtered = b;
	}

	bool getTitleFiltered() const {
		return this->title_filtered;
	}

	void setTitleFiltered(bool b) {
		this->title_filtered = b;
	}

	bool getDirectorFiltered() const {
		return this->director_filtered;
	}

	void setDirectorFiltered(bool b) {
		this->director_filtered = b;
	}

	bool getStarsFiltered() const {
		return this->stars_filtered;
	}

	void setStarsFiltered(bool b) {
		this->stars_filtered = b;
	}

	// Genre Filtering
	bool getGenreFiltered() const {
		return this->genre_filtered;
	}
	void setGenreFiltered(bool b) {
		this->genre_filtered = b;
	}

	//Year Filtering
	bool getYearFiltered() const {
		return this->year_filtered;
	}
	void setYearFiltered(bool b) {
		this->year_filtered = b;
	}

};

