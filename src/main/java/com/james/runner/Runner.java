package com.james.runner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.james.crawler.ZacksCrawler;
import com.james.dao.ConsoleDAO;
import com.james.dao.FileDAO;
import com.james.dao.FileUtil;
import com.james.modal.ZackData;

public class Runner {
	
	public static void main(String[] args) {
		Date date1 = new Date();

		Map<String, String> input = new HashMap<String, String>();
		for (int i = 0; i < args.length; i++) {
			String[] parts = args[i].trim().split("=");
			input.put(parts[0], parts[1]);
			System.out.println(parts[0] + ": " + parts[1]);
		}

		Runner runner = new Runner();

		switch (input.get("mode")) {
			case "single-thread": runner.getZacksData(input.get("stock-list"), input.get("output")); break;
			case "multithread": runner.getZacksData(input.get("stock-list"), input.get("output"), input.get("thread")); break;
			default: break;
		}

		Date date2 = new Date();
		System.out.println(date2.getTime() - date1.getTime());
	}
	
	public void getZacksData(String stockList, String output, String threadNumber) {
		try {
			Queue<String> queue = FileUtil.getStockListFromFile(stockList);			
			ExecutorService executor = Executors.newFixedThreadPool(Integer.parseInt(threadNumber));

			while (!queue.isEmpty()) {
				executor.execute(new ZacksRunner(queue.poll(), output));
			}

			executor.shutdown();
			executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getZacksData(String stockList, String output) {
		Queue<String> queue = FileUtil.getStockListFromFile(stockList);
		while (!queue.isEmpty()) {
			ZacksRunner runner = new ZacksRunner(queue.poll(), output);
			runner.run();
		}
	}
	
	class ZacksRunner implements Runnable {
		
		private String ticker; 
		private String finalPath;
		
		public ZacksRunner(String ticker, String output) {
			this.ticker = ticker;
			this.finalPath = output;
		}
		
		@Override
		public void run() {
			if(FileUtil.isFileExist(finalPath + "/" + ticker + ".txt")) return; 
			ZacksCrawler dataCrawler = new ZacksCrawler();
			ZackData data = dataCrawler.getDetailInfo(ticker);
			ConsoleDAO.output(data, true);
			FileDAO.output(data, finalPath);
		}
		
	}

}
