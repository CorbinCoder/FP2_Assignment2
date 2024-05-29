package fpoua.week8.assessment;

import java.io.Serializable;

/*
 * ASSESSMENT WEEK 6 TASK 2
 * Panda should now implement Serializable
 */
public class Panda extends Animal implements Serializable{
	public Panda() {
		super();
	}

	public Panda(String name, String colour, int age) {
		super(name, colour, age);
		// TODO Auto-generated constructor stub
	}

	public Panda(int id, String name, String colour, int age) {
		super(id, name, colour, age);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub

	}

}
