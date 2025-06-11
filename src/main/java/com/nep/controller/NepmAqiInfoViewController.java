package com.nep.controller;

import com.nep.po.AqiFeedback;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.nep.util.FileUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NepmAqiInfoViewController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(NepmAqiInfoViewController.class.getName());

    @FXML
    private TableView<AqiFeedback> txt_tableView;

    public TableView<AqiFeedback> getTxt_tableView() {
        return txt_tableView;
    }

    public void setTxt_tableView(TableView<AqiFeedback> txt_tableView) {
        this.txt_tableView = txt_tableView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 初始化table 数据表
        TableColumn<AqiFeedback, Integer> afIdColumn = new TableColumn<>("编号");
        afIdColumn.setMinWidth(40);
        afIdColumn.setStyle("-fx-alignment: center;");	//居中
        afIdColumn.setCellValueFactory(new PropertyValueFactory<>("afId"));

        TableColumn<AqiFeedback, String> provinceNameColumn = new TableColumn<>("省区域");
        provinceNameColumn.setMinWidth(60);
        provinceNameColumn.setStyle("-fx-alignment: center;");	//居中
        provinceNameColumn.setCellValueFactory(new PropertyValueFactory<>("provinceName")); // 修正拼写

        TableColumn<AqiFeedback, String> cityNameColumn = new TableColumn<>("市区域");
        cityNameColumn.setMinWidth(60);
        cityNameColumn.setStyle("-fx-alignment: center;");	//居中
        cityNameColumn.setCellValueFactory(new PropertyValueFactory<>("cityName"));

        TableColumn<AqiFeedback, String> estimateGradeColumn = new TableColumn<>("预估等级");
        estimateGradeColumn.setMinWidth(60);
        estimateGradeColumn.setStyle("-fx-alignment: center;");	//居中
        estimateGradeColumn.setCellValueFactory(new PropertyValueFactory<>("estimateGrade"));

        TableColumn<AqiFeedback, String> dateColumn = new TableColumn<>("反馈时间");
        dateColumn.setMinWidth(80);
        dateColumn.setStyle("-fx-alignment: center;");	//居中
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<AqiFeedback, String> afNameColumn = new TableColumn<>("反馈者");
        afNameColumn.setMinWidth(60);
        afNameColumn.setStyle("-fx-alignment: center;");	//居中
        afNameColumn.setCellValueFactory(new PropertyValueFactory<>("afName"));

        TableColumn<AqiFeedback, String> infoColumn = new TableColumn<>("反馈信息");
        infoColumn.setMinWidth(210);
        infoColumn.setCellValueFactory(new PropertyValueFactory<>("information")); // 修正拼写

        txt_tableView.getColumns().addAll(afIdColumn, provinceNameColumn, cityNameColumn,
                estimateGradeColumn, dateColumn, afNameColumn, infoColumn);

        ObservableList<AqiFeedback> data = FXCollections.observableArrayList();

        try {
            // 使用资源路径而非绝对路径
            List<AqiFeedback> afList = (List<AqiFeedback>) FileUtil.readObject(
                    "NepDatas/ObjectData/aqifeedback.txt");

            // 检查afList是否为null
            if (afList != null) {
                for (AqiFeedback afb : afList) {
                    if ("未指派".equals(afb.getState())) { // 避免NPE的字符串比较方式
                        data.add(afb);
                    }
                }
            } else {
                LOGGER.warning("未找到AQI反馈数据或数据为空");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "加载AQI反馈数据失败", e);
            // 可以在这里添加UI提示，告知用户加载数据失败
        }

        txt_tableView.setItems(data);
    }
}