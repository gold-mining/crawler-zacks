package com.james.modal;

public class ZackData {
	public String Price;
	public String Industry;
	public String IndustrySection;
	public String ZacksRank;
	public String IndustryRank;
	public String ZacksSectorRank;
	public String ValueScores;
	public String GrowthScores;
	public String MomentumScores;
	public String TotalScores;
	
	public String Open;
	public String DayLow;
	public String DayHigh;
	public String Low52Wk;
	public String High52Wk;
	public String AvgVolume;
	public String MarketCap;
	public String Dividend;
	public String Beta;
	public String EarningsESP;
	public String MostAccurateEst;
	public String CurrentQtrEst;
	public String CurrentYrEst;
	public String ExpEarningsDate;
	public String PriorYearEPS;
	public String ExpEPSGrowth;
	public String ForwardPE;
	public String PEGRatio;
	
	@Override
	public String toString() {
		return Price + "\t" + Industry + "\t" + IndustrySection + "\t" + ZacksRank + "\t" + IndustryRank + "\t" + ZacksSectorRank + "\t" + ValueScores + "\t" + GrowthScores + "\t" + MomentumScores + "\t" + TotalScores + "\t" + Open + "\t" + DayLow + "\t" + DayHigh + "\t" + Low52Wk + "\t" + High52Wk + "\t" + AvgVolume + "\t" + MarketCap + "\t" + Dividend + "\t" + Beta + "\t" + EarningsESP + "\t" + MostAccurateEst + "\t" + CurrentQtrEst + "\t" + CurrentYrEst + "\t" + ExpEarningsDate + "\t" + PriorYearEPS + "\t" + ExpEPSGrowth + "\t" + ForwardPE + "\t" + PEGRatio;
	}
	
	public static String getLable() {
		return "Price" + "\t" + "Industry" + "\t" + "IndustrySection" + "\t" + "ZacksRank" + "\t" + "IndustryRank" + "\t" + "ZacksSectorRank" + "\t" + "ValueScores" + "\t" + "GrowthScores" + "\t" + "MomentumScores" + "\t" + "TotalScores" + "\t" + "Open" + "\t" + "DayLow" + "\t" + "DayHigh" + "\t" + "Low52Wk" + "\t" + "High52Wk" + "\t" + "AvgVolume" + "\t" + "MarketCap" + "\t" + "Dividend" + "\t" + "Beta" + "\t" + "EarningsESP" + "\t" + "MostAccurateEst" + "\t" + "CurrentQtrEst" + "\t" + "CurrentYrEst" + "\t" + "ExpEarningsDate" + "\t" + "PriorYearEPS" + "\t" + "ExpEPSGrowth" + "\t" + "ForwardPE" + "\t" + "PEGRatio";
	}
}
