package com.lzt.ylu.community.controller;

import com.lzt.ylu.community.dto.AccessTokenDTO;
import com.lzt.ylu.community.dto.GitUser;
import com.lzt.ylu.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${git.redirect.uri}")
    private String redirectUri;
    @Value("${git.client.id}")
    private String clientId;
    @Value("${git.client.secret}")
    private String clientSecret;


    @GetMapping("/callback")
    public String CallBack(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GitUser user = githubProvider.getUser(accessToken);
//        if (user != null) {
//
//        } else {
//            // Login fail
//            return "redirect:/";
//        }
        request.getSession().setAttribute("user", user);
        // Login success
        return "redirect:/";
    }


}
