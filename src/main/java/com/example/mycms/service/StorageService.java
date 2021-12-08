package com.example.mycms.service;

import com.example.mycms.entity.Page;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void store(MultipartFile file, String identity, Page page);


    Resource loadAsResource(String fileName);
}
