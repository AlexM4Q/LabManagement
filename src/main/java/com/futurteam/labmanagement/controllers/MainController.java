package com.futurteam.labmanagement.controllers;

import com.futur.common.helpers.resources.FXMLHelper;
import com.futurteam.labmanagement.controllers.base.BaseController;
import com.futurteam.labmanagement.entities.models.rows.ManagementRow;
import com.futurteam.labmanagement.utils.ResourcesUtils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public class MainController extends BaseController {

    @FXML
    private DatePicker periodFrom_DP;

    @FXML
    private DatePicker periodTo_DP;

    @FXML
    private TreeView<String> workers_TV;

    @FXML
    private TableView<ManagementRow> management_TV;

    @FXML
    public void initialize() {

    }

    @FXML
    private void addWorker_B_action() {
        @NotNull val root = workers_TV.getRoot();

        val addWorker = FXMLHelper.<AddWorkerController, Parent>loadFXML(ResourcesUtils.WINDOW_ADD_WORKER_FXML);
        val node = addWorker.getNode();
        val controller = addWorker.getController();

        val stage = new Stage();
        controller.setStage(stage);
        stage.initOwner(getPrimaryStage());
        stage.setTitle("Добавление работника");
        stage.setScene(new Scene(node));
        stage.showAndWait();

        if (controller.isAccepted()) {
            root.getChildren().add(new TreeItem<String>(controller.getWorkerName(), new FontAwesomeIconView(FontAwesomeIcon.USER)));
        }
    }

    @FXML
    private void removeWorker_B_action() {
    }

    @FXML
    private void addPatient_B_action() {
        val addWorker = FXMLHelper.<AddPatientController, Parent>loadFXML(ResourcesUtils.WINDOW_ADD_PATIENT_FXML);
        val node = addWorker.getNode();
        val controller = addWorker.getController();

        val stage = new Stage();
        controller.setStage(stage);
        stage.initOwner(getPrimaryStage());
        stage.setTitle("Добавление пациента");
        stage.setScene(new Scene(node));
        stage.showAndWait();

        if (controller.isAccepted()) {
            management_TV.getItems().add(controller.createManagementRow());
        }
    }

    @FXML
    private void removePatient_B_action() {
    }

    @FXML
    private void chartWorker_B_action() {
    }
}
