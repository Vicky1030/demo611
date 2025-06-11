package com.nep.po;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GridCity implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("city_id")
    private Integer cityId;

    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("province_id")
    private Integer provinceId;

    @JsonProperty("province_name")
    private String provinceName;

    private String remarks;
    private List<String> cityNames;  // 注意改为复数形式更符合语义

    public GridCity() {
    }

    public GridCity(Integer cityId, String cityName, Integer provinceId, String provinceName,String remarks) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.remarks = remarks;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public void setCityNames(List<String> cityNames) {
        this.cityNames = cityNames;
    }


    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getProvinceId() {
        return provinceId;
    }
    public String getProvinceName() {
        return this.provinceName;
    }
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }
    public List<String> getCityNames() {  // 修改方法名和返回类型
        return cityNames;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "GridCity [cityId=" + cityId + ", cityName=" + cityName + ", provinceId=" + provinceId + ", remarks=" + remarks + "]";
    }
}
