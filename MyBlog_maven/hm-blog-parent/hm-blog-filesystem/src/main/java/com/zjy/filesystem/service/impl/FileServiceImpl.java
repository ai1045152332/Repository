package com.zjy.filesystem.service.impl;

import com.zjy.filesystem.domain.UploadFile;
import com.zjy.filesystem.repository.FileRepository;
import com.zjy.filesystem.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * File 服务.
 * 
 * @since 1.0.0 2017年7月30日
 * @author <a href="https://zjy.com">zhaojianyu</a>
 */
@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	public FileRepository fileRepository;

	@Override
	public UploadFile saveFile(UploadFile file) {
		return fileRepository.save(file);
	}

	@Override
	public void removeFile(String id) {
		fileRepository.deleteById(id);
	}

	@Override
	public Optional<UploadFile> getFileById(String id) {
		return fileRepository.findById(id);
	}

	@Override
	public List<UploadFile> listFilesByPage(int pageIndex, int pageSize) {
		Page<UploadFile> page = null;
		List<UploadFile> list = null;
		
		Sort sort = new Sort(Direction.DESC,"uploadDate");
		Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);
		
		page = fileRepository.findAll(pageable);
		list = page.getContent();
		return list;
	}
}
