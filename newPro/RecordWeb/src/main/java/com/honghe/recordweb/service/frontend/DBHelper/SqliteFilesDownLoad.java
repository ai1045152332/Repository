package com.honghe.recordweb.service.frontend.DBHelper;

import com.honghe.recordweb.service.frontend.news.NewsService;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by hthwx on 2015/3/14.
 */
@WebServlet(urlPatterns = "/sqliteFilesDownloadService", loadOnStartup = 1)
public class SqliteFilesDownLoad extends HttpServlet {
    private NewsService newsService;

    /**
     * 重写doPost
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        newsService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(NewsService.class);
        req.setCharacterEncoding("ISO8859-1");
        res.setContentType("text/html;charset=utf-8");
        String path = getServletContext().getRealPath("/");
        newsService.generateAllSqLiteByDspecId(path);
    }

    /**
     * 重写doGet
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
