package com.example.mycms.controller;

public class AbstractController {

    public String redirect(String url) {
        return "redirect:" + url;
    }
}
