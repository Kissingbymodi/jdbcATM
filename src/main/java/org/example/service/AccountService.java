package org.example.service;

import org.example.model.Account;

public interface AccountService {

    //登录
    public Account Login(String tel, String password);

    //取款
    public boolean updateMoney(String tel, double money);

    //存款
    public boolean updateMoneyCun(String tel, double balance);

    //查阅账户
    public Account findAccount(String tel);

    //修改密码
    public boolean updatePassword(String password,String tel);

    //注册
    public boolean reg(Account a);

    //手机号是否被注册过
    public Account findByTel(String tel);

    //账户号是否被注册过
    public Account findByCardNumber(String cardNumber);

    //求取余额
    public Account findByBalance(String tel);
    //转账


}
