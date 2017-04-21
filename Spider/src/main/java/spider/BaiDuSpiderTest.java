package spider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaiDuSpiderTest {
	private static final int thread = 1;
	private static final int driverCount = 1;

	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(thread);
		WebDriverPool pool = new WebDriverPool();
		pool.setPoolCount(driverCount);
		taskChangeIP();
		for (int i = 0; i < thread; i++) {
			BaiDuSpiderRunnable task = new BaiDuSpiderRunnable();// 800000000-90000000
			// BaiDuSpiderRunnable task = new BaiDuSpiderRunnable(877701619L,
			// 877701680L);
			fixedThreadPool.execute(task);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void taskLog() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				for (int i = 0; i < driverCount; i++) {
					System.out.println(WebDriverPool.getDriverStatus(i));
				}
			}
		};
		Timer timer = new Timer();
		long delay = 2000;
		long intevalPeriod = 2 * 1000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	}

	public static void taskChangeIP() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				ConnectNetWork.changeIP();
			}
		};
		Timer timer = new Timer();
		long delay = 10000;
		long intevalPeriod = 2 * 1000;
		// schedules the task to be run in an interval
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
	}
}
