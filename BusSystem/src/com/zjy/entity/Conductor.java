package com.zjy.entity;

public class Conductor implements Runnable{

	private String name;
	
	public Conductor() {}

	public Conductor(String name) {
		super();
		this.name = name;
	}

	public void openDoor(){
		System.out.println("开车门");
	}
	public void sellTicket(){
		System.out.println("卖票");
	}
	public void closeDoor(){
		System.out.println("关门");
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		
	}

	

}
