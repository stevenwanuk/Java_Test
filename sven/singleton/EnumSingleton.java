package sven.singleton;

public enum EnumSingleton {

	
	Instance;
	
	private void doSomething() {
	
		
		System.out.println("do something");
	}

	
	public static void main(String args[]) {
	
	
		EnumSingleton.Instance.doSomething();
	}
	
}
