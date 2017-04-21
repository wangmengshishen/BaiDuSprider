package us.codecraft.webmagic.downloader.selenium;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.PlainText;
import us.codecraft.webmagic.utils.UrlUtils;

public class baiduDownloader implements Downloader, Closeable {
	private ChromeDriver webDriver;
	private int sleepTime = 2000;
	private List<String>pages=new ArrayList<String>();
	public baiduDownloader(String chromeDriverPath) {
		System.out.println("new......");
		System.getProperties().setProperty("webdriver.chrome.driver",
				chromeDriverPath);
		webDriver = new ChromeDriver();
	}

	@Override
	public void close() throws IOException {
		System.out.println("close......");
		webDriver.close();
		
	}

	@Override
	public Page download(Request request, Task task) {
		System.out.println("download......");
		int currPage=getCurrentPage(request.getUrl());
		String content="";
		if(-1==currPage){
			//第一次加载
			webDriver.get(request.getUrl());
			try {//加载 sleep 2000s
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//pages=BaiDuProcessorUtils.getAllPageHtml(webDriver,6500);
			content=pages.get(0);
		}else{
			//从list取
			if(pages.size()>1){
				if(currPage<pages.size()){
					content=pages.get(currPage);
				}
			}
		}
		WebDriver.Options manage = webDriver.manage();
		Site site = task.getSite();
		if (site.getCookies() != null) {
			for (Map.Entry<String, String> cookieEntry : site.getCookies()
					.entrySet()) {
				Cookie cookie = new Cookie(cookieEntry.getKey(),
						cookieEntry.getValue());
				manage.addCookie(cookie);
			}
		}
		Page page = new Page();
		page.setRawText(content);
		page.setHtml(new Html(UrlUtils.fixAllRelativeHrefs(content,
				request.getUrl())));
		page.setUrl(new PlainText(request.getUrl()));
		page.setRequest(request);
		return page;
	}

	@Override
	public void setThread(int arg0) {
		// TODO Auto-generated method stub
		
	}
	//https://pan.baidu.com/share/home?uk=389858048#category/type=0&&isPage=19
	private  int getCurrentPage(String urlStr){
		String strs[]=urlStr.split("&&");
		if(strs.length>2){
			String page=urlStr.split("&&")[1].substring(6);
			return Integer.parseInt(page);
		}else{
			return -1;
		}
	}
	public static void main(String[] args) {
		Long i=-1L;
		System.out.println(-1L==i);
	}
}
