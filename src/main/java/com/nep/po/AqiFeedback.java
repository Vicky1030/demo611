package com.nep.po;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AqiFeedback implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("af_id")
    private Integer afId;

    private String provinceName;
    private String cityName;

    @JsonProperty("tel_id")
    private String telId;

    @JsonProperty("af_name")
    private String afname;

    @JsonProperty("province_id")
    private Integer provinceId;

    @JsonProperty("city_id")
    private Integer cityId;

    private String address;

    private String information;
    private String gmname;

    @JsonProperty("estimated_grade")
    private Integer estimatedGrade;

    @JsonProperty("af_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date afDate;


    @JsonProperty("af_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date afTime;

    @JsonProperty("gm_id")
    private Integer gmId;

    @JsonProperty("assign_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date assignDate;

    @JsonProperty("assign_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private Date assignTime;

    private Integer state;

    private String remarks;

    private Date confirmDate;
    private Double co;
    private Double so2;
    private Double pm;
    private Integer confirmLevel;
    private String confirmExplain;

    public AqiFeedback() {
    }

    public AqiFeedback(Integer afId, String telId, String afname,Integer provinceId, Integer cityId, String address, String information, Integer estimatedGrade, Date afDate, Date afTime, Integer gmId, Date assignDate, Date assignTime, Integer state, String remarks,String gmname) {
        this.afId = afId;
        this.telId = telId;
        this.afname = afname;
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.address = address;
        this.information = information;
        this.estimatedGrade = estimatedGrade;
        this.afDate = afDate;
        this.afTime = afTime;
        this.gmId = gmId;
        this.gmname = gmname;
        this.assignDate = assignDate;
        this.assignTime = assignTime;
        this.state = state;
        this.remarks = remarks;
    }

    public Integer getAfId() {
        return afId;
    }

    public String getAfname() {return afname; }

    public String getGmName() {return gmname; }

    public void setAfId(Integer afId) {
        this.afId = afId;
    }

    public void setAfname(String afname) {
        this.afname = afname;
    }

    public String getTelId() {
        return telId;
    }

    public void setTelId(String telId) {
        this.telId = telId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Integer getEstimatedGrade() {
        return estimatedGrade;
    }

    public void setEstimatedGrade(Integer estimatedGrade) {
        this.estimatedGrade = estimatedGrade;
    }

    public Date getAfDate() {
        return afDate;
    }

    public void setAfDate(Date afDate) {
        this.afDate = afDate;
    }

    public Date getAfTime() {
        return afTime;
    }

    public void setAfTime(Date afTime) {
        this.afTime = afTime;
    }

    public Integer getGmId() {
        return gmId;
    }

    public void setGmId(Integer gmId) {
        this.gmId = gmId;
    }

    public Date getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public Double getCo() {
        return co;
    }

    public void setCo(Double co) {
        this.co = co;
    }

    public Double getSo2() {
        return so2;
    }

    public void setSo2(Double so2) {
        this.so2 = so2;
    }

    public Double getPm() {
        return pm;
    }

    public void setPm(Double pm) {
        this.pm = pm;
    }

    public Integer getConfirmLevel() {
        return confirmLevel;
    }

    public void setConfirmLevel(Integer confirmLevel) {
        this.confirmLevel = confirmLevel;
    }

    public String getConfirmExplain() {
        return confirmExplain;
    }

    public void setConfirmExplain(String confirmExplain) {
        this.confirmExplain = confirmExplain;
    }

    @Override
    public String toString() {
        return "AqiFeedback [afId=" + afId + ", telId=" + telId + ", provinceId=" + provinceId + ", cityId=" + cityId + ", address=" + address + ", information=" + information + ", estimatedGrade=" + estimatedGrade + ", afDate=" + afDate + ", afTime=" + afTime + ", gmId=" + gmId + ", assignDate=" + assignDate + ", assignTime=" + assignTime + ", state=" + state + ", remarks=" + remarks + "]";
    }

    public void setGmname(String realName) {
        this.gmname = gmname;
    }
}