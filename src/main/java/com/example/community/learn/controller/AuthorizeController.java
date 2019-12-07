package com.example.community.learn.controller;

import com.example.community.learn.dto.AccessTokenDTO;
import com.example.community.learn.dto.GithubUser;
import com.example.community.learn.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: ruixi
 * @Date: 2019/12/7 12:07
 */
@Controller
public class AuthorizeController {

        @Autowired
        private GithubProvider githubProvider;

        @GetMapping("/callback")
        public String callback(@RequestParam(name = "code") String code,
                                        @RequestParam(name = "state") String state){
                AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
                accessTokenDTO.setCode(code);
                accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
                accessTokenDTO.setState(state);
                accessTokenDTO.setClient_id("d79d425f7cd90573aaa6");
                accessTokenDTO.setClient_secret("4bc6e646cdc220e0b29683f911768a58682c86bc");
                String accessToken = githubProvider.getAccessToken(accessTokenDTO);
                GithubUser user = githubProvider.getUser(accessToken);
                System.out.println(user.getName());
                return "index";
        }
}
