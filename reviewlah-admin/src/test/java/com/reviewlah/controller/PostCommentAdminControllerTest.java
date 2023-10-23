package com.reviewlah.controller;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.InsertPostCommentRequest;
import com.reviewlah.controller.form.UpdatePostCommentRequest;
import com.reviewlah.remote.DeletePostCommentByPCId;
import com.reviewlah.remote.InsertPostComment;
import com.reviewlah.remote.PostCommentService;
import com.reviewlah.remote.SelectPostCommentByPostId;
import com.reviewlah.remote.UpdatePostComment;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PostCommentAdminControllerTest {
    @MockBean
    private PostCommentService postCommentService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getPostComment() throws Exception {
        SelectPostCommentByPostId request = Mockito.mock(SelectPostCommentByPostId.class);
        Mockito.when(postCommentService.getPostComment(request)).thenReturn(RCode.ok());
        mockMvc.perform(get("/admin/post/1/comments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void insertPostComment() throws Exception {
        InsertPostComment request = Mockito.mock(InsertPostComment.class);
        Mockito.when(postCommentService.insertPostComment(request)).thenReturn(RCode.ok());
        InsertPostCommentRequest insertPostCommentRequest = new InsertPostCommentRequest();
        insertPostCommentRequest.setContent("1");
        insertPostCommentRequest.setCustomer_id(BigInteger.ONE);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(insertPostCommentRequest);
        mockMvc.perform(post("/admin/post/1/comments").content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deletePostCommentByPCId() throws Exception {
        DeletePostCommentByPCId request = Mockito.mock(DeletePostCommentByPCId.class);
        Mockito.when(postCommentService.deletePostCommentByPCId(request)).thenReturn(RCode.ok());
        mockMvc.perform(delete("/admin/post/comments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updatePostCommentByPCId() throws Exception {
        UpdatePostComment request = Mockito.mock(UpdatePostComment.class);
        Mockito.when(postCommentService.updatePostCommentByPCId(request)).thenReturn(RCode.ok());
        UpdatePostCommentRequest updatePostCommentRequest = new UpdatePostCommentRequest();
        updatePostCommentRequest.setContent("1");
        updatePostCommentRequest.setCustomer_id(BigInteger.ONE);
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(updatePostCommentRequest);
        mockMvc.perform(put("/admin/post/comments/1").content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}