package spider;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.jsoup.nodes.Element;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaiDuSpider {
	public static Long startUK=800000000L;
	private String baseUrl = "https://pan.baidu.com/share/home?uk=${uk}#category/type=0";// https://pan.baidu.com/share/home?uk=877701619#category/type=0
	public static Long endUK=900000000L;
	private ChromeDriver webDriver;
	public ChromeDriver getWebDriver() {
		return webDriver;
	}
	public void setWebDriver(ChromeDriver webDriver) {
		this.webDriver = webDriver;
	}

	/*
	 * public void runSpider() throws Exception { ChromeDriver
	 * webDriver=WebDriverPool.getWebDriver(); do {
	 * List<String>pageList=getPageHtmlByUK(webDriver);
	 * List<FileVo>fileVo=getPageResult(pageList); saveFileInfo(fileVo);
	 * startUK++; } while (startUK <endUK); System.out.println("done...."); }
	 */
	public void runSpider() throws Exception {
		do {
			// 切换IP(爬取9个页面)
			/*
			 * if(index%9==0){ ConnectNetWork.changeIp(2000); }
			 */
			// index++;
			webDriver=geDriverTimer();
			WebDriverPool.blockFetch(webDriver);
			List<String> pageList = getPageHtmlByUK(webDriver);
			List<FileVo> fileVo = getPageResult(pageList);
			saveFileInfo(fileVo);
			WebDriverPool.setFetchIndex(webDriver);
			WebDriverPool.relaseDriver(webDriver);
		} while (startUK < endUK);
		webDriver.close();
		System.out.println("done....");
	}

	/* 根据UK参数获取html */
	private List<String> getPageHtmlByUK(ChromeDriver webDriver)
			throws Exception {
		String url = baseUrl.replace("${uk}", startUK.toString());
		startUK++;
		webDriver.get(url);
		Thread.sleep(3000);
		List<String> list = BaiDuProcessorUtils.getAllPageHtml(webDriver, 3000);
		return list;
	}

	/* 获得处理页面的结果 */
	private List<FileVo> getPageResult(List<String> list) {
		List<FileVo> files = new ArrayList<FileVo>();
		if (list.size() > 0) {
			for (String content : list) {
				List<Element> eles = JsoupUtils.getElementsByClassName(content,
						"file-handler");
				if (eles.size() > 0) {
					for (Element ele : eles) {
						String url = ele.attr("href");
						String title = ele.attr("title");
						String shareTime = JsoupUtils.getShareTime(ele);
						FileVo file = new FileVo();
						file.setTitle(title);
						file.setUrl(url);
						file.setShareTime(shareTime);
						files.add(file);
					}
				} else {
					System.out.println("can't find anything file!");
				}
			}
		} else {
			System.out.println("fetch page content is empt!");
		}
		return files;
	}

	private void saveFileInfo(FileVo file) throws SQLException {
		DBUtils.save("insert into fileInfo VALUES(?,?,?)", file.getTitle(),
				file.getUrl(), file.getShareTime());
	}

	private void saveFileInfo(List<FileVo> files) throws SQLException {
		if (files != null) {
			if (files.size() > 0) {
				for (FileVo fileVo : files) {
					saveFileInfo(fileVo);
				}
			}
		}
	}

	private void changeIp() {

	}

	private ChromeDriver geDriverTimer() {
		while(true){
			ChromeDriver webDriver=WebDriverPool.getWebDriver();
			if (webDriver != null) {
				System.out.println("得到driver.....");
				return webDriver;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("wait......");
		}
	}
	
}
