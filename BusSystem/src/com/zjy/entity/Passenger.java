package com.zjy.entity;

import com.zjy.system.BusSystem;
import com.zjy.test.Main;

public class Passenger implements Runnable {
	private String name;
	private int startStation;
	private int finalStation;
	public Passenger() {
	}

	public void waitBus() {
		System.out.println("\t\t\t\t\t\t"+this.name+"在等车--怎么还不来?我要从"+Main.station[startStation]+"去"+Main.station[finalStation]);
		int[] a = BusSystem.stationHavePeople;
		a[startStation]++;
	}

	public void joinBus() {
		System.out.println("\t\t\t\t\t\t"+this.name+"上车了!");
		bs.setBuyTicket(-1);
	}

	public void buyTicketBus() {
		System.out.println("\t\t\t\t\t\t"+this.name+"收到售票员的票,买票了!");
		bs.setBuyTicket(1);
	}

	public void getOffBus() {
		System.out.println("\t\t\t\t\t\t"+this.name+"到站了下车");
		bs.setNum(bs.getNum()-1);
	}

	@Override
	public synchronized void run() {
		waitBus();
		while(true){
			try {
				Thread.sleep(50);//稳一下
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if(startStation==bs.getStationNo()&&bs.getDoor()==1&&bs.getStatus()==2){
				//到站了 成功上车
				joinBus();
				bs.sellTicket();
				buyTicketBus();
				bs.getOnBusOver();
			}
			if(finalStation==bs.getStationNo()){
				//乘客下车
				getOffBus();
				break;
			}

		}
	}


	public Passenger(String name, int startStation, int finalStation, BusSystem bs) {
		super();
		this.name = name;
		this.startStation = startStation;
		this.finalStation = finalStation;
		this.bs = bs;
	}


	private BusSystem bs;
	
	public BusSystem getBs() {
		return bs;
	}
	public void setBs(BusSystem bs) {
		this.bs = bs;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStartStation() {
		return startStation;
	}

	public void setStartStation(int startStation) {
		this.startStation = startStation;
	}

	public int getFinalStation() {
		return finalStation;
	}

	public void setFinalStation(int finalStation) {
		this.finalStation = finalStation;
	}
}
