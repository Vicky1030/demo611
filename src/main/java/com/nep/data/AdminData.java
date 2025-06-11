package com.nep.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nep.po.Admin;
import com.nep.util.FileUtil;
import com.nep.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class AdminData {
    public static void main(String[] args) {
        //管理员账号初始化
        Admin a1 = new Admin();
        a1.setAdminId(1001);
        a1.setPassword("111");
        a1.setAdminCode("赵本山");
        Admin a2 = new Admin();
        a2.setAdminId(1002);
        a2.setPassword("222");
        a2.setAdminCode("刘德华");

        List<Admin> alist = new ArrayList<Admin>();
        alist.add(a1);
        alist.add(a2);

        try {
            FileUtil.writeObjectFromClasspath("NepDatas/JSONData/admin.json", alist);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
