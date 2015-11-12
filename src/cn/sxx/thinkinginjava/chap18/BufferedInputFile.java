package cn.sxx.thinkinginjava.chap18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile {
	public static String read(String filename) throws IOException{
		FileReader fr = new FileReader(filename);
		BufferedReader in = new BufferedReader(fr);
		
		StringBuilder sb = new StringBuilder();
		String s = "";
		while ((s = in.readLine()) != null){	//ע��readLine()�Ὣ���з�ɾ��
			sb.append(s + "\n");
		}
		in.close();
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(read("D:\\SXX\\Study\\JavaStudy\\ThinkingInJava\\testfile.txt"));
	}
}
