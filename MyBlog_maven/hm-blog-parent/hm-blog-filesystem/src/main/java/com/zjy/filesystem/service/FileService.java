/**
 * 
 */
package com.zjy.filesystem.service;

import com.zjy.filesystem.domain.UploadFile;

import java.util.List;
import java.util.Optional;

/**
 * File 服务接口.
 * 
 * @since 1.0.0 2017年3月28日
 * @author <a href="https://zjy.com">zhaojianyu</a>
 */
public interface FileService {
	/**
	 * 保存文件
	 * @param file
	 * @return
	 */
	UploadFile saveFile(UploadFile file);
	
	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	void removeFile(String id);
	
	/**
	 * 根据id获取文件
	 * @param id
	 * @return
	 */
	Optional<UploadFile> getFileById(String id);

	/**
	 * 分页查询，按上传时间降序
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<UploadFile> listFilesByPage(int pageIndex, int pageSize);
}
