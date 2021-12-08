package com.example.mycms.service;

import com.example.mycms.entity.Page;
import com.example.mycms.form.PageForm;
import com.example.mycms.form.PageTextForm;

import javax.validation.constraints.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PageService {

    void savePage(PageForm pageForm);

    void savePageText(PageTextForm pageTextForm);

    List<Page> findAll();

    void updatePageText(@NotNull String identity, @NotNull String content);

    Map<String, String> createPageTextsMap(Optional<Page> pageOptional);

    Map<String,String> createPageImagesMap(Optional<Page> pageOpt);
}
