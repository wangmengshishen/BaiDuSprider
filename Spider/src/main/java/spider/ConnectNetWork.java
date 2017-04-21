package spider;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConnectNetWork {
	public static final int fetchCount = 9;
	private static final int changeWaitTimes = 60000;// 毫秒

	/**
	 * 执行CMD命令,并返回String字符串
	 */
	public static String executeCmd(String strCmd) throws Exception {
		System.out.println(strCmd);
		Process p = Runtime.getRuntime().exec("cmd /c " + strCmd);
		StringBuilder sbCmd = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				p.getInputStream(), "GBK"));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
			sbCmd.append(line + "\n");
		}
		return sbCmd.toString();
	}

	/**
	 * 连接ADSL
	 */
	public static boolean connAdsl(String adslTitle, String adslName,
			String adslPass) throws Exception {
		System.out.println("正在建立连接.");
		String adslCmd = "rasdial " + adslTitle + " " + adslName + " "
				+ adslPass;
		String tempCmd = executeCmd(adslCmd);
		// 判断是否连接成功
		if (tempCmd.indexOf("已连接") > 0) {
			System.out.println("已成功建立连接.");
			return true;
		} else {
			System.err.println(tempCmd);
			System.err.println("建立连接失败");
			return false;
		}
	}

	/**
	 * 断开ADSL
	 */
	public static boolean cutAdsl(String adslTitle) throws Exception {
		String cutAdsl = "rasdial " + adslTitle + " /disconnect";
		String result = executeCmd(cutAdsl);

		if (result.indexOf("没有连接") != -1) {
			System.err.println(adslTitle + "连接不存在!");
			return false;
		} else {
			System.out.println("连接已断开");
			return true;
		}
	}

	public static void changeIp(int waitTime) {
		try {
			//cutAdsl("internetLink");
			System.out.println("change ip......");
			Thread.sleep(waitTime);// 所有线程等待10s
			//connAdsl("internetLink", "051511114255", "112233");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 再连，分配一个新的IP
	}

	public static void changeIP() {
		int poolCount = WebDriverPool.getPoolCount();
		for (int i = 0; i < poolCount; i++) {
			int count = WebDriverPool.getisfetchCount(i);
			if (fetchCount != count) {// 保证每个driver fetch 次数都是 fetchCount
				return;
			}
		}
		changeIp(changeWaitTimes);
		for (int i = 0; i < poolCount; i++) {
			WebDriverPool.setisfetchCount(i, 0);
		}
	}

	public static void main(String[] args) throws Exception {
		// connAdsl("宽带","02512358445","320123");
		// Thread.sleep(6000);
		cutAdsl("internetLink");
		// Thread.sleep(6000);
		// 再连，分配一个新的IP
		// System.out.println(connAdsl("internetLink","051511114255","112233"));
	}
}