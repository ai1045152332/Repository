package com.honghe.recordweb.service.frontend.websocket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhanghongbin
 * Date: 14-9-17
 * Time: 下午1:30
 * To change this template use File | Settings | File Templates.
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

             MessageSender.send("1","xxxxxxxxxxxxx");
    }
}
