package com.futurteam.labmanagement.controllers;

import com.futurteam.labmanagement.controllers.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class AlertWarningController extends BaseController {

    @Setter
    @Nullable
    private Stage stage;

    @FXML
    private Label cause_L;
    @FXML
    private TextArea description_TA;

    @Getter
    private boolean isAccepted;

    public void setCause(@NotNull final String cause) {
        cause_L.setText(cause);
    }

    public void setDescription(@NotNull final String description) {
        description_TA.setText(description);
    }

    private void exit(final boolean isAccepted) {
        this.isAccepted = isAccepted;
        assert stage != null;
        stage.close();
    }

    @FXML
    private void ok_B_action() {
        exit(true);
    }

    @FXML
    private void cancel_B_action() {
        exit(false);
    }
}
