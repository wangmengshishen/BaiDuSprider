package hadoop;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.Test;

public class HDFSTest {
@Test
/*将本地文件复制到Hadoop文件系统*/
public void test() throws Exception{
	String path="E:/fileTest/helloWord.txt";
	String des="/user/hadoop/helloWord1.txt/";
	InputStream ins=new FileInputStream(path);
	Configuration conf=new Configuration();
	FileSystem fsystem= FileSystem.get(URI.create(des), conf);
	OutputStream os=fsystem.create(new Path(des),new Progressable() {
		
		@Override
		public void progress() {
			// TODO Auto-generated method stub
			System.out.println(".");
			
		}
	});
	IOUtils.copyBytes(ins, os, 4096, true);
}
}
