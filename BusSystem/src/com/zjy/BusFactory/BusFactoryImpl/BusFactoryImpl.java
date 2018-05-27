package com.zjy.BusFactory.BusFactoryImpl;

import com.zjy.BusFactory.BusFactory;
import com.zjy.entity.Bus;
import com.zjy.system.BusSystem;

public class BusFactoryImpl implements BusFactory{

	public Bus createBus(Integer id, String name ,String[] station,BusSystem bs) {
		return new Bus(id,name ,station,bs);
	}

}
