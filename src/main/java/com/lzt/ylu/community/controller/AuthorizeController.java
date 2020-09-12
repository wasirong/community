package com.lzt.ylu.community.controller;

import com.lzt.ylu.community.dto.AccessTokenDTO;
import com.lzt.ylu.community.dto.GitUser;
import com.lzt.ylu.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String CallBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost:8080/callback");
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id("b5afe4009c1e87fdb580");
        accessTokenDTO.setClient_secret("4ef87f41d5c00ebff38163d5797ce0126f4d5377");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitUser user = githubProvider.getUser(accessToken);
//        System.out.println(user.getName());
        return "index";
    }


}
