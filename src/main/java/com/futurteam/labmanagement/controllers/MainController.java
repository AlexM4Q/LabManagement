package com.futurteam.labmanagement.controllers;

import com.futur.common.helpers.resources.FXMLHelper;
import com.futurteam.labmanagement.controllers.base.BaseController;
import com.futurteam.labmanagement.entities.AppContext;
import com.futurteam.labmanagement.entities.models.DataBase;
import com.futurteam.labmanagement.entities.models.Laborant;
import com.futurteam.labmanagement.entities.models.rows.LaborantNoteRow;
import com.futurteam.labmanagement.utils.ObjectIOUtils;
import com.futurteam.labmanagement.utils.ResourcesUtils;
import com.futurteam.labmanagement.utils.UIUtils;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Optional;

public final class MainController extends BaseController {

    private final FileChooser dataBaseChooser;

    @FXML
    private Label laborantName_L;

    @FXML
    private DatePicker periodFrom_DP;

    @FXML
    private DatePicker periodTo_DP;

    @FXML
    private TreeView<Laborant> workers_TV;

    @FXML
    private TableView<LaborantNoteRow> management_TV;

    public MainController() {
        // todo Добавить фильтры
        this.dataBaseChooser = new FileChooser();
    }

    @FXML
    public void initialize() {
        workers_TV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            @NotNull val laborant = newValue.getValue();
            laborantName_L.setText(laborant.getName());
            setLaborant(laborant);
        });
    }

    private void clear() {
        workers_TV.getRoot().getChildren().clear();
        management_TV.getItems().clear();
    }

    private void open(@NotNull final File file) {
        Optional.ofNullable(ObjectIOUtils.readDataBase(file)).ifPresent(this::open);
    }

    private void open(@NotNull final DataBase dataBase) {
        clear();
        dataBase.getLaborants().forEach(this::addLaborant);
    }

    private void addLaborant(@NotNull final Laborant laborant) {
        @NotNull val graphic = new FontAwesomeIconView(FontAwesomeIcon.USER);
        @NotNull val treeItem = new TreeItem<Laborant>(laborant, graphic);
        workers_TV.getRoot().getChildren().add(treeItem);

        getDataBase().getLaborants().add(laborant);
    }

    private void setLaborant(@NotNull final Laborant laborant) {
        @NotNull val rows = management_TV.getItems();
        rows.clear();
        laborant.getNotes().stream().map(LaborantNoteRow::new).forEach(rows::add);
    }

    @FXML
    private void new_B_action() {
        @NotNull val alert = UIUtils.alertWarning("Новый проект", "При создании нового проекта, все не сохраненные данные будут потеряны. Хотите продолжить?");
        if (alert.getController().isAccepted()) {
            disposeData();
            clear();
        }
    }

    @FXML
    private void open_B_action() {
        Optional.ofNullable(dataBaseChooser.showOpenDialog(getPrimaryStage())).ifPresent(this::open);
    }

    @FXML
    private void save_B_action() {
        @Nullable val workFile = AppContext.getINSTANCE().getNowInWorkFile();
        if (workFile == null) {
            saveAs_B_action();
        } else {
            ObjectIOUtils.writeDataBase(getDataBase(), workFile);
        }
    }

    @FXML
    private void saveAs_B_action() {
        Optional.ofNullable(dataBaseChooser.showSaveDialog(getPrimaryStage())).ifPresent(file -> {
            AppContext.getINSTANCE().setNowInWorkFile(file);
            ObjectIOUtils.writeDataBase(getDataBase(), file);
        });
    }

    @FXML
    private void exit_B_action() {
        @NotNull val alert = UIUtils.alertWarning("Выход", "Все не сохраненные данные будут потеряны. Хотите продолжить?");
        if (alert.getController().isAccepted()) {
            System.exit(0);
        }
    }

    @FXML
    private void addLaborant_B_action() {
        @NotNull val addWorker = FXMLHelper.<CreateLaborantController, Parent>loadFXML(ResourcesUtils.WINDOW_CREATE_LABORANT_FXML);
        @NotNull val node = addWorker.getNode();
        @NotNull val controller = addWorker.getController();

        @NotNull val stage = new Stage();
        controller.setStage(stage);
        stage.initOwner(getPrimaryStage());
        stage.setTitle("Добавление работника");
        stage.setScene(new Scene(node));
        stage.showAndWait();

        if (controller.isAccepted()) {
            @NotNull val laborant = controller.createLaborant();
            addLaborant(laborant);
        }
    }

    @FXML
    private void removeLaborant_B_action() {
    }

    @FXML
    private void addPatient_B_action() {
        @NotNull val addWorker = FXMLHelper.<CreateLaborantNoteController, Parent>loadFXML(ResourcesUtils.WINDOW_CREATE_LABORANT_NOTE_FXML);
        @NotNull val node = addWorker.getNode();
        @NotNull val controller = addWorker.getController();

        @NotNull val stage = new Stage();
        controller.setStage(stage);
        stage.initOwner(getPrimaryStage());
        stage.setTitle("Добавление записи");
        stage.setScene(new Scene(node));
        stage.showAndWait();

        if (controller.isAccepted()) {
            @NotNull val laborantNote = controller.createLaborantNote();
            management_TV.getItems().add(new LaborantNoteRow(laborantNote));
            controller.getLaborant().getNotes().add(laborantNote);
        }
    }

    @FXML
    private void removePatient_B_action() {
    }

    @FXML
    private void chartLaborantNotes_B_action() {
    }
}
