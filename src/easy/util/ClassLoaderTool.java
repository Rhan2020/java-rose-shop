package easy.util;

import java.io.InputStream;
import java.net.URL;

public class ClassLoaderTool {
	static ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	public ClassLoaderTool() {
	}
	public static String getClassFolder(){
		URL url=classLoader.getResource(".");
		return url.getPath().substring(1);
	}
	/**
	 * 读取class目录下资源文件
	 * @param path class目录下的资源文件，相对目录
	 * @return 流
	 */
	public static InputStream getResourceAsStream(String path){
		return classLoader.getResourceAsStream(path);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(ClassLoaderTool.getClassFolder());
	}

}
