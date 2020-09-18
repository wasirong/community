package com.lzt.ylu.community.controller;

import com.lzt.ylu.community.mapper.QuestionMapper;
import com.lzt.ylu.community.model.Question;
import com.lzt.ylu.community.model.User;
import com.lzt.ylu.community.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {


    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/postPublish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest httpServletRequest,
            Model model
    ) {
        Question question = new Question();
        question.setDescription(description);
        question.setTag(tag);
        question.setTitle(title);
        System.out.println(question);
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null){
            System.out.println("please Login");
            model.addAttribute("error", "please Login");
            return "publish";
        }
        System.out.println(user);
        question.setCreateUserId(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModify(question.getGmtCreate());
        questionMapper.create(question);
        return "publish";
    }
}
