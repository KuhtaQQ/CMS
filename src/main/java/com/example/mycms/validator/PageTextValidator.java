package com.example.mycms.validator;

import com.example.mycms.form.PageTextForm;
import com.example.mycms.repository.PageTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageTextValidator {
    private PageTextRepository pageTextRepository;

    @Autowired
    public PageTextValidator(PageTextRepository pageTextRepository) {
        this.pageTextRepository = pageTextRepository;
    }

    /**
     * @param pageTextForm form object
     * @return true if page text can be added
     */
    public boolean isAddPageTextValid(PageTextForm pageTextForm) {
        boolean isValid = !pageTextRepository.findByIdentity(pageTextForm.getIdentity()).isPresent();

        return isValid;
    }
}
