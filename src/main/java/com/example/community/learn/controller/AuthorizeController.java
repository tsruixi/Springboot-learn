package com.example.community.learn.controller;

import com.example.community.learn.dto.AccessTokenDTO;
import com.example.community.learn.dto.GithubUser;
import com.example.community.learn.mapper.UserMapper;
import com.example.community.learn.model.User;
import com.example.community.learn.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Author: ruixi
 * @Date: 2019/12/7 12:07
 */
@Controller

public class AuthorizeController {

        @Autowired
        private GithubProvider githubProvider;
        @Value("${github.client.id}")
        private String clientId;
        @Value("${github.client.secret}")
        private String clientSecret;
        @Value("${github.redirect.uri}")
        private String redirectUri;

        @Autowired
        private UserMapper userMapper;

        @GetMapping("/callback")
        public String callback(@RequestParam(name = "code") String code,
                               @RequestParam(name = "state") String state,
                               HttpServletRequest request){
                AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
                accessTokenDTO.setCode(code);
                accessTokenDTO.setRedirect_uri(redirectUri);
                accessTokenDTO.setState(state);
                accessTokenDTO.setClient_id(clientId);
                accessTokenDTO.setClient_secret(clientSecret);
                String accessToken = githubProvider.getAccessToken(accessTokenDTO);
                GithubUser githubUser = githubProvider.getUser(accessToken);
                if(githubUser != null){
                        User user = new User();
                        user.setToken(UUID.randomUUID().toString());
                        user.setName(githubUser.getName());
                        user.setAccountId(String.valueOf(githubUser.getId()));
                        user.setGmtCreate(System.currentTimeMillis());
                        user.setGmtModified(user.getGmtCreate());
                        userMapper.insert(user);
                        //登录成功
                        request.getSession().setAttribute("user", githubUser);
                        return "redirect:/";
                }else{
                        //登录失败
                        return "redirect:/";
                }
        }
}
