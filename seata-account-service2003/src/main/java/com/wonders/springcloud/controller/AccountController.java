package com.wonders.springcloud.controller;

import com.wonders.springcloud.common.entity.CommonResult;
import com.wonders.springcloud.service.AccountService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @author qiu
 * @date 2020-07-16 14:50
 */
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId,money);
        return  CommonResult.success("扣减账户余额成功！");
    }
}
