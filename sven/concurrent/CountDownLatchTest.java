package sven.concurrent;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Programe with 
 * 
 * @author Sven Wan 
 *
 */

public class CountDownLatchTest {

	
	
	public void runPrograme() {
		
		final int MODULE_COUNT = 4;
		new Programe(MODULE_COUNT).start();;
		
	}
	
	
	public static void main(String[] args) {
		
		new CountDownLatchTest().runPrograme();
		
	}
	
	
	class Programe {
		
		private CountDownLatch latch;
		
		private List<Module> moduleList = new  ArrayList<Module>();
		
		public Programe(int moduleCount) {
			
			latch = new CountDownLatch(moduleCount);
			
			Random r = new Random();
			for(int i = 0; i < moduleCount ; i++) {
				
				StringBuffer moduleNameBuilder = new StringBuffer();
				moduleNameBuilder.append("module[").append(i).append("]");
								
				int time = r.nextInt(2000);
				moduleList.add(new Module(latch, moduleNameBuilder.toString(), time));
			}
		}

		public void start() {

			try {
				
				
				ExecutorService exec = Executors.newCachedThreadPool();  
				for(Module module : moduleList) {
					
					exec.execute(module);
				}
				
				//wait for all modules completion
				latch.await();
				System.out.println("programe done");
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
		}
		
		
	}
	
	class Module implements Runnable {

		private CountDownLatch latch;
		private final String moduleName;
		private int time;
		
		public Module(CountDownLatch latch, String moduleName, int time) {
			
			this.latch = latch;
			this.moduleName = moduleName;
			this.time = time;
		}
		
		@Override
		public void run() {
			
			try {
				
				doModule();
				latch.countDown();
				
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			
		}
		
		public void doModule() {
			
			try {
				TimeUnit.MILLISECONDS.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" complete " + moduleName + "  in " + time);
		}
	}
	
	
	
	
	
	
}
