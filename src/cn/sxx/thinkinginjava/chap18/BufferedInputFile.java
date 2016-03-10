package cn.sxx.thinkinginjava.chap18;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BufferedInputFile {
//	public static String read(String filename) throws IOException{
//		BufferedReader in = new BufferedReader(new FileReader(filename));
//		String s;
//		StringBuilder sb = new StringBuilder();
//		while ((s = in.readLine()) != null){
//			sb.append(s + "\n");
//		}
//		in.close();
//		return sb.toString();
//	}
	
	public static List<String> read(String name) throws IOException {
		List<String> retList = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(name));
		String s;
		while ((s = reader.readLine()) != null){
			retList.add(s);
		}
		reader.close();
		return retList;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(read("D:\\SXX\\TestPics\\test.txt"));
	}
}
