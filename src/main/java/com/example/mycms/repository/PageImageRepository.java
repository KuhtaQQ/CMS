package com.example.mycms.repository;

import com.example.mycms.entity.PageImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageImageRepository extends JpaRepository<PageImage, Long> {
    Optional<PageImage> getByIdentity(String identity);
}
