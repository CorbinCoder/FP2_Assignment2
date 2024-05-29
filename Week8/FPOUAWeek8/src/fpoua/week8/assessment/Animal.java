package fpoua.week8.assessment;

import java.io.Serializable;

/*
 * ASSESSMENT WEEK 6 TASK 2
 * Animal should now be serializable
 */
public abstract class Animal implements Serializable{
	
	protected int id;
	protected String name;
	protected String colour;
	protected int age;

	/*
	 * Animal default constructor that has no parameter.
	 * Initialises id, name, colour, and age with default falues.
	 * write your code below
	 */
	public Animal() {
		this.id = 0;
		this.name = "new animal";
		colour = "";
		age = 0;
	}
	
	/*
	 * Animal secondary constructor that takes in name, colour and age
	 * @param name, colour, age
	 */
	public Animal(String name, String colour, int age) {
		this.name = name;
		this.colour = colour;
		this.age = age;
	}
	
	/*
	 * Animal tertiary constructor that takes in id, name, colour and age
	 * @param id, name, colour, age
	 * write your code bellow
	 */
	public Animal(int id, String name, String colour, int age) {
		this.id = id;
		this.name = name;
		this.colour = colour;
		this.age = age;
	}
	
	public abstract void makeSound();
	
	public void eat() {
		System.out.println(getName() + " is eating");
	}
	
	public void sleep() {
		System.out.println(getName() + " is sleeping");
	}
	
	public String getName() {
		return name;
	}
	
	public String getColour() {
		return colour;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Setter method for id
	 */ 
	public void setID(int id) {
		this.id = id;
	}
	/*
	 * Getter method for for id
	 */ 
	public int getID() {
		return id;
	}
	
	/*
	 * Quick and dirty string to check content of object.
	 */
	public String toString() {
		return id + " " + name + " " + colour + " " + age;
	}
}
