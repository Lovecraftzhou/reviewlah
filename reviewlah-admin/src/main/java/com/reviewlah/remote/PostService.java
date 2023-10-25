package com.reviewlah.remote;

import com.reviewlah.common.util.RCode;
import com.reviewlah.controller.form.DeletePostRequest;
import com.reviewlah.controller.form.InsertPostRequest;
import com.reviewlah.controller.form.SelectPostByCustomerIdRequest;
import com.reviewlah.controller.form.SelectPostByPostIdRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("customer")
public interface PostService {
    @PostMapping({"customer/post/insert"})
    RCode insertPost(@RequestBody InsertPostRequest request);
    @PostMapping({"customer/post/delete"})
    RCode deletePost(@RequestBody DeletePostRequest request);
    @PostMapping({"customer/post/mypost"})
    RCode selectPostByCustomerId(@RequestBody SelectPostByCustomerIdRequest request);
    @PostMapping({"customer/post/detail"})
    RCode selectPostByPostId(@RequestBody SelectPostByPostIdRequest request);

}
