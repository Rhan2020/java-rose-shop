package easy.util;

import java.util.UUID;


public class UUIDTool {

	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long s=System.currentTimeMillis();
		for ( int i=0; i<100000; i++) {
//			String id = (String) gen.generate();
//			System.out.println(id);
//			String id2 = (String) gen2.generate();
//			System.out.println(id2);
			String id2 = UUIDTool.getUUID();
			System.out.println(id2);
		}
		System.out.println(System.currentTimeMillis()-s);
	}

}
