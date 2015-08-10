package sven.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class SingletonWithSerializable implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final SingletonWithSerializable instance = new SingletonWithSerializable();
	
	public static SingletonWithSerializable getInstance() {
		
		return instance;
	}
	
	private SingletonWithSerializable() {
		
		//block reflection

		if (instance != null) {
			
			throw new RuntimeException("instance has existed");
		}

	}
	
	public void doSomething() {
		
		System.out.println("dosomething");
	}
	
	private Object readResolve() throws ObjectStreamException {
		
		throw new RuntimeException("block Serializable");
		
		/**
		 * or return instance
		 */
		
		//return instance;
	}
	
	
	public static void main(String[] args) {
		
		SingletonWithSerializable.getInstance().doSomething();
		
		try {
			
			new SingletonWithSerializable();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		//Write
		ObjectOutputStream os = null;
		try { 
			os = new ObjectOutputStream(new FileOutputStream("SingletonWithSerializable"));
			os.writeObject(SingletonWithSerializable.getInstance());
			os.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
			//TODO release....
		}
		
		//Read
		ObjectInputStream  is = null;
		try { 
			is = new ObjectInputStream (new FileInputStream("SingletonWithSerializable"));
			is.readObject();
			is.close();
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			
			//TODO release...
		}
		
		
	}
}
