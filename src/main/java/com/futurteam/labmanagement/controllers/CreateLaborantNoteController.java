package com.futurteam.labmanagement.controllers;

import com.futur.ui.FormatterHelper;
import com.futurteam.labmanagement.controllers.base.BaseController;
import com.futurteam.labmanagement.entities.models.Laborant;
import com.futurteam.labmanagement.entities.models.LaborantNote;
import com.futurteam.labmanagement.utils.UIUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tornadofx.control.DateTimePicker;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

public final class CreateLaborantNoteController extends BaseController {

    @Getter
    @Setter
    @Nullable
    private Stage stage;

    @FXML
    private ComboBox<Laborant> laborants_CB;
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
        number_TF.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 6) {
                number_TF.setText(oldValue);
            }
        });

        laborants_CB.getItems().addAll(getDataBase().getLaborants());
    }

    @NotNull
    public LaborantNote createLaborantNote() {
        @NotNull val dateTimeValue = dateTime_DTP.getDateTimeValue();
        @NotNull val dateTime = Date.from(dateTimeValue.atZone(ZoneId.systemDefault()).toInstant());
        @NotNull val date = new SimpleDateFormat("dd.MM.yyyy").format(dateTime);
        @NotNull val time = new SimpleDateFormat("HH:mm:ss").format(dateTime);
        return new LaborantNote(date, time, number_TF.getText(), patientName_TF.getText());
    }

    @NotNull
    public Laborant getLaborant() {
        @Nullable val laborant = laborants_CB.getValue();
        assert laborant != null;
        return laborant;
    }

    private void exit(final boolean isAccepted) {
        this.isAccepted = isAccepted;
        assert stage != null;
        stage.close();
    }

    @FXML
    private void add_B_action() {
        if (laborants_CB.getValue() == null) {
            UIUtils.alertWarning("Создание записи", "Выбирете лаборанта");
            return;
        }

        if (dateTime_DTP.getValue() == null) {
            UIUtils.alertWarning("Создание записи", "Выбирете дату");
            return;
        }

        if (number_TF.getText().isEmpty()) {
            UIUtils.alertWarning("Создание записи", "Введите номер карты");
            return;
        }

        if (patientName_TF.getText().isEmpty()) {
            UIUtils.alertWarning("Создание записи", "Введите Ф.И.О. пациента");
            return;
        }

        exit(true);
    }

    @FXML
    private void cancel_B_action() {
        exit(false);
    }

}
