package com.lzt.ylu.community.controller;

import com.lzt.ylu.community.dto.AccessTokenDTO;
import com.lzt.ylu.community.dto.GitUser;
import com.lzt.ylu.community.mapper.UserMapper;
import com.lzt.ylu.community.provider.GithubProvider;
import com.lzt.ylu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

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

    @Autowired(required = false)
    private UserMapper userMapper;

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
        GitUser gitUser = githubProvider.getUser(accessToken);
//        if (user != null) {
//
//        } else {
//            // Login fail
//            return "redirect:/";
//        }
        if (gitUser != null) {
            //用户数据库存入
            System.out.println("存入database");
            User user = new User();
            user.setName(gitUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAccountId(String.valueOf(gitUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModify(user.getGmtCreate());
            userMapper.insert(user);
        }
        request.getSession().setAttribute("user", gitUser);
        // Login success
        return "redirect:/";
    }


}
