package com.james.crawler;

import java.io.File;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.james.modal.ZackData;

import us.codecraft.xsoup.Xsoup;

public class ZacksCrawler {

	private String ticker = "";

	private String finalOutPath = "";

	private PrintWriter writer;

	public ZacksCrawler() {
		super();
	}

	public boolean initCrawler(String ticker, String date, String output) {
		this.ticker = ticker;
		this.finalOutPath = output + "/" + date;
		if (isFileExist()) return false;

		try {
			new File(finalOutPath).mkdirs();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void getDetailInfo() {
		try {
			Document document = Jsoup.connect("https://www.zacks.com/stock/quote/" + this.ticker).get();
			ZackData zackData = getZackData(document);
			outputToFile(zackData);
			// outputToConsole(zackData);
		} catch (Exception e) {
			System.err.println(this.ticker);
			e.printStackTrace();
		}
	}

	private ZackData getZackData(Document document) {
		ZackData zackData = new ZackData();
		
		zackData.Price = document.select("#get_last_price").get(0).text();
		zackData.Industry = Xsoup.compile("//*[@id=\"quote_overview\"]/div[2]/table/tbody/tr/td/a[1]").evaluate(document).getElements().get(0).text();
		zackData.IndustrySection = Xsoup.compile("//*[@id=\"quote_overview\"]/div[2]/table/tbody/tr/td/a[2]").evaluate(document).getElements().get(0).text();;
		
		Elements premiumResearch = Xsoup.compile("//*[@id=\"premium_research\"]/div/table/tbody/tr").evaluate(document).getElements();
		zackData.ZacksRank = Xsoup.compile("tr/td").evaluate(premiumResearch.get(0)).getElements().get(0).text();
		zackData.IndustryRank = Xsoup.compile("tr/td").evaluate(premiumResearch.get(1)).getElements().get(0).text();
		zackData.ZacksSectorRank = Xsoup.compile("tr/td").evaluate(premiumResearch.get(2)).getElements().get(0).text();
		String styleScores = Xsoup.compile("th/p").evaluate(premiumResearch.get(3)).getElements().get(0).text();
		zackData.ValueScores = styleScores.split(" ")[0];
		zackData.GrowthScores = styleScores.split(" ")[3];
		zackData.MomentumScores = styleScores.split(" ")[6];
		zackData.TotalScores = styleScores.split(" ")[9];
		
		Element stockActivity = Xsoup.compile("//*[@id=\"stock_activity\"]/table/tbody").evaluate(document).getElements().get(0);
		zackData.Open = Xsoup.compile("tbody/tr[1]/td[2]").evaluate(stockActivity).getElements().get(0).text();
		zackData.DayLow = Xsoup.compile("tbody/tr[2]/td[2]").evaluate(stockActivity).getElements().get(0).text();
		zackData.DayHigh = Xsoup.compile("tbody/tr[3]/td[2]").evaluate(stockActivity).getElements().get(0).text();
		zackData.Low52Wk = Xsoup.compile("tbody/tr[4]/td[2]").evaluate(stockActivity).getElements().get(0).text();
		zackData.High52Wk = Xsoup.compile("tbody/tr[5]/td[2]").evaluate(stockActivity).getElements().get(0).text();
		zackData.AvgVolume = Xsoup.compile("tbody/tr[6]/td[2]").evaluate(stockActivity).getElements().get(0).text();
		zackData.MarketCap = Xsoup.compile("tbody/tr[7]/td[2]").evaluate(stockActivity).getElements().get(0).text();
		zackData.Dividend = Xsoup.compile("tbody/tr[8]/td[2]").evaluate(stockActivity).getElements().get(0).text();
		zackData.Beta = Xsoup.compile("tbody/tr[9]/td[2]").evaluate(stockActivity).getElements().get(0).text();
		
		Element stockKeyEarnings = Xsoup.compile("//*[@id=\"stock_key_earnings\"]/table/tbody").evaluate(document).getElements().get(0);
		zackData.EarningsESP = Xsoup.compile("tbody/tr[1]/td[2]").evaluate(stockKeyEarnings).getElements().get(0).text();
		zackData.MostAccurateEst = Xsoup.compile("tbody/tr[2]/td[2]").evaluate(stockKeyEarnings).getElements().get(0).text();
		zackData.CurrentQtrEst = Xsoup.compile("tbody/tr[3]/td[2]").evaluate(stockKeyEarnings).getElements().get(0).text();
		zackData.CurrentYrEst = Xsoup.compile("tbody/tr[4]/td[2]").evaluate(stockKeyEarnings).getElements().get(0).text();
		zackData.ExpEarningsDate = Xsoup.compile("tbody/tr[5]/td[2]").evaluate(stockKeyEarnings).getElements().get(0).text();
		zackData.PriorYearEPS = Xsoup.compile("tbody/tr[6]/td[2]").evaluate(stockKeyEarnings).getElements().get(0).text();
		zackData.ExpEPSGrowth = Xsoup.compile("tbody/tr[7]/td/p").evaluate(stockKeyEarnings).getElements().get(0).text();
		zackData.ForwardPE = Xsoup.compile("tbody/tr[8]/td[2]").evaluate(stockKeyEarnings).getElements().get(0).text();
		zackData.PEGRatio = Xsoup.compile("tbody/tr[9]/td[2]").evaluate(stockKeyEarnings).getElements().get(0).text();		
		
		return zackData;		
	}

	public void outputToFile(ZackData zackData) throws Exception {
		this.writer = new PrintWriter(finalOutPath + "/" + this.ticker + ".txt", "UTF-8");
		this.writer.println(this.ticker);
		this.writer.println("\t" + ZackData.getLable());
		this.writer.println("\t" + zackData);
		this.writer.close();
	}
	
	public void outputToConsole(ZackData zackData) throws Exception {
		System.out.println(this.ticker);
		System.out.println("\t" + ZackData.getLable());
		System.out.println("\t" + zackData);
	}
	
	private boolean isFileExist() {
		File file = new File(finalOutPath + "/" + this.ticker + ".txt");
		return file.exists() && file.length() != 0;
	}

}
