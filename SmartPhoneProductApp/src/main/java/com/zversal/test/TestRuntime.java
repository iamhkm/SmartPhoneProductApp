package com.zversal.test;

import java.io.IOException;

public class TestRuntime {
	public static void main(String args[]){
		Runtime r = Runtime.getRuntime();
		try {
			r.exec("start.exe C:\\Users\\MITTAL\\Desktop\\other\\project.docx");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
