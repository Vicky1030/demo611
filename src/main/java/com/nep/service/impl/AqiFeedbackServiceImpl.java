package com.nep.service.impl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.nep.po.AqiFeedback;
import com.nep.util.FileUtil;
import com.nep.service.AqiFeedbackService;
import com.nep.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AqiFeedbackServiceImpl implements AqiFeedbackService {


// 在当前类中定义自己的logger
private static final Logger logger = LoggerFactory.getLogger(AqiFeedbackServiceImpl.class);

    private static final String FEEDBACK_FILE_PATH = "D:/neusoft/demo611/demo611/NepDatas/JSONData/aqi_feedback.json";


    @Override
    public boolean saveFeedBack(AqiFeedback af) {
        try {
            List<AqiFeedback> afList = JsonUtil.readListFromFileSystem(
                    FEEDBACK_FILE_PATH,
                    new TypeReference<List<AqiFeedback>>() {}
            );

            if (afList == null) {
                afList = new ArrayList<>();
            }

            afList.add(af);
            JsonUtil.writeListToJson(afList, FEEDBACK_FILE_PATH);

            return true;
        } catch (IOException e) {
            logger.error("保存AQI反馈失败", e);
            return false;
        }
    }

    @Override
    public void assignGridMember(String afId, String realName) {
        String ProPaht = System.getProperty("user.dir") + "/NepDatas/JSONData/";

        try {
            // 读取 JSON 文件
            List<AqiFeedback> alist = JsonUtil.readListFromFileSystem(
                    ProPaht + "aqi_feedback.json",
                    new TypeReference<List<AqiFeedback>>() {}
            );

            // 修改对应记录
            for (AqiFeedback af : alist) {
                if (af.getAfId().toString().equals(afId)) {
                    af.setGmname(realName);
                    af.setState(2); // 设置为“已指派”
                    break;
                }
            }

            // 写回 JSON 文件
            JsonUtil.writeListToJson(alist, ProPaht + "aqi_feedback.json");

        } catch (IOException e) {
            e.printStackTrace(); // 或写日志
            throw new RuntimeException("写入 JSON 文件失败: " + e.getMessage());
        }
    }



    @Override
    public void confirmData(AqiFeedback afb) {
        // TODO Auto-generated method stub
        System.out.println(afb.getAfId());
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/JSONData/";
        List<AqiFeedback> afList = null;
        try {
            afList = (List<AqiFeedback>) JsonUtil.readListFromFileSystem("/NepDatas/JSONData/aqi_feedback.json",new TypeReference<List<AqiFeedback>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(int i = 0; i< afList.size();i++){
            AqiFeedback a = afList.get(i);
            if(a.getGmName() != null && a.getGmName().equals(afb.getGmName()) && a.getAfId().intValue()==afb.getAfId().intValue()){
                a.setState(afb.getState());
                a.setConfirmDate(afb.getConfirmDate());
                a.setCo(afb.getCo());
                a.setSo2(afb.getSo2());
                a.setPm(afb.getPm());
                a.setConfirmLevel(afb.getConfirmLevel());
                a.setConfirmExplain(afb.getConfirmExplain());
                break;
            }
        }
        FileUtil.writeObject(ProPaht+"aqi_feedback.json", afList);
    }
}
