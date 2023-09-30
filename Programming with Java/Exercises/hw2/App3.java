/*
	Name: Nikos Mitsakis
	Student Number: 3210122
*/

class Shape {
	void draw() {
	}

	void erase() {
	}
}

class Circle extends Shape {
	void draw() {
		System.out.println("Circle.draw()");
	}

	void erase() {
		System.out.println("Circle.erase()");
	}
}

class Square extends Shape {
	void draw() {
		System.out.println("Square.draw()");
	}

	void erase() {
		System.out.println("Square.erase()");
	}
}

class Triangle extends Shape {
	void draw() {
		System.out.println("Triangle.draw()");
	}

	void erase() {
		System.out.println("Triangle.erase()");
	}
}

class App3 {
	static Shape randShape() {
		switch ((int) (Math.random() * 3)) {
			default:
			case 0:
				return new Circle();
			case 1:
				return new Square();
			case 2:
				return new Triangle();
		}
	}

	public static void main(String[] args) {

		Shape[] shapes = new Shape[10];

		for (int i = 0; i < shapes.length; i++) {
			shapes[i] = randShape();
		}

		for (int count = 0; count < shapes.length; count++) {
			shapes[count].draw();
		}

	}
}
