package com.instahost.api.controller;

import com.instahost.api.service.FileStorage;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HtmlController {

    private final FileStorage fileStorage;

    public HtmlController(FileStorage fileStorage) {
        this.fileStorage = fileStorage;
    }

    @SneakyThrows
    @GetMapping(value = "/{id}", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String getHtml(@PathVariable String id) {
        return fileStorage.retrieve(id);
    }
}
