package com.sist.temp;
import java.util.*;
import java.io.*;
public class MainClass {
	public static void main(String[] args) {
		componentScan("com.sist.model");
	}

	public static List<String> componentScan(String pack) {
		List<String> list = new ArrayList<String>();
		try {
			String path = "C:\\Users\\sist\\git\\Home\\MVCProject4\\src";
			path = path + "\\" + pack.replace(".", "\\");
			File dir = new File(path);
			File[] files = dir.listFiles();
			for (File f : files) {
				// System.out.println(f.getName());
				String ext = f.getName().substring(f.getName().lastIndexOf(".") + 1);
				if (!ext.equals("java"))
					continue;
				String p = pack + "." + f.getName().substring(0, f.getName().lastIndexOf("."));
				System.out.println(p);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

}
