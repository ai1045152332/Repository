package com.honghe.recordweb.service.frontend.timeplan;

import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordweb.service.frontend.hostgroup.HostgroupService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

/**
 * Created by lch on 2014/10/13.
 */
public class HostgroupServiceTest {
    private HostgroupService hostgroupService;
    @Before
    public void setUp() throws Exception {
        hostgroupService = DaoFactory.getDao(HostgroupService.class);
    }

    @Test
    public void testHostInGroup() throws Exception
    {
       /* List<Map> hostInGroup = hostgroupService.hostInGroup(1,"hostRelation",1,1);
        System.out.println(hostInGroup.size());
        assertNotNull(hostInGroup);*/


    }
}
