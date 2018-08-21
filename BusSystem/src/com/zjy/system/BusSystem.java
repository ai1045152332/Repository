package com.zjy.system;

public class BusSystem {

	// 此时状态
	private int status = 1; // 1运行 2停车
	// 是否开门
	private int door = -1;// -1没开 1开
	// 站台号
	private int stationNo = -1;
	// 买票状态
	private int buyTicket = -1;// -1没买 1购买

	// 最大载客数
	private int maxPeopleNumber;
	// 当前乘客数
	private int num;
	//本站上车人数
	public static int[] stationHavePeople;
	
	public synchronized void goBus() {

		if (num <= maxPeopleNumber) {
			// 关门开车继续行驶
			if (status == 1 && door == -1) {
				super.notify();
			}
			// 车停门开 等待
			if (status == 2 && door == 1) {
				openDoor();
				try {
					super.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {

			}
		}else{
			System.out.println("\t\t\t抱歉人满了,不能上车了!");
		}
	}

	public void openDoor() {
		System.out.println("\t\t\t开车门");
	}

	public void sellTicket() {
		System.out.println("\t\t\t卖票");
		num++;
	}

	public void closeDoor() {
		try {
			// 等待是否有下车
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		door=-1;
		status=1;
		System.out.println("\t\t\t关门,车起步,请扶好坐好");
	}

	public void noPeople() {
		System.out.println("system:没人上车哦~");
		status = 1;
		door = -1;
		closeDoor();
		goBus();

	}

	public void getOnBusOver() {
		// 上车完成
		closeDoor();
		status = 1;
		door = -1;
		try {// 缓一会
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		goBus();

	}

	// 构造方法----------------------
	public BusSystem() {
	}

	public BusSystem(int maxPeopleNumber, int num,int size) {
		super();
		this.maxPeopleNumber = maxPeopleNumber;
		this.num = num;
		stationHavePeople=new int[size];
		for(int i=0;i<size;i++){
			stationHavePeople[i]=0;
		}
	}

	// set get----------------------
	public int getDoor() {
		return door;
	}

	public void setDoor(int door) {
		this.door = door;
	}

	public int getBuyTicket() {
		return buyTicket;
	}

	public void setBuyTicket(int buyTicket) {
		this.buyTicket = buyTicket;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStationNo() {
		return stationNo;
	}

	public void setStationNo(int stationNo) {
		this.stationNo = stationNo;
	}

	public void setMaxPeopleNumber(int maxPeopleNumber) {
		this.maxPeopleNumber = maxPeopleNumber;
	}

	public int getMaxPeopleNumber() {
		return maxPeopleNumber;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	
}
