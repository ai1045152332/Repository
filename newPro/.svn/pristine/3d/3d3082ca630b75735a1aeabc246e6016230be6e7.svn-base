package com.honghe.recordhibernate.dao;

import com.honghe.recordhibernate.entity.Video;

import java.util.List;

/**
 * Created by lichunming on 2015/3/24.
 */
public interface VideoDao {
    //保存文件信息
    public int saveVideo(Video video) throws Exception;
    //删除文件信息
    public void delVideo(int id) throws Exception;
    //修改文件信息
    public boolean updateVideo(Video video) throws Exception;
    //点击上传修改文件状态
    public void updateVideoUpload(String videoId,int videoUpload) throws Exception;
    //初始化所有未上传完成文件的状态
    public void initialVideoUpload() throws Exception;
    //根据条件获取文件列表
    public List<Object[]> getVideoListFromSql(final int ResourceId, String condition, final int userId) throws Exception;
    //根据分类ID获取video
    public List getVideoByRid(int rid) throws Exception;

    public Video getVideoInfoByRid(int rId) throws Exception;

    public void delVideoByRid(int rid) throws Exception;
    /**
     * 统计当前有多少文件     */

    public int countVideo() throws Exception;

    public int countUploadNumber() throws Exception;

    public void getVideoListByPageSql(Page page, int rid,String order) throws Exception;

    public Video getVideo(int res_id) throws Exception;
    /**
     * 根据id获取资源的详细信息
     */
    //public Video getVideoInfoById(int rId) throws Exception;
}

