package fpoua.week8.assessment;

public class Dog extends Animal{

	/**
	 * Constructor for Cat
	 */
	public Dog(String name, String colour, int age) {
		super(name, colour, age);
	}

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("woof");
	}

}
