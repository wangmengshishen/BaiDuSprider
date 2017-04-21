package spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

    public class MoocProcessor implements PageProcessor {  
        private Site site = new Site().setRetryTimes(3).setSleepTime(100)  
                //添加cookie之前一定要先设置主机地址，否则cookie信息不生效  
                .setDomain("www.imooc.com")  
                //添加抓包获取的cookie信息  
                .addCookie("Hm_lpvt_f0cfcccd7b1393990c78efdeebff3968", "1485168377")  
                .addCookie("Hm_lvt_f0cfcccd7b1393990c78efdeebff3968", "1485167766").addCookie("IMCDNS: ", "0") 
                .addCookie("PHPSESSID", "t40iai164nrbjgcu2gtav9rst6")  
                .addCookie("apsid",  
                        "EyZDdjMjNjNTk5N2Q5YjZlZjAwMmNiOTQ4N2ZiMjIAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAANDc3ODE0MgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAzNzU3Mzc5MDFAcXEuY29tAAAAAAAAAAAAAAAAAAAAADE2NjYwNGExYTgzODg4YWQ4ZmUyMmRkNTg2NWU2MzcxnN2FWJzdhVg%3DM2%3DM2%3DND")  
                .addCookie("cvde", "5885dc5b7cd93-33").addCookie("imooc_isnew", "1")  
                .addCookie("imooc_isnew_ct", "1485167707").addCookie("imooc_uuid", "c0eda702-bfac-45a0-ab34-b6fd607732f1")  
                .addCookie("last_login_username", "375737901@qq.com").addCookie("loginstate", "1")  
                //添加请求头，有些网站会根据请求头判断该请求是由浏览器发起还是由爬虫发起的  
                .addHeader("User-Agent",  
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393")  
                .addHeader("Accept", "text/html, application/xhtml+xml, image/jxr, */*")  
                .addHeader("Accept-Encoding", "gzip, deflate").addHeader("Accept-Language", "zh-CN")  
                .addHeader("Connection", "keep-alive").addHeader("Referer", "http://www.imooc.com/");  
      
        @Override  
        public void process(Page page) {  
            page.putField("common-title", page.getHtml().toString());  
        }  
      
        @Override  
        public Site getSite() {  
            return site;  
        }  
        public static void main(String[] args) {  
            Spider.create(new MoocProcessor())  
                    // 从"http://www.imooc.com/user/setprofile"开始抓  
                    .addUrl("https://pan.baidu.com/share/home?uk=877701619#category/type=0").addPipeline(new ConsolePipeline())  
                    // 开启5个线程抓取  
                    .thread(1)  
                    // 启动爬虫  
                    .run();  
        } 
    }  