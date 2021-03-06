package com.instahost.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instahost.api.dto.UploadFileResult;
import com.instahost.api.service.FileStorage;
import com.instahost.api.service.IdGenerator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileController.class)
class FileControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private IdGenerator idGenerator;

    @MockBean
    private FileStorage fileStorage;

    private JacksonTester<UploadFileResult> jsonResult;

    @BeforeEach
    void beforeEach() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @SneakyThrows
    @Test
    void getFile() {
        // given
        final String id = "12345abc";
        final String file = "file data";
        given(fileStorage.retrieve(id)).willReturn(file);

        // when
        mvc.perform(get("/api/files/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(file));

        // then
        verify(fileStorage, times(1)).retrieve(id);
    }

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
        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
        assertThat(response.getContentAsString(), is(jsonResult.write(new UploadFileResult(id)).getJson()));
        verify(idGenerator, times(1)).generate();
        verify(fileStorage, times(1)).store(id, data);
    }
}
