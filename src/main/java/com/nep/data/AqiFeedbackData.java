package com.nep.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nep.po.Admin;
import com.nep.po.AqiFeedback;
import com.nep.util.FileUtil;
import com.nep.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class AqiFeedbackData {
    public static void main(String[] args) {
        List<AqiFeedback> afList = new ArrayList<AqiFeedback>();

        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/JSONData/";
        FileUtil.writeObject(ProPaht+"aqifeedback.json", afList);

    }
}

