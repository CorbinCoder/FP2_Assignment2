/**
 * 
 */
package fpoua.week8.assessment;

/**
 * @author dipto
 *
 */
public class Cat extends Animal{

	/**
	 * Constructor for Cat
	 */
	public Cat(String name, String colour, int age) {
		super(name, colour, age);
	}
	
	public Cat(int id, String name, String colour, int age) {
		super(id, name, colour, age);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeSound() {
		// TODO Auto-generated method stub
		System.out.println("miaw");
	}
}
