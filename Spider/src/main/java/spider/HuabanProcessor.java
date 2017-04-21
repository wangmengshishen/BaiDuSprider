/*package spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class HuabanProcessor implements PageProcessor {

    private Site site;

    @Override
    public void process(Page page) {
    	System.out.println(page.getHtml().toString());
        page.addTargetRequests(page.getHtml().links().regex("http://huaban\\.com/.*").all());
        if (page.getUrl().toString().contains("pins")) {
            page.putField("img", page.getHtml().xpath("//div[@id='pin_img']/img/@src").toString());
        } else {
            page.getResultItems().setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        if (site == null) {
            site = Site.me().setDomain("huaban.com").addStartUrl("http://huaban.com/").setSleepTime(1000);
        }
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new HuabanProcessor()).thread(1)
                .pipeline(new FilePipeline("D://webmagic"))
                .downloader(new SeleniumDownloader("E://selenium//chromedriver.exe"))
                .run();
    }
}*/