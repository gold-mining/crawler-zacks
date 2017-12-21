package com.james.dao;

import java.io.PrintWriter;

import com.james.modal.ZackData;

public class FileDAO {
	
	public static void output(ZackData data, String finalPath) {
		try {
			FileUtil.createDirectory(finalPath);
			PrintWriter writer = new PrintWriter(finalPath + "/" + data.ticker + ".txt", "UTF-8");
			writer.println(data);
			writer.close();
		} catch (Exception e) {
			System.out.println("Output data for " + data.ticker + " fail");
			System.out.println(e.toString());
		}
		
	}
}