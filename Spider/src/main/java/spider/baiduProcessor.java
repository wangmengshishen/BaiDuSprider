package spider;

import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.selenium.baiduDownloader;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class baiduProcessor implements PageProcessor {
	private Site site;

	@Override
	public Site getSite() {
		if (site == null) {
			site = Site
					.me()
					.setDomain("baidu.com")
					.addStartUrl(
							"https://pan.baidu.com/share/home?uk=877701619#category/type=0")
					.setSleepTime(1000);
		}
		return site;
	}

	@Override
	public void process(Page page) {
		/*  page.addTargetRequest("https://pan.baidu.com/share/home?uk=389858048#category/type=0");*/
		String requestUrl=page.getUrl().all().get(0);
		List<String> list = page.getHtml() 
				.xpath("//a[@class='file-handler b-no-ln dir-handler']").all();
		System.out.println(list.size());
		for (String str : list) {
			String title = getTitle(str);
			String url = getURL(str);
			System.out.println("title:" + title + ",url:" + url);
		}

	}
	private String getTitle(String str) {
		int start=str.indexOf("title=")+7;
		int end=str.indexOf("\"", start);
		return str.substring(start,end);
	}
	private String getURL(String str) {
		int start=str.indexOf("href=")+6;
		int end=str.indexOf("\"", start);
		return str.substring(start,end);
	}

	public static void main(String[] args) {
		Spider.create(new baiduProcessor())
				.thread(1)
				.pipeline(new FilePipeline("D://webmagic"))
				.downloader(
						new baiduDownloader("E://selenium//chromedriver.exe"))
				.run();
	}

}
