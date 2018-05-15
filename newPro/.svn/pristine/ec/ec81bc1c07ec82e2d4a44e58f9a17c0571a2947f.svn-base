package com.honghe.recordweb.service.frontend.timeplan;

import com.honghe.recordhibernate.dao.ClasstimeDao;
import com.honghe.recordhibernate.dao.config.DaoFactory;
import com.honghe.recordhibernate.entity.Classtime;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.List;

import static org.junit.Assert.*;

public class ClasstimeServiceTest {
    private ClasstimeService classtimeService;
    @Before
    public void setUp() throws Exception {
        classtimeService = DaoFactory.getDao(ClasstimeService.class);
    }

    @Test
    public void testClasstimeList() throws Exception {
        List<Classtime> classtimelist = (List<Classtime>)classtimeService.classtimeList();
        //List<Classtime> classtimes = classtimelist;
        //assertNull(classtimelist);
        System.out.print(classtimelist);
        assertEquals(classtimelist.get(0).getClasstimeId(),1);
    }

    @Test
    public void testAddClasstime() throws Exception {
        String[] weekids;
        String[] starttime ;
        String[] starttime_1 ;
        String[] endtime;
        String[] endtime_1;
        weekids = new String[2];
        weekids[0] = "1";
        weekids[1] = "2";
        starttime = new String[2];
        starttime_1 = new String[2];
        starttime[0] = "8";
        starttime[1] = "9";
        starttime_1[0] = "8";
        starttime_1[1] = "9";
        endtime = new String[2];
        endtime_1 = new String[2];
        endtime[0] = "8";
        endtime[1] = "9";
        endtime_1[0] = "8";
        endtime_1[1] = "9";
        Boolean bool = classtimeService.addClasstime(weekids,starttime,starttime_1,endtime,endtime_1);
        assertTrue("true", bool);
    }

    @Test
    public void testUpdateClasstime() throws Exception {
        String[] starttime ;
        String[] starttime_1 ;
        String[] endtime;
        String[] endtime_1;
        starttime = new String[2];
        starttime_1 = new String[2];
        starttime[0] = "8";
        starttime[1] = "9";
        starttime_1[0] = "8";
        starttime_1[1] = "9";
        endtime = new String[2];
        endtime_1 = new String[2];
        endtime[0] = "8";
        endtime[1] = "9";
        endtime_1[0] = "8";
        endtime_1[1] = "9";

        String[] classtimeid;
        classtimeid = new String[1];
        classtimeid[0] = "1";
        classtimeid[1] = "2";

        String[] weekids;
        weekids = new String[2];
        weekids[0] = "1";
        weekids[1] = "2";
        int bool = classtimeService.updateClasstime(classtimeid,weekids,starttime,starttime_1,endtime,endtime_1);
        //assertTrue("true",bool);
    }

    @Test
    public void testDelete() throws Exception {

    }
}