package com.nep.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.nep.po.AqiFeedback;
import com.nep.po.GridMember;
import com.nep.util.JsonUtil;
import com.nep.util.JavafxUtil;
import com.nep.service.AqiFeedbackService;
import com.nep.service.impl.AqiFeedbackServiceImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class NepmAqiAssignViewController implements Initializable {
    @FXML
    private Pane txt_pane1;
    @FXML
    private Pane txt_pane2;
    @FXML
    private Pane txt_pane3;
    @FXML
    private TextField txt_afId;
    @FXML
    private Label label_afId;
    @FXML
    private Label label_afName;
    @FXML
    private Label label_proviceName;
    @FXML
    private Label label_cityName;
    @FXML
    private Label label_address;
    @FXML
    private Label label_infomation;
    @FXML
    private Label label_estimateGrade;
    @FXML
    private Label label_date;
    @FXML
    private ComboBox<String> combo_realName;

    public static Stage aqiInfoStage;

    private AqiFeedbackService aqiFeedbackService = new AqiFeedbackServiceImpl();

    // 维护名字到ID的映射
    private Map<String, String> nameToIdMap = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 初始化面板样式
        txt_pane1.setStyle("-fx-border-color: #CCC;");
        txt_pane2.setStyle("-fx-background-color: #CCC;");
        txt_pane3.setStyle("-fx-border-color: #CCC;");

        initConroller();

        // 加载网格员，填充comboBox和映射
        try (InputStream inputStream = new FileInputStream("D:/neusoft/demo611/demo611/NepDatas/JSONData/grid_member.json")) {
            List<GridMember> glist = JsonUtil.readListFromJson(inputStream, new TypeReference<List<GridMember>>() {});
            for (GridMember gm : glist) {
                if (gm.getState() == 0) { // 可用网格员
                    combo_realName.getItems().add(gm.getGmName());
                    nameToIdMap.put(gm.getGmName(), gm.getGmId().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询反馈详情
    public void queryFeedback() {
        String afId = txt_afId.getText();
        String filePath = "D:/neusoft/demo611/demo611/NepDatas/JSONData/aqi_feedback.json";

        try (InputStream inputStream = new FileInputStream(filePath)) {
            List<AqiFeedback> alist = JsonUtil.readListFromJson(inputStream, new TypeReference<List<AqiFeedback>>() {});

            boolean found = false;
            for (AqiFeedback af : alist) {
                if (af.getAfId().toString().equals(afId) && af.getState() == 1) {
                    label_afId.setText(String.valueOf(af.getAfId()));
                    label_afName.setText(af.getAfname());
                    label_address.setText(af.getAddress());
                    label_cityName.setText(af.getCityName());

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    label_date.setText(sdf.format(af.getAfDate()));

                    label_estimateGrade.setText(af.getEstimatedGrade());
                    label_infomation.setText(af.getInformation());
                    label_proviceName.setText(af.getProvinceName());

                    found = true;
                    break;
                }
            }

            if (!found) {
                JavafxUtil.showAlert(aqiInfoStage, "查询失败", "未找到当前编号反馈信息", "请重新输入AQI反馈数据编号", "warn");
                initConroller();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 指派给网格员
    public void assignGridMember(){
        if(label_afId.getText().equals("无")){
            JavafxUtil.showAlert(aqiInfoStage, "指派失败", "未找到要指派的反馈信息", "请选择要指派的反馈信息","warn");
            return;
        }
        if(combo_realName.getValue() == null || combo_realName.getValue().equals("请选择网格员")){
            JavafxUtil.showAlert(aqiInfoStage, "指派失败", "您没有选择要指派的网格员", "请选择您要指派的网格员","warn");
            return;
        }
        String afId = label_afId.getText();
        String selectedName = combo_realName.getValue();
        String selectedId = nameToIdMap.get(selectedName);
        if(selectedId == null){
            JavafxUtil.showAlert(aqiInfoStage, "指派失败", "未找到对应网格员ID", "请选择有效的网格员","warn");
            return;
        }

        // 传ID给业务层
        aqiFeedbackService.assignGridMember(afId, selectedId);
        JavafxUtil.showAlert(aqiInfoStage, "指派成功", "AQI反馈信息指派成功!", "请等待网格员实测数据信息","info");
        initConroller();
    }

    // 界面空间初始化方法
    public void initConroller(){
        txt_afId.setText("");
        label_afId.setText("无");
        label_afName.setText("无");
        label_address.setText("无");
        label_cityName.setText("无");
        label_date.setText("无");
        label_estimateGrade.setText("无");
        label_infomation.setText("无");
        label_proviceName.setText("无");
        combo_realName.setValue("请选择网格员");
    }
}
