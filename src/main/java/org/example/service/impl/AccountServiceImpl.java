package org.example.service.impl;

import org.example.dao.AccountDao;
import org.example.model.Account;
import org.example.service.AccountService;

public class AccountServiceImpl implements AccountService {
    AccountDao accountDao = new AccountDao();
    ;

    @Override
    public Account Login(String tel, String password) {
        String sql = "select id,name,tel,cardNumber,password,cardType,balance,addtime from account where name=? and password=?";
        if (accountDao.find(sql, tel, password).size() != 0) {
            return accountDao.find(sql, tel, password).get(0);
        }
        return null;
    }

    @Override
    public boolean updateMoney(String tel, double balance) {
        double balance2 = 0;
        if (findByBalance(tel) != null) {
            balance2 = findByBalance(tel).getBalance();
        }
        if (balance2 - balance >= 0) {
            String sql = "update account set balance=? where tel=?";
            return accountDao.query(sql, balance2 - balance, tel);
        } else {
            System.out.println("余额不足");
            return false;
        }
    }

    @Override
    public boolean updateMoneyCun(String tel, double balance) {
        double balance2 = 0;
        if (findByBalance(tel) != null) {
            balance2 = findByBalance(tel).getBalance();
        }

        String sql = "update account set balance=? where tel=?";
        return accountDao.query(sql, balance2 + balance, tel);
    }

    @Override
    public Account findAccount(String tel) {
        String sql = "select id,name,tel,cardNumber,password,cardType,balance,addtime from account where tel=?";
        if (accountDao.find(sql, tel).size() != 0) {
            return accountDao.find(sql, tel).get(0);
        }
        return null;
    }

    @Override
    public boolean updatePassword(String password, String tel) {
        return false;
    }

    @Override
    public boolean reg(Account a) {
        String sql = "insert into account(name,tel,cardNumber,password) values(?,?,?,?)";
        return accountDao.query(sql, a.getName(),a.getTel(),a.getCardNumber(),a.getPassword());
    }

    @Override
    public Account findByTel(String tel) {
        String sql = "select id,name,tel,cardNumber,password,cardType,balance,addtime from account where tel=?";
        if (accountDao.find(sql, tel).size() != 0) {
            return accountDao.find(sql, tel).get(0);
        }
        return null;
    }

    @Override
    public Account findByCardNumber(String cardNumber) {
        String sql = "select id,name,tel,cardNumber,password,cardType,balance,addtime from account where cardNumber=?";
        if (accountDao.find(sql, cardNumber).size() != 0) {
            return accountDao.find(sql, cardNumber).get(0);
        }
        return null;
    }

    @Override
    public Account findByBalance(String tel) {
        String sql = "select id,name,tel,cardNumber,password,cardType,balance,addtime from account where tel=?";
        if (accountDao.find(sql, tel).size() != 0) {
            return accountDao.find(sql, tel).get(0);
        }
        return null;
    }
}
