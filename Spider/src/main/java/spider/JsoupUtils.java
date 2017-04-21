package spider;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class JsoupUtils {
	//加载文档
public static Document getDocument(String html){
	Document doc = Jsoup.parse(html);
	return doc;
}
public static List<Element> getElementsByClassName(String html,String className){
	Document doc=getDocument(html);
	//System.out.println(html);
	List<Element> content = doc.getElementsByClass(className);
	return content;
}
public static String getShareTime(Element ele){
	String str= ele.parentNode().parentNode().nextSibling().childNode(0).outerHtml();
	str=str.substring(6, str.lastIndexOf("<"));
	return str;
}
}
