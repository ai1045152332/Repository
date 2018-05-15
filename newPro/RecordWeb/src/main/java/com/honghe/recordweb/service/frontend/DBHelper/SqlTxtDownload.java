package com.honghe.recordweb.service.frontend.DBHelper;

import com.honghe.recordhibernate.dao.NewsDao;
import com.honghe.recordweb.service.frontend.news.NewsService;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Created by hthwx on 2015/3/6.
 */
@WebServlet(urlPatterns = "/sqlTxtDownloadService", loadOnStartup = 1)
public class SqlTxtDownload extends HttpServlet {
    private final static Logger logger = Logger.getLogger(SqlTxtDownload.class);

    //@Resource
    private NewsDao newsDao;
    private NewsService newsService;

    /**
     * todo 加注释
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        newsDao = ContextLoaderListener.getCurrentWebApplicationContext().getBean(NewsDao.class);
        newsService = ContextLoaderListener.getCurrentWebApplicationContext().getBean(NewsService.class);
        req.setCharacterEncoding("ISO8859-1");
        res.setContentType("text/html;charset=utf-8");
        String path = getServletContext().getRealPath("/");
        String cmdcode_update = req.getParameter("cmdcode_update");
        //  newsService.generateSqLiteDbFile(ip,path);
        try {
            List<Object[]> list = newsDao.querryNewsBySql("select dspec_id from cmdcode_update where cmdcode_update='" + cmdcode_update + "'");

            if (list != null && list.size() > 0) {
                Object objArr = list.get(0);
                if (objArr != null && !objArr.toString().equals("")) {
                    path += objArr.toString().trim() + ".db";
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            logger.error("", e);
        }
        // 服务器绝对路径
        //  path=java.net.URLEncoder.encode(path,"utf-8");
        path = new String(path.getBytes("ISO8859-1"), "utf-8");
        // 检查文件是否存在
        File obj = new File(path);
        if (!obj.exists()) {
            //  res.setContentType("text/html;charset=utf-8");
            res.getWriter().print(path);
            res.getWriter().print("指定文件不存在！");
            return;
        }
        // 读取文件名：用于设置客户端保存时指定默认文件名
        int index = path.lastIndexOf("\\"); // 前提：传入的path字符串以“\”表示目录分隔符
        String fileName = path.substring(index + 1);
        // 写流文件到前端浏览器
        ServletOutputStream out = res.getOutputStream();
        res.setHeader("Content-disposition", "attachment;filename=" + fileName);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(path));
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    /**
     * todo 加注释
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
