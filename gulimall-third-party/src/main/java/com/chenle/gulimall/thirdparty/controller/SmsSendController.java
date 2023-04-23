package com.chenle.gulimall.thirdparty.controller;


import com.chenle.common.utils.R;
import com.chenle.gulimall.thirdparty.component.QQMailComponent;
import com.chenle.gulimall.thirdparty.component.SmsComponent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/sms")
public class SmsSendController {

    @Resource
    private QQMailComponent qqMailComponent;

    /**
     * 提供给别的服务进行调用
     * @param phone
     * @param code
     * @return
     */
    @GetMapping(value = "/sendCode")
    public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code) {

        //发送验证码
        qqMailComponent.sendSimpleMail(phone,code);

        return R.ok();
    }

}
