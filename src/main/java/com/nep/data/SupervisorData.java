package com.nep.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nep.po.Supervisor;
import com.nep.util.JsonUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SupervisorData {
    public static void main(String[] args) {
        //管理员账号初始化
        Supervisor s1 = new Supervisor("13812345678","123","张三",1,"无");
        Supervisor s2 = new Supervisor("13811111111","123","李四",0,"无");
        Supervisor s3 = new Supervisor("13812341234","123","王五",1,"无");

        List<Supervisor> alist = new ArrayList<Supervisor>();
        alist.add(s1);
        alist.add(s2);
        alist.add(s3);
        try (InputStream inputStream = SupervisorData.class.getClassLoader().getResourceAsStream("NepDatas/JSONData/supervisor.json")) {
            if (inputStream != null) {
                JsonUtil.readListFromJson(inputStream, new TypeReference<List<Supervisor>>() {});
            } else {
                System.err.println("未找到资源文件: NepDatas/JSONData/supervisor.json");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
