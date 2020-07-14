package com.instahost.api.controller;

import com.instahost.api.service.FileStorage;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(FileUploadController.class)
class FileUploadControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IdGenerator idGenerator;

    @MockBean
    private FileStorage fileStorage;

    @SneakyThrows
    @Test
    void postFile() {
        // given
        var id = "12345abc";
        var data = "file data".getBytes();
        given(idGenerator.generate()).willReturn(id);

        // when
        var file = new MockMultipartFile("file", "foo.bar", "text/plain", data);

        var response = mvc.perform(multipart("/api/files").file(file))
                .andDo(print())
                .andReturn()
                .getResponse();

        // then
        assertThat(response.getStatus(), is(HttpStatus.CREATED.value()));
        verify(idGenerator, times(1)).generate();
        verify(fileStorage, times(1)).store(id, data);
    }
}
