package com.nep.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nep.po.Admin;
import com.nep.po.AqiFeedback;
import com.nep.util.FileUtil;
import com.nep.service.AdminService;
import com.nep.util.JsonUtil;

import java.util.List;

public class AdminServiceImpl implements AdminService {
    @Override
    public boolean login(int loginCode, String password) {
        // TODO Auto-generated method stub
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/JSONData/";
        List<Admin> alist =(List<Admin>) JsonUtil.readListFromJson("/NepDatas/JSONData/admins.json",new TypeReference<List<Admin>>() {});
        for(Admin a : alist){
            if(a.getAdminId()==loginCode && a.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
}
