package spider;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaiDuProcessorUtils {
	/*获得页面的html代码*/
public static String getPageHtml(WebDriver webDriver){
	WebElement webElement = webDriver.findElement(By.xpath("/html"));
	String content = webElement.getAttribute("outerHTML");
	return content;
}
    /*点击下一页*/
public static void clickNextPage(ChromeDriver webDriver){
	List<WebElement>eles = webDriver.findElements(By.xpath("//a[@class='page-next mou-evt']"));
	webDriver.executeScript("arguments[0].click();",eles.get(0)); 
}
/*输入框输入页码*/
public static void clickNextPage(ChromeDriver webDriver,int pageIndex){
	List<WebElement>eles = webDriver.findElements(By.xpath("//input[@class='page-input']"));
	webDriver.executeScript("arguments[0].focus();arguments[0].value=15",eles.get(0)); 
}
    /*获得总页数*/
public static Long getAllPage(ChromeDriver webDriver){
	try {
		WebElement ele = webDriver.findElement(By.xpath("//b[@class='page-all']"));
		//System.out.println("the total Pages is:"+ele.getText());
		return Long.parseLong(ele.getText());
	} catch (Exception e) {
		// TODO: handle exception
	}
	return 1L;
}
/*得到所有页面的html*/
public static List<String> getAllPageHtml(ChromeDriver webDriver,int nextPageTimes) throws Exception{
	List<String>list=new ArrayList<String>();
	Long totalPage=getAllPage(webDriver);
	int index=1;
	do{
		String content=getPageHtml(webDriver);
		list.add(content);
		if(index<totalPage){
			//如果更换ip 进入循环
			//只有状态为 nextPage 才下一页
			//PAGEING
			WebDriverPool.blockFetch(webDriver);
			clickNextPage(webDriver);
			WebDriverPool.setFetchIndex(webDriver);
			//afetrPage
			try {
				Thread.sleep(nextPageTimes);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		index++;
	}while(index<=totalPage);
	return list;
}
}
