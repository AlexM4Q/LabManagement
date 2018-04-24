package com.futurteam.labmanagement.controllers;

import com.futur.ui.FormatterHelper;
import com.futurteam.labmanagement.controllers.base.BaseController;
import com.futurteam.labmanagement.entities.models.rows.ManagementRow;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.jetbrains.annotations.Nullable;
import tornadofx.control.DateTimePicker;

public class AddPatientController extends BaseController {

    @Getter
    @Setter
    @Nullable
    private Stage stage;

    @FXML
    private DateTimePicker dateTime_DTP;
    @FXML
    private TextField number_TF;
    @FXML
    private TextField patientName_TF;

    @Getter
    private boolean isAccepted;

    @FXML
    public void initialize() {
        FormatterHelper.applyIntegerFormat(number_TF);
    }

    public ManagementRow createManagementRow() {
        val dateTimeValue = dateTime_DTP.getDateTimeValue();
        // todo set up date/time
        return new ManagementRow("", "", number_TF.getText(), patientName_TF.getText());
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
