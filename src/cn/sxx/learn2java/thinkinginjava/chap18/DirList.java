package cn.sxx.learn2java.thinkinginjava.chap18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirList {
	public static void main(String[] args) {
		File path = new File(".");
		String[] list;
		String reg = "\\..*";
		if (reg.length() == 0) {
			list = path.list();
		}else {
			list = path.list(new DirFilter(reg));
		}
		
		for (String string : list) {
			System.out.println(string);
		}
	}
}

class DirFilter implements FilenameFilter{
	private Pattern p;
	public DirFilter(String regex) {
		p = Pattern.compile(regex);
	}
		
	@Override
	public boolean accept(File dir, String name) {
		return p.matcher(name).matches();
	}
	
}