package spider;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestSelenium {
	 @Test
	    public void testSelenium() throws Exception {
		 
		  System.getProperties().setProperty("webdriver.chrome.driver", "E://selenium//chromedriver.exe");
		  ChromeDriver webDriver = new ChromeDriver();
	        System.out.println("---初始化浏览器驱动---");
	        webDriver.get("https://pan.baidu.com/share/home?uk=877701619#category/type=0");
				Thread.sleep(3000);
				List<String>list=BaiDuProcessorUtils.getAllPageHtml(webDriver,7000);//访问时间不能太频繁否则无法获取数据
				List<FileVo>files=new ArrayList<FileVo>();
				if(list.size()>0){
					for(String content:list){
						List<Element>eles=JsoupUtils.getElementsByClassName(content, "file-handler");
						if(eles.size()>0){
							for(Element ele:eles){
								String url=ele.attr("href");
								String title=ele.attr("title");
								String shareTime=JsoupUtils.getShareTime(ele);
								FileVo file=new FileVo();
								file.setTitle(title);
								file.setUrl(url);
								file.setShareTime(shareTime);
								files.add(file);
							}	
						}else{
							System.out.println("can't find anything file!");
						}
						
					}	
				}else{
					System.out.println("fetch page content is empt!");
				}
	       // webDriver.close();
	        System.out.println("done....");
	 }
}
