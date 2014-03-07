package org.moze.background.fxbtc.datatype;

public class UserOrder {
	
	private String mSymbol = "";
	
	private long mId;
	
	private String mType = "";
	
	private double mRate;
	
	private double mVol;

	public void setSymbol(String symbol) {
		mSymbol = symbol;
	}
	
	public String getSymbol(){
		return mSymbol;
	}

	public void setId(long id) {
		mId = id;
	}
	
	public long getId() {
		return mId;
	}
	
	public void setType(String type) {
		mType = type;
	}
	
	public String getType() {
		return mType;
	}

	public void setRate(double rate) {
		mRate = rate;
	}

	public double getRate() {
		return mRate;
	}
	
	public void setVol(double vol) {
		mVol = vol;
	}
	
	public double getVol() {
		return mVol;
	}

}
