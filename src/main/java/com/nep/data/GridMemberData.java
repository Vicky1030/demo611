package com.nep.data;

import com.nep.po.GridMember;
import com.nep.util.FileUtil;
import com.nep.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class GridMemberData {
    public static void main(String[] args) {
        //管网格员账号初始化
        com.nep.po.GridMember gm1 = new com.nep.po.GridMember();
        gm1.setGmCode("2024001");
        gm1.setPassword("111");
        gm1.setGmName("郭晓川");
        gm1.setTel("13888888888");
        gm1.setState(1);
        com.nep.po.GridMember gm2 = new com.nep.po.GridMember();
        gm2.setGmCode("2024002");
        gm2.setPassword("222");
        gm2.setGmName("韩德君");
        gm2.setTel("13555555555");
        gm2.setState(1);
        com.nep.po.GridMember gm3 = new com.nep.po.GridMember();
        gm3.setGmCode("2024003");
        gm3.setPassword("333");
        gm3.setGmName("李晓旭");
        gm3.setTel("13444444444");
        gm3.setState(1);
        com.nep.po.GridMember gm4 = new com.nep.po.GridMember();
        gm4.setGmCode("2024004");
        gm4.setPassword("444");
        gm4.setGmName("赵继伟");
        gm4.setTel("13222222222");
        gm4.setState(0);
        com.nep.po.GridMember gm5 = new com.nep.po.GridMember();
        gm5.setGmCode("2024005");
        gm5.setPassword("555");
        gm5.setGmName("易建联");
        gm5.setTel("13666666666");
        gm5.setState(1);
        List<com.nep.po.GridMember> glist = new ArrayList<GridMember>();
        glist.add(gm1);
        glist.add(gm2);
        glist.add(gm3);
        glist.add(gm4);
        glist.add(gm5);
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/JSONData/";
        FileUtil.writeObject(ProPaht+"gridmember.json", glist);
    }
}
