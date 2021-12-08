package com.example.mycms.repository;

import com.example.mycms.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    Optional<Page> getByUrl(String url);

    List<Page> findByParentPage(Page parentPage);
}
