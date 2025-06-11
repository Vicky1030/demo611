package com.nep.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nep.po.Admin;
import com.nep.po.Aqi;
import com.nep.po.Supervisor;
import com.nep.util.FileUtil;
import com.nep.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class AqiData {
    public static void main(String[] args) {
        List<Supervisor> slist = new ArrayList<Supervisor>();
        Aqi a1 = new Aqi(1, "优","空气质量很好","绿色","空气质量令人满意,基本无空气污染","各类人群可正常活动",0,50,0,2,0,35,"无");
        Aqi a2 = new Aqi(2, "良", "空气质量较好","黄色","空气质量可接受,但某些污染物可能对极少数异常敏感人群健康有较弱的影响","极少数异常敏感人群应减少户外活动", 51,150,2,4,36,75,"无");
        Aqi a3 = new Aqi(3, "轻度污染", "空气质量一般","橙色","易感人群症状有轻度加剧,健康人群出现刺激症状","儿童、老年人及心脏病、呼吸系统疾病患者应减少长时间、高强度的户外锻炼",151,475,4,14,76,115,"无");
        Aqi a4 = new Aqi(4, "中度污染", "空气质量较差","红色","进一步加剧易感人群症状,可能对健康人群心脏、呼吸系统有影响","儿童、老年人及心脏病、呼吸系统疾病患者避免长时间、高强度的户外锻练，一般人群适量减少户外运动",476,800,14,24,116,150,"无");
        Aqi a5 = new Aqi(5, "重度污染", "空气质量很差","紫色","心脏病和肺病患者症状显著加剧,运动耐受力降低,健康人群普遍出现症状","儿童、老年人和心脏病、肺病患者应停留在室内，停止户外运动，一般人群减少户外运动",801,1600,24,36,151,250,"无");
        Aqi a6 = new Aqi(6, "严重污染", "空气质量极差","褐红色","健康人群耐受力降低,有明显强烈症状,提前出现某些疾病","儿童、老年人和病人应当留在室内，避免体力消耗，一般人群应避免户外活动",1600,9999,36,9999,251,9999,"无");
        List<Aqi> alist = new ArrayList<Aqi>();
        alist.add(a1);
        alist.add(a2);
        alist.add(a3);
        alist.add(a4);
        alist.add(a5);
        alist.add(a6);
        String ProPaht = System.getProperty("user.dir") + "/src/main/resources/NepDatas/JSONData/";
        FileUtil.writeObject(ProPaht+"aqi.json", alist);
    }
}
