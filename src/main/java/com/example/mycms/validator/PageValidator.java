package com.example.mycms.validator;

import com.example.mycms.form.PageForm;
import com.example.mycms.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageValidator {

    private PageRepository pageRepository;

    @Autowired
    public PageValidator(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public boolean isAddPageValid(PageForm pageForm) {
        boolean isValid = !pageRepository.getByUrl(pageForm.getUrl()).isPresent();

        return isValid;
    }
}
