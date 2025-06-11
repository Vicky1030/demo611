package com.nep.service;


public interface AdminService {
    /**
     * 管理员登录
     * @param loginCode
     * @param password
     * @return
     */
    public boolean login(int loginCode,String password);
}
