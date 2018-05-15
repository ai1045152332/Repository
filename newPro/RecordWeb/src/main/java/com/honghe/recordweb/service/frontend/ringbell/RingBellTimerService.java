package com.honghe.recordweb.service.frontend.ringbell;

import com.honghe.recordhibernate.entity.Ringbell;

/**
 * Created by yanxue on 2015-06-23.
 */
public interface RingBellTimerService {


    /**
     * 初始化
     */
    public void init();


    /**
     * 删除定时计划时，删除有关该计划的所有定时器
     * @param pid
     * @return
     */
    public boolean delTimerbyRingBell(int pid);


    /**
     * 添加定时计划时，添加将该定时计划有关的定时器
     * @param ringbell
     * @param hostStr
     * @return
     */
    public boolean addTimerbyRingBell(final Ringbell ringbell,String hostStr);


    /**
     *  删除班级时，删除有关该班级的所有定时器
     * @param hid
     * @return
     */
    public boolean delTimerbyHost(int hid);


}
