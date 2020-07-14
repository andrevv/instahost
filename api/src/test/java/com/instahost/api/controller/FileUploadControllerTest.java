package com.instahost.api.controller;

import com.instahost.api.service.IdGenerator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(FileUploadController.class)
class FileUploadControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IdGenerator idGenerator;

    @SneakyThrows
    @Test
    void postFile() {
        // given
        var id = "12345abc";
        given(idGenerator.generate()).willReturn(id);

        // when
        var file = new MockMultipartFile("file", "foo.bar", "text/plain", "body".getBytes());

        var response = mvc.perform(multipart("/api/files").file(file))
                .andDo(print())
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus(), is(HttpStatus.CREATED.value()));
    }
}
