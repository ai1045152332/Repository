package com.zjy.BusFactory;

import com.zjy.entity.*;
import com.zjy.system.BusSystem;

public interface BusFactory {
	Bus createBus(Integer id, String name ,String[] station,BusSystem bs);
}
