package com.nep.data;
import com.nep.po.GridCity;
import com.nep.po.GridProvince;
import com.nep.util.FileUtil;
import com.nep.util.JsonUtil;

import java.util.ArrayList;
import java.util.List;

public class ProvinceCityData {
    public static void main(String[] args) {
        List<GridProvince> provinceList = new ArrayList<>();
        provinceList.add(new GridProvince(1, "北京市", "京", null));
        provinceList.add(new GridProvince(2, "天津市", "津", null));
        provinceList.add(new GridProvince(3, "河北省", "冀", null));
        provinceList.add(new GridProvince(4, "山西省", "晋", null));
        provinceList.add(new GridProvince(5, "内蒙古", "蒙", null));
        provinceList.add(new GridProvince(6, "辽宁省", "辽", null));
        provinceList.add(new GridProvince(7, "吉林省", "吉", null));
        provinceList.add(new GridProvince(8, "黑龙江省", "黑", null));
        provinceList.add(new GridProvince(9, "上海市", "沪", null));
        provinceList.add(new GridProvince(10, "江苏省", "苏", null));
        provinceList.add(new GridProvince(11, "浙江省", "浙", null));
        provinceList.add(new GridProvince(12, "安徽省", "皖", null));
        provinceList.add(new GridProvince(13, "福建省", "闽", null));
        provinceList.add(new GridProvince(14, "江西省", "赣", null));
        provinceList.add(new GridProvince(15, "山东省", "鲁", null));
        provinceList.add(new GridProvince(16, "河南省", "豫", null));

        List<GridCity> cityList = new ArrayList<>();
        cityList.add(new GridCity(1, "北京市", 1,"北京市" ,null));
        cityList.add(new GridCity(2, "天津市", 2,"天津市", null));
        cityList.add(new GridCity(3, "石家庄市", 3, "河北省",null));
        cityList.add(new GridCity(4, "太原市", 4, "山西省", null));
        cityList.add(new GridCity(5, "呼和浩特市", 5, "内蒙古", null));
        cityList.add(new GridCity(6, "沈阳市", 6, "辽宁省",null));
        cityList.add(new GridCity(17, "大连市", 6,"辽宁省" ,null));
        cityList.add(new GridCity(7, "长春市", 7,"吉林省", null));
        cityList.add(new GridCity(8, "哈尔滨市", 8,"黑龙江省", null));
        cityList.add(new GridCity(9, "上海市", 9, "上海市", null));
        cityList.add(new GridCity(10, "南京市", 10, "江苏省",null));
        cityList.add(new GridCity(11, "杭州市", 11,"浙江省", null));
        cityList.add(new GridCity(12, "合肥市", 12, "安徽省", null));
        cityList.add(new GridCity(13, "福州市", 13,"福建省", null));
        cityList.add(new GridCity(14, "南昌市", 14,"江西省", null));
        cityList.add(new GridCity(15, "济南市", 15,"山东省", null));
        cityList.add(new GridCity(16, "郑州市", 16, "河南省", null));

        try {
            FileUtil.writeObjectFromClasspath("NepDatas/JSONData/grid_city.json", cityList);
            FileUtil.writeObjectFromClasspath("NepDatas/JSONData/grid_province.json", provinceList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
