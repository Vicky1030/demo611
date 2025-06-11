package com.nep.service;

import com.nep.po.GridMember;

import java.io.IOException;

public interface GridMemberService {
    /**
     * 网格员登录
     * @param loginCode
     * @param password
     * @return
     */
    public GridMember login(String loginCode, String password) throws IOException;
}
