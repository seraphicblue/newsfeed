package com.example.activity;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-module", url = "http://localhost:8081")
public interface MemberClient {
 /*   @GetMapping("/members/{memberId}")
    MemberDto getMemberById(@PathVariable(name = "memberId") Long memberId);*/
}
