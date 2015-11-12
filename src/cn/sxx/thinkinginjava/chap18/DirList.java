package cn.sxx.thinkinginjava.chap18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirList {
	public static void main(String[] args) {
		File path = new File("D:\\SXX\\TestPics");
		String[] list;
		String reg = ".*\\.jpg";
		if (reg.length() == 0) {
			list = path.list();
		}else {
			list = path.list(new FilenameFilter() {		//使用匿名内部类
				private Pattern p = Pattern.compile(reg);			
				@Override
				public boolean accept(File dir, String name) {
					return p.matcher(name).matches();
				}
			});
		}
		
		for (String string : list) {
			System.out.println(string);
		}
	}
	
}
