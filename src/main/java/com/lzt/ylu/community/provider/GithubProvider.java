package com.lzt.ylu.community.provider;

import com.alibaba.fastjson.JSON;
import com.lzt.ylu.community.dto.AccessTokenDTO;
import com.lzt.ylu.community.dto.GitUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType
                = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            String tmpStr = str.split("&")[0].split("=")[1];
//            System.out.println(tmpStr);
            return tmpStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GitUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.github.com/user?access_token=" + accessToken;
//        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String str = response.body().string();
//            GitUser gitUser = JSON.parseObject(str, GitUser.class);
            System.out.println(str);
            GitUser gitUser1 = JSON.parseObject(str, GitUser.class);
//            System.out.println(gitUser1.getBio() + gitUser1.getId() +gitUser1.getName() + "Json");
            return gitUser1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
