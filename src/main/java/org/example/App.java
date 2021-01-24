package org.example;

import org.example.model.Account;
import org.example.service.AccountService;
import org.example.service.impl.AccountServiceImpl;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        AccountService accountService = new AccountServiceImpl();
        System.out.println("欢迎来到XXXATM");
        while(true) {
            System.out.println("请选择操作: 1 登录; 2 注册");
            int choose = scanner.nextInt();
            boolean flag = false;
            while (true) {
                if (choose == 1) {
                    System.out.println("请输入账号, 密码");
                    String username = scanner.next();
                    String password = scanner.next();
                    Account login = accountService.Login(username, password);
                    if (login != null) {
                        System.out.println("登录成功!");
                        while (true) {
                            System.out.println("请选择操作: 1 取款 2 存款 3 查询 4 退出");
                            int opt = scanner.nextInt();
                            if (opt == 1) {
                                System.out.println("请输入取款金额为100的倍数");
                                int quMoney = scanner.nextInt();
                                if (quMoney % 100 == 0) {
/*
                            System.out.println("请输入取款手机号");
*/
                                    if (accountService.updateMoney(login.getTel(), quMoney)) {
                                        System.out.println("取款成功");
                                    } else {
                                        System.out.println("取款失败");
                                    }
                                } else {
                                    System.out.println("输入的金额不正确");
                                }
                            }
                            if (opt == 2) {
                                System.out.println("请输入存款金额为100的倍数");
                                int cunuMoney = scanner.nextInt();
                                if (cunuMoney % 100 == 0) {
                                    /*System.out.println("请输入存款手机号");*/
                                    if (accountService.updateMoneyCun(login.getTel(), cunuMoney)) {
                                        System.out.println("存款成功");
                                    } else {
                                        System.out.println("存款失败");
                                    }
                                } else {
                                    System.out.println("输入的金额不正确");
                                }
                            }
                            if (opt == 3) {
                                /*System.out.println("请输入查找的电话");*/
//                        String tel = scanner.next();
                                if (accountService.findAccount(login.getTel()) != null) {
                                    System.out.println(accountService.findAccount(login.getTel()));
                                } else {
                                    System.out.println("查找的电话不存在");
                                }
                            }
                            if (opt == 4) {
                                flag = true;
                                System.out.println("已退出欢迎使用");
                                break;
                            }
                        }
                        if (flag) break;
                    } else {
                        System.out.println("账号或密码错误,请重新输入");
                    }
                }
                if (choose == 2) {
                    System.out.println("请输入要注册的手机号");
                    String tel = scanner.next();
                    if (accountService.findByTel(tel) != null) {
                        System.out.println("改手机号已经被 注册了");
                    } else {
                        System.out.println("请输入姓名,密码");
                        Account account = new Account();
                        account.setName(scanner.next());
                        account.setPassword(scanner.next());
                        account.setTel(tel);
                        StringBuilder stringBuilder = new StringBuilder();
                        while(true) {
                            for (int i = 0; i < 8; i++) {
                                int num = (int) (Math.random() * 10);
                                stringBuilder.append(num);
                            }
                            String cardNumber = stringBuilder.toString();
                            if(accountService.findByCardNumber(cardNumber) == null) {
                                account.setCardNumber(cardNumber);
                                break;
                            }
                        }
                        if(accountService.reg(account)) {
                            System.out.println("注册成功");
                            break;
                        } else {
                            System.out.println("注册失败");
                        }
                    }
                }
            }
            if(flag)break;
        }

    }
}