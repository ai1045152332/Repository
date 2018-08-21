package com.zjy.entity;

import java.util.Date;

import com.zjy.system.BusSystem;
import com.zjy.util.DateUtil;

public class Bus implements Runnable {

	private Integer id;
	private String name;
	private String[] station;
	public Bus(Integer id, String name ,String[] station,BusSystem bs) {
		super();
		this.id = id;
		this.name = name;
		this.station = station;
		this.bs = bs;
	}
	public void startBus() {
		System.out.println("■■■■■■■发票■■■■■■■■■"
				+ "\n■欢迎乘坐本次公交车"
				+ "\n■编号:" + id 
				+ "\n■公交车名:" + name
				+"\n■出发时间:"+DateUtil.dateFormat(new Date())
				+"\n■■■■■■■■■■■■■■■■■■");
	}

	public void runBefore() {
		System.out.println("\n"+
"                              __________________\n"+
"                             |[_][_][_][_][_][_]|\n"+
"公交车启动...                 |o _         _  _  |\n"+
"                              `(_)-------(_)(_)-\n");
	}
	public void runBus() {
		System.out.println("\n"+
"                              __________________\n"+
"                             |[_][_][_][_][_][_]|\n"+
"公交车在行驶...               |o _         _  _  |\n"+
"                              `(_)-------(_)(_)-\n");
		bs.setStatus(1);
		bs.setDoor(-1);
	}

	public void stopBus(int i) {
		System.out.println("公交车到站了.." + station[i]);
	}

	@Override
	public synchronized void run() {
		//全程车站数循环
		startBus();
		for (int i = 0; i < station.length; i++) {
			if(i!=0){
				System.out.println("========================本站结束==============================");
			}
			runBefore();
			try {
				// 设置沉睡距离 2S---200M
				Thread.sleep(1000);
				runBus();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// 停车到站/乘务员开门
			stopBus(i);
			bs.setStationNo(i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int[] a = BusSystem.stationHavePeople;
			//有人等车
			if(a[bs.getStationNo()]>0){
				//车门开了车停了
				bs.setDoor(1);
				bs.setStatus(2);
				//进行车操作
				bs.goBus();
			}else{
				//车停时   不上车不下车
				bs.noPeople();
			}
		}
		System.out.println("::::::::终点站到了  结束行程::::::::\n到达时间"+DateUtil.dateFormat(new Date()));

	}

	private BusSystem bs;
	
	public BusSystem getBs() {
		return bs;
	}
	public void setBs(BusSystem bs) {
		this.bs = bs;
	}
	//------------------------------------
	public Bus() {}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String[] getStation() {
		return station;
	}

	public void setStation(String[] station) {
		this.station = station;
	}

}
