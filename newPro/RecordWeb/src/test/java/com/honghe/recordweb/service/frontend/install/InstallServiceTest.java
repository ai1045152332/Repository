package com.honghe.recordweb.service.frontend.install;

import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

public class InstallServiceTest {

    @Resource
    private InstallService installService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testReadFile() throws Exception {

            installService.readFile("");
    }

    @Test
    public void testDeleteFile() throws Exception {

    }

    @Test
    public void testOneKeyInstallService() throws Exception {

    }
}