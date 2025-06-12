package com.nep.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.nep.po.Supervisor;
import com.nep.service.impl.SupervisorServiceImpl;
import com.nep.util.JsonUtil;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.nep.util.JavafxUtil;
import com.nep.NepsMain;
import com.nep.service.SupervisorService;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class NepsRegisterViewController {
    @FXML
    private TextField txt_id;
    @FXML
    private PasswordField txt_password;
    @FXML
    private PasswordField txt_repassword;
    @FXML
    private TextField txt_realName;
    @FXML
    private RadioButton txt_sex;
    public static Stage primaryStage;
    private SupervisorService supervisorService = new SupervisorServiceImpl();




// ...

    public void register() throws IOException {
        if (!txt_password.getText().equals(txt_repassword.getText())) {
            JavafxUtil.showAlert(primaryStage, "注册失败", "两次输入密码不一致", "请重新输入确认密码", "warn");
            txt_repassword.setText("");
            return;
        }
        Supervisor supervisor = new Supervisor();
        supervisor.setTelId(txt_id.getText());
        supervisor.setPassword(txt_password.getText());
        supervisor.setRealName(txt_realName.getText());
        if ("女".equals(txt_sex.getText())) {
            supervisor.setSex(0);
        } else if ("男".equals(txt_sex.getText())) {
            supervisor.setSex(1);
        }
        boolean flag = supervisorService.register(supervisor);
        if (flag) {
            JavafxUtil.showAlert(primaryStage, "注册成功", txt_id.getText() + " 账号注册成功!", "可以进行用户登录!", "info");
            // 注册成功后，更新内存中的用户列表
            List<Supervisor> slist = JsonUtil.readListfromJson(
                    System.getProperty("user.dir") + "/src/main/resources/NepDatas/JSONData/supervisor.json",
                    new TypeReference<List<Supervisor>>() {}
            );
            // 确保slist是支持修改操作的列表
            List<Supervisor> mutableList = new ArrayList<>(slist);
            mutableList.add(supervisor);

            // 跳转到登录界面进行登录
            JavafxUtil.showStage(NepsMain.class, "view/NepsLoginView.fxml", primaryStage, "东软环保公众监督平台-公众监督员端");
        } else {
            JavafxUtil.showAlert(primaryStage, "注册失败", "手机号已被注册", "请重新输入注册手机号码", "warn");
            txt_id.setText("");
            return;
        }
    }


    public void back() {
        JavafxUtil.showStage(NepsMain.class, "view/NepsLoginView.fxml", primaryStage, "东软环保公众监督平台-公众监督员端");
    }
}
