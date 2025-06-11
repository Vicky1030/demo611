package com.nep.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nep.po.Admin;
import com.nep.po.AqiFeedback;
import com.nep.po.GridMember;
import com.nep.util.FileUtil;
import com.nep.service.GridMemberService;
import com.nep.util.JsonUtil;

import java.io.IOException;
import java.util.List;

public class GridMemberServiceImpl implements GridMemberService {
    @Override
    public GridMember login(String loginCode, String password) throws IOException {
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/JSONData/";

        List<GridMember> glist = (List<GridMember>) JsonUtil.readListFromJson("/NepDatas/JSONData/grid_member.json",new TypeReference<List<GridMember>>() {});
        for(GridMember gm : glist){
            if(gm.getGmCode().equals(loginCode) && gm.getPassword().equals(password)){
                return gm;
            }
        }
        return null;
    }
}
