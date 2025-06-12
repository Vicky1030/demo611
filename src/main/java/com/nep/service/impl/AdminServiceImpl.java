package com.nep.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nep.po.Admin;
import com.nep.service.AdminService;
import com.nep.util.JsonUtil;

import java.io.IOException;
import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean login(String loginCode, String password) {
        // TODO Auto-generated method stub
        String ProPaht = System.getProperty("user.dir") + "/NepDatas/JSONData/";
        List<Admin> alist = null;
        try {
            String path = System.getProperty("user.dir") + "/NepDatas/JSONData/admins.json";
            alist = JsonUtil.readListFromFileSystem(path, new TypeReference<List<Admin>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Admin a : alist){
            if(a.getAdminId().equals(loginCode) && a.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
