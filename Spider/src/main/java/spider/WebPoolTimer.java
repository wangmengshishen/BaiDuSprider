package spider;

import java.util.Timer;

import org.openqa.selenium.chrome.ChromeDriver;

public class WebPoolTimer {
	public static boolean isRun = false;
	public static Timer timer = new Timer();
	private ChromeDriver webDriver = null;
	public ChromeDriver getWebDriver() {
		return webDriver;
	}
	public void setWebDriver(ChromeDriver webDriver) {
		this.webDriver = webDriver;
	}
	public void start() {
		if (!isRun) {
			System.out.println("timer start......");
			isRun = true;
			timer = new Timer();
			timer.schedule(new DcSpTimerTask(), 0, 1000);// 在1秒后执行此任务,每次间隔2秒,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
		}
	}
	class DcSpTimerTask extends java.util.TimerTask {
		@Override
		public void run() {
			setWebDriver(WebDriverPool.getWebDriver());
			System.out.println("aaaaaaaa");
			if (webDriver != null) {
				System.out.println("得到driver.....取消timmer");
				timer.cancel();
			}
			System.out.println("wait......");

		}
	}
}
