package com.zjy.test;

import com.zjy.BusFactory.BusFactory;
import com.zjy.BusFactory.BusFactoryImpl.BusFactoryImpl;
import com.zjy.entity.*;
import com.zjy.system.*;

public class Main {
	public static String[] station = new String[]{"省监狱","紫园小区","大迪小区","工人文化宫","大慈阁","北国商厦","古莲花池"};
	public static void main(String[] args) {
		
		BusSystem bs = new BusSystem(10,0,station.length);
		
		Conductor c = new Conductor("售票员39");
		
		//Bus bus = new Bus(1001,"39路",station,bs);
		BusFactory bf = new BusFactoryImpl();
		Bus bus = bf.createBus(1001,"39路",station,bs);
		Passenger passenger = new Passenger("乘客[葫芦娃]",0,1,bs);
		Passenger passenger2 = new Passenger("乘客[蓝精灵]",3,5,bs);
		Passenger passenger3 = new Passenger("乘客[机器猫]",3,6,bs);
		Thread t = new Thread(bus,"公交线程");
		Thread t2 = new Thread(passenger,"乘客线程");
		Thread t3 = new Thread(passenger2,"乘客线程2");
		Thread t4 = new Thread(passenger3,"乘客线程3");
		t.start();
		t2.start();
		t3.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t4.start();
	}

}
