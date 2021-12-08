package com.example.mycms.repository;

import com.example.mycms.entity.PageText;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PageTextRepository extends JpaRepository<PageText, Long> {

    Optional<PageText> findByIdentity(String identity);
}
