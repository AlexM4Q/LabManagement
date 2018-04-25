package com.futurteam.labmanagement.controllers;

import com.futurteam.labmanagement.controllers.base.BaseController;
import com.futurteam.labmanagement.entities.models.Laborant;
import com.futurteam.labmanagement.utils.UIUtils;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CreateLaborantController extends BaseController {

    @Getter
    @Setter
    @Nullable
    private Stage stage;

    @FXML
    private TextField workerName_TF;

    @Getter
    private boolean isAccepted;

    @NotNull
    public Laborant createLaborant() {
        return new Laborant(workerName_TF.getText());
    }

    private void exit(final boolean isAccepted) {
        this.isAccepted = isAccepted;
        assert stage != null;
        stage.close();
    }

    @FXML
    private void add_B_action() {
        if (workerName_TF.getText().isEmpty()) {
            UIUtils.alertWarning("Добавление лаборанта", "Введите Ф.И.О. лаборанта");
            return;
        }

        exit(true);
    }

    @FXML
    private void cancel_B_action() {
        exit(false);
    }
}
