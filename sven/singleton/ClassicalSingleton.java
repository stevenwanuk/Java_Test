package sven.singleton;

public class ClassicalSingleton {

	private static final ClassicalSingleton instance = new ClassicalSingleton();
	
	public static ClassicalSingleton getInstance() {
		
		return instance;
	}
	
	private ClassicalSingleton() {
		
		//block reflection

		if (instance != null) {
			
			throw new RuntimeException("instance has existed");
		}

	}
	
	public void doSomething() {
		
		System.out.println("dosomething");
	}
	
	
	public static void main(String[] args) {
		
		ClassicalSingleton.getInstance().doSomething();
		
		
		new ClassicalSingleton();
	}
}
