package spider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverPool {
	private static final String driverKey="webdriver.chrome.driver";
	private static final String driverValue="E://selenium//chromedriver.exe";
	public static int poolCount;//目前与当前线一样
	private static ChromeDriver[] drivers;
	private static boolean[]driverStatus;
	private static boolean[]isWaitingChangeIP;
	private static int[]fetchCount;
	static{
		System.getProperties().setProperty(driverKey,driverValue);
	}
	public static int getPoolCount() {
		return poolCount;
	}
	public void setPoolCount(int poolCount) {
		this.poolCount = poolCount;
		if(poolCount>0){
			drivers=new ChromeDriver[poolCount];
			driverStatus=new boolean[poolCount];
			fetchCount=new int[poolCount];
			for(int i=0;i<poolCount;i++){
				driverStatus[i]=true;
			}
		}
	}
	/*获得fetch次数*/
	public static int getisfetchCount(int driverIndex){
		return fetchCount[driverIndex];
	}
	/*设置fetch次数*/
	public static void setisfetchCount(int driverIndex,int i){
		fetchCount[driverIndex]=i;
	}
	/*获得切换ip状态*/
	public static boolean getisWaitingChangeIP(int driverIndex){
		return isWaitingChangeIP[driverIndex];
	}
	/*设置切换ip状态*/
	public static void setisWaitingChangeIP(int driverIndex,boolean flag){
		isWaitingChangeIP[driverIndex]=flag;
	}
	/*获得webDriver状态*/
	public static boolean getDriverStatus(int driverIndex){
		return driverStatus[driverIndex];
	}
	/**/
	public static void setDriverStatus(int driverIndex,boolean flag){
		driverStatus[driverIndex]=flag;
	}
	/*获得当前driverIndex*/
	private static int getWerDriverIndex(WebDriver curDriver){
		for(int i=0;i<drivers.length;i++){
			if(curDriver==drivers[i]){
				return i;
			}
		}
		return -1;
	}
	public static ChromeDriver getWebDriver(){
		for(int i=0;i<drivers.length;i++){
			boolean driverStatus=getDriverStatus(i);//获得状态
			if(driverStatus){//可用
				System.out.println("正在分配driver......");
				setDriverStatus(i,false);//设置为不可用
				System.out.println("分配完毕......");
				if(drivers[i]==null){
					System.out.println("new .....");
					drivers[i]=new ChromeDriver();
				}
				return drivers[i];
			}
		}
		System.out.println("没有可用driver......");
		return null;
	}
	public static void relaseDriver(WebDriver driver){
		System.out.println("释放driver......");
		int index=getWerDriverIndex(driver);
		setDriverStatus(index,true);
		
	}
	public static void setFetchIndex(WebDriver driver){
		int index=getWerDriverIndex(driver);
		int i=getisfetchCount(index);
		setisfetchCount(index,i+1);
	}
	public static int getFetchIndex(WebDriver driver){
		int index=getWerDriverIndex(driver);
		return getisfetchCount(index);
	}
	public static void blockFetch(WebDriver driver) throws InterruptedException{
			while(true){
				int i=getFetchIndex(driver);
				if(i==ConnectNetWork.fetchCount){
					Thread.sleep(1000);
			}else{
				return;
			}
		}
	}
/*	public  ChromeDriver getWebDriver(){
		DesiredCapabilities cap=new DesiredCapabilities();
		setProxy(cap,"138.68.143.78:8818");
		ChromeDriver web=new ChromeDriver(cap);
		return web;
	}
	public static void setProxy(DesiredCapabilities cap,String proxyIpAndPort){
		Proxy proxy=new Proxy();
		proxy.setHttpProxy(proxyIpAndPort).setFtpProxy(proxyIpAndPort).setSslProxy(proxyIpAndPort);
		cap.setCapability(CapabilityType.ForSeleniumServer.AVOIDING_PROXY, true);
		cap.setCapability(CapabilityType.ForSeleniumServer.ONLY_PROXYING_SELENIUM_TRAFFIC, true);
		//System.setProperty("http.nonProxyHosts", "localhost");
		cap.setCapability(CapabilityType.PROXY, proxy);
	}*/
	
}
