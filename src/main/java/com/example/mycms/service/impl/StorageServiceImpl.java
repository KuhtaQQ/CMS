package com.example.mycms.service.impl;

import com.example.mycms.entity.Page;
import com.example.mycms.entity.PageImage;
import com.example.mycms.repository.PageImageRepository;
import com.example.mycms.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Value("${upload.work-dir}")
    private String uploadWorkdir;

    private static final String WORK_IMAGE = "image";

    private Path uploadPath;

    private PageImageRepository pageImageRepository;

    @Autowired
    public StorageServiceImpl(PageImageRepository pageImageRepository) {
        this.pageImageRepository = pageImageRepository;
    }

    @PostConstruct
    private void init() {
        this.uploadPath = Paths.get(uploadWorkdir).resolve(WORK_IMAGE);
    }

    @Override
    public void store(MultipartFile file, String identity, Page page) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Optional<PageImage> pageImageOpt = pageImageRepository.getByIdentity(identity);

        PageImage pageImage = pageImageOpt.orElseGet(PageImage::new);
        pageImage.setFileName(fileName);
        pageImage.setIdentity(identity);
        pageImage.setPage(page);

        pageImageRepository.save(pageImage);

        if (file.isEmpty() || fileName.contains("..")) {
        }

        try {
            Files.copy(file.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            log.info("file with name {} was stored", fileName);
        } catch (IOException e) {
            log.error("file was not stored", e);
        }

    }

    @Override
    public Resource loadAsResource(String fileName) {
        try {
            Path file = uploadPath.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());

            return resource;
        } catch (MalformedURLException e) {
            log.error("resource was not loaded", e);
        }
        return null;
    }
}
