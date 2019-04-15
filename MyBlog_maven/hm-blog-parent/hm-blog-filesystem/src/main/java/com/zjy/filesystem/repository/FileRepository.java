package com.zjy.filesystem.repository;

import com.zjy.filesystem.domain.UploadFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FileRepository extends MongoRepository<UploadFile, String> {
}
