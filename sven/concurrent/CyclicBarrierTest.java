package sven.concurrent;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTest {
	
	
	final static int MODULE_COUNT = 4;
	
	
	final static CyclicBarrier barrier = new CyclicBarrier(MODULE_COUNT, new Runnable() {

		@Override
		public void run() {
			System.out.println("start to release all thread...");
			
		}
		
		
	});
	
	public void runPrograme() {
		
		ExecutorService exec = Executors.newCachedThreadPool();  
		
		Random r = new Random();
		for (int i = 0; i < MODULE_COUNT; i++) {
			
			StringBuffer moduleNameBuilder = new StringBuffer();
			moduleNameBuilder.append("module[").append(i).append("]");
			
			int time = r.nextInt(2000);
			exec.execute(new Module(barrier, moduleNameBuilder.toString(), time));
		}		
	}
	
	

	public static void main(String[] args) {
		
		CyclicBarrierTest test = new CyclicBarrierTest();
		test.runPrograme();
		test.runPrograme();
		test.runPrograme();
		
	}
	
	
	class Module implements Runnable {

		private CyclicBarrier barrier;
		private final String moduleName;
		private int time;
		
		public Module(CyclicBarrier barrier, String moduleName, int time) {
			
			this.barrier = barrier;
			this.moduleName = moduleName;
			this.time = time;
		}
		
		@Override
		public void run() {
			
			try {
				
				doModule();
				
				barrier.await();
				
				System.out.println("release " + moduleName);
				
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
