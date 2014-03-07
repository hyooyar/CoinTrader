package org.moze.background.fxbtc.datatype;

public class Order {
	private int mCount = -1;
	private double mRate = -1;
	private double mVol = -1;
	
	public Order(int count , double rate , double vol){
		mCount = count;
		mRate = rate;
		mVol = vol;
	}
	
	public int getCount() {
		return mCount;
	}
	
	public double getRate() {
		return mRate;
	}
	
	public double getVol() {
		return mVol;
	}
}
