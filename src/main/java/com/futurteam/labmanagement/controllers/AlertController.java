package com.futurteam.labmanagement.controllers;

import com.futurteam.labmanagement.controllers.base.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class AlertController extends BaseController {

    @Setter
    @Nullable
    private Stage stage;

    @FXML
    private Label description_L;

    @Getter
    private boolean isAccepted;

    public void setDescription(@NotNull final String description) {
        description_L.setText(description);
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
