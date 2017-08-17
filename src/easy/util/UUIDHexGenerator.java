package easy.util;

import java.net.InetAddress;

public class UUIDHexGenerator  {

	private String sep = "";
	
	private static final int IP;
	static {
		int ipadd;
		try {
			ipadd = toInt( InetAddress.getLocalHost().getAddress() );
		}
		catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	private static short counter = (short) 0;
	private static final int JVM = (int) ( System.currentTimeMillis() >>> 8 );

	protected static int toInt( byte[] bytes ) {
		int result = 0;
		for (int i=0; i<4; i++) {
			result = ( result << 8 ) - Byte.MIN_VALUE + (int) bytes[i];
		}
		return result;
	}
	
	protected short getCount() {
		synchronized(UUIDHexGenerator.class) {
			if (counter<0) counter=0;
			return counter++;
		}
	}
	
	protected short getHiTime() {
		return (short) ( System.currentTimeMillis() >>> 32 );
	}
	protected int getLoTime() {
		return (int) System.currentTimeMillis();
	}

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace( 8-formatted.length(), 8, formatted );
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace( 4-formatted.length(), 4, formatted );
		return buf.toString();
	}

	protected String generateUUID() {
		return new StringBuffer(36)
			.append( format( IP ) ).append(sep)
			.append( format( JVM ) ).append(sep)
			.append( format( getHiTime() ) ).append(sep)
			.append( format( getLoTime() ) ).append(sep)
			.append( format( getCount() ) )
			.toString();
	}

	public static String generate(){
		return new UUIDHexGenerator().generateUUID();
	}
	public static void main( String[] args ) throws Exception {
//		UUIDHexGenerator gen = new UUIDHexGenerator();
//		UUIDHexGenerator gen2 = new UUIDHexGenerator();
		long s=System.currentTimeMillis();
		for ( int i=0; i<100000; i++) {
//			String id = (String) gen.generate();
//			System.out.println(id);
//			String id2 = (String) gen2.generate();
//			System.out.println(id2);
			String id2 = UUIDHexGenerator.generate();
			System.out.println(id2);
		}
		System.out.println(System.currentTimeMillis()-s);
	}

}
