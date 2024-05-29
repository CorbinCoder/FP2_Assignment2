/**
 * 
 */
package fpoua.week8.assessment;

import java.io.Serializable;

/*
 * ASSESSMENT WEEK 6 TASK 2
 * Fish should now implement Serializable in addition to CanSwim
 */
public class Fish extends Animal implements CanSwim, Serializable{

	public Fish() {
		super();
	}
	
	/**
	 * 3 args constructor
	 * @param name
	 * @param colour
	 * @param age
	 */
	public Fish (String name, String colour, int age) {
		super(name, colour, age);
	}
	
	/*
	 * 4 args constructor
	 * @param id
	 * @param name
	 * @param colour
	 * @param age
	 */
	public Fish (int id, String name, String colour, int age) {
		super(id, name, colour, age);
	}

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("Just keep swimming");
	}

	@Override
	public void swim() {
		// TODO Auto-generated method stub
		System.out.println("swimming");
	}

}
