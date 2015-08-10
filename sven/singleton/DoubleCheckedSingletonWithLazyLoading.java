package sven.singleton;

public class DoubleCheckedSingletonWithLazyLoading {

	private static DoubleCheckedSingletonWithLazyLoading instance = null;
	
	public static DoubleCheckedSingletonWithLazyLoading getInstance() {
		
		if (instance == null) {
			
			synchronized(DoubleCheckedSingletonWithLazyLoading.class) {
				
				if (instance == null) {
					
					instance = new DoubleCheckedSingletonWithLazyLoading();
				}
			}
			
		}
		
		return instance;
	}
	
	private DoubleCheckedSingletonWithLazyLoading() {
		
		//block reflection
		synchronized(DoubleCheckedSingletonWithLazyLoading.class) {
			
			if (instance != null) {
				
				throw new RuntimeException("instance has existed");
			}
		}
	}
	
	public void doSomething() {
		
		System.out.println("dosomething");
	}
	
	public static void main(String[] args) {
		
		DoubleCheckedSingletonWithLazyLoading.getInstance().doSomething();
		
		new DoubleCheckedSingletonWithLazyLoading();
	}
}
