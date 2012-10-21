package net.tallpixel.ld12oct.utils;

public class Lg {
	
	public static void d(Object... objs) {
		String line = "";
		for(Object obj : objs) {
			if(obj == null) {
				line += "null ";
			} else {
				line += obj + " ";
			}
		}
		
		if(line.length() == 0) return; 
		line = line.substring(0, line.length()-1);
		System.out.println(line);
	}
	
}
