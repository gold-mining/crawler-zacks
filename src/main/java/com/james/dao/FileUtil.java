package com.james.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class FileUtil {
	public static boolean isFileExist(String finalPath) {
		File file = new File(finalPath);
		return file.exists() && file.length() != 0;
	}

	public static void createDirectory(String filePath) {
		try {
			new File(filePath).mkdirs();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static Queue<String> getStockListFromFile(String stockList) {
		Queue<String> stockQueue = new LinkedList<String>();

		try {
			File file = new File(stockList);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stockQueue.add(line.split("\t")[0]);
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return stockQueue;
	}
}
