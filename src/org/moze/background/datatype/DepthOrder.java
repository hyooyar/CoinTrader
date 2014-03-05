package org.moze.background.datatype;

import java.util.ArrayList;

public class DepthOrder {

	private ArrayList<Order> mAskOrders = new ArrayList<Order>();
	private ArrayList<Order> mBidOrders = new ArrayList<Order>();
	
	public DepthOrder(ArrayList<Order> askOrders , ArrayList<Order> bidOrders) {
		mAskOrders = askOrders;
		mBidOrders = bidOrders;
	}
	
	public ArrayList<Order> getAskOrders() {
		return mAskOrders;
	}

	public ArrayList<Order> getBidOrders() {
		return mBidOrders;
	}
	
}