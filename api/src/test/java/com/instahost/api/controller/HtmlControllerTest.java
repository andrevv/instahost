package com.instahost.api.controller;

import com.instahost.api.service.FileStorage;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HtmlController.class)
class HtmlControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FileStorage fileStorage;

    @SneakyThrows
    @Test
    void getHtml() {
        // given
        final String id = "12345abc";
        final String file = "file data";
        given(fileStorage.retrieve(id)).willReturn(file);

        // when
        mvc.perform(get("/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(file));

        // then
        verify(fileStorage, times(1)).retrieve(id);
    }
}