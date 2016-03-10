package cn.sxx.thinkinginjava.chap18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirList {
	public static void main(String[] args) {
		File path = new File("D:\\SXX\\TestPics");
		String[] list = path.list(new DirFilter("image."));
		System.out.println(list.length);
		for (String dirItem : list) {
			System.out.print(dirItem + " ");
		}
	}
	
}

class DirFilter implements FilenameFilter{
	private Pattern pattern;

	public DirFilter(String regex) {
		pattern = Pattern.compile(regex);
	}
	
	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).matches();
	}
	
	
}