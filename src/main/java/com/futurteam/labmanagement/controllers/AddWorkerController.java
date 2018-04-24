package com.futurteam.labmanagement.controllers;

import com.futurteam.labmanagement.controllers.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

public class AddWorkerController extends BaseController {

    @Getter
    @Setter
    @Nullable
    private Stage stage;

    @FXML
    private TextField workerName_TF;

    @Getter
    private boolean isAccepted;

    public String getWorkerName() {
        return workerName_TF.getText();
    }

    private void exit(final boolean isAccepted) {
        this.isAccepted = isAccepted;
        assert stage != null;
        stage.close();
    }

    @FXML
    private void add_B_action() {
        exit(true);
    }

    @FXML
    private void cancel_B_action() {
        exit(false);
    }
}
