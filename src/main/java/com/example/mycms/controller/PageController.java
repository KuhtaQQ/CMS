package com.example.mycms.controller;

import com.example.mycms.entity.Page;
import com.example.mycms.form.PageForm;
import com.example.mycms.form.PageTextForm;
import com.example.mycms.repository.PageRepository;
import com.example.mycms.repository.PageTextRepository;
import com.example.mycms.service.PageService;
import com.example.mycms.service.StorageService;
import com.example.mycms.validator.PageTextValidator;
import com.example.mycms.validator.PageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping
public class PageController extends AbstractController {

    private PageService pageService;
    private StorageService storageService;

    private PageRepository pageRepository;
    private PageTextRepository pageTextRepository;

    private PageValidator pageValidator;
    private PageTextValidator pageTextValidator;

    @Autowired
    public PageController(PageService pageService,
                          StorageService storageService,
                          PageRepository pageRepository,
                          PageTextRepository pageTextRepository,
                          PageValidator pageValidator,
                          PageTextValidator pageTextValidator) {
        this.pageService = pageService;
        this.storageService = storageService;
        this.pageRepository = pageRepository;
        this.pageTextRepository = pageTextRepository;
        this.pageValidator = pageValidator;
        this.pageTextValidator = pageTextValidator;
    }

    @GetMapping("/addPage")
    public String addPage(@RequestParam(required = false) Long parentPageId, PageForm pageForm, Model model) {
        model.addAttribute("parentPageId", parentPageId);

        return "addPage";
    }

    @PostMapping("/addPage")
    public String pageForm(@Valid PageForm pageForm,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || !pageValidator.isAddPageValid(pageForm)) {
            return "addPage";
        }
        pageService.savePage(pageForm);

        redirectAttributes.addAttribute("pageUrl", pageForm.getUrl());
        return redirect("page/{pageUrl}");
    }

    @PostMapping("/page/addPageText")
    public String pageTextForm(@Valid PageTextForm pageTextForm,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        Page page = pageRepository.getOne(pageTextForm.getPageId());
        if (bindingResult.hasErrors() || !pageTextValidator.isAddPageTextValid(pageTextForm)) {
            model.addAttribute("page",page);
            return "page";
        }
        pageService.savePageText(pageTextForm);

        redirectAttributes.addAttribute("pageUrl", page.getUrl());
        return redirect("page/{pageUrl}");
    }

    @PostMapping("/page/editPageText")
    public String pageTextEditForm(@RequestParam String content,
                                   @RequestParam String identity,
                                   @RequestParam Long pageId,
                                   RedirectAttributes redirectAttributes) {
        pageService.updatePageText(identity, content);
        Page page = pageRepository.getOne(pageId);

        redirectAttributes.addAttribute("pageUrl", page.getUrl());
        return redirect("page/{pageUrl}");
    }

    @GetMapping("/page/mainMenu")
    public List<Page> findAll(){
        List<Page> pages = pageService.findAll();
        return pages;
    }


    @RequestMapping(value = "/page/{url}")
    public String renderPage(@PathVariable("url") String url, Model model) {
        Optional<Page> pageOptional = pageRepository.getByUrl(url);

        if (pageOptional.isPresent()) {
            Page page = pageOptional.get();

            PageTextForm pageTextForm = new PageTextForm();
            pageTextForm.setPageId(page.getId());

            model.addAttribute("page", pageOptional.get());
            model.addAttribute("pageTextForm", pageTextForm);

            return "page";
        } else {
            return redirect("dashboard");
        }
    }


    @PostMapping("/addPageImage")
    public String addPageImage(@RequestParam("file") MultipartFile file,
                               @RequestParam("identity") String identity,
                               @RequestParam("pageId") Long pageId,
                               RedirectAttributes redirectAttributes) {
        Page page = pageRepository.getOne(pageId);
        storageService.store(file, identity, page);

        redirectAttributes.addAttribute("pageUrl", page.getUrl());
        return redirect("page/{pageUrl}");
    }

}

