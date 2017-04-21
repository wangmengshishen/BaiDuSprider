package spider;


public class BaiDuSpiderRunnable implements Runnable {
	public void run() {
		BaiDuSpider spider=new BaiDuSpider();
		try {
			spider.runSpider();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			e.printStackTrace();
		}
	}

}
