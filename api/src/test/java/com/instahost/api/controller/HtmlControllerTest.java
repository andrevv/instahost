package com.instahost.api.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HtmlController.class)
class HtmlControllerTest {

    @Autowired
    private MockMvc mvc;

    @SneakyThrows
    @Test
    void getHtml() {
        // given
        final String id = "12345abc";

        // when
        mvc.perform(get("/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(id));

        // then
    }
}