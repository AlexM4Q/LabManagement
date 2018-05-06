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
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

public final class MainController extends BaseController {

    // todo заполнить
    private static final String[] advices = new String[]{
            "В жизни надо быть упёртым, но не бараном",
            "2",
            "3"
    };

    private static final String[] nextNames = new String[]{
            "Ага",
            "Ок",
            "Понял",
            "Хорошо",
            "Дальше",
            "Буду знать"
    };

    private final FileChooser dataBaseChooser;

    private final FileChooser exportDataChooser;

    @FXML
    private Label laborantName_L;
    @FXML
    private DatePicker periodFrom_DP;
    @FXML
    private DatePicker periodTo_DP;
    @FXML
    private TextField patientNameSearch_TF;
    @FXML
    private TreeView<Laborant> workers_TV;
    @FXML
    private TableView<LaborantNoteRow> management_TV;
    @FXML
    private Label adviceOfDay_L;
    @FXML
    private Button nextAdvice_B;

    @Nullable
    private Laborant currentLaborant;

    public MainController() {
        this.dataBaseChooser = new FileChooser();
        dataBaseChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Бинарные файлы", "*.bin"));
        dataBaseChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Все файлы", "*.*"));

        this.exportDataChooser = new FileChooser();
        exportDataChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel(CSV) файлы", "*.csv"));
    }

    @FXML
    public void initialize() {
        workers_TV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setLaborant(newValue.getValue()));

        adviceOfDay_L.setText(advices[0]);
        nextAdvice_B.setText(nextNames[0]);
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
        laborantName_L.setText(laborant.getName());
        currentLaborant = laborant;
    }

    @FXML
    private void new_B_action() {
        if (existsData()) {
            @NotNull val alert = UIUtils.alertWarning("Новый проект", "При создании нового проекта, все не сохраненные данные будут потеряны. Хотите продолжить?");
            if (!alert.getController().isAccepted()) {
                return;
            }
        }

        disposeData();
        clear();
    }

    @FXML
    private void open_B_action() {
        if (existsData()) {
            @NotNull val alert = UIUtils.alertWarning("Открытие", "При открытии проекта, все не сохраненные данные будут потеряны. Хотите продолжить?");
            if (!alert.getController().isAccepted()) {
                return;
            }
        }

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
        if (existsData()) {
            @NotNull val alert = UIUtils.alertWarning("Выход", "Все не сохраненные данные будут потеряны. Хотите продолжить?");
            if (!alert.getController().isAccepted()) {
                return;
            }
        }

        System.exit(0);
    }

    @FXML
    private void dev_B_action() {
        UIUtils.openAboutDev();
    }

    @FXML
    private void program_B_action() {
        UIUtils.openAboutProgram();
    }

    @FXML
    private void help_B_action() {
        UIUtils.openHelp();
    }

    @FXML
    private void addLaborant_B_action() {
        @NotNull val addWorker = FXMLHelper.<CreateLaborantController, Parent>loadFXML(ResourcesUtils.WINDOW_CREATE_LABORANT_FXML);
        @NotNull val node = addWorker.getNode();
        @NotNull val controller = addWorker.getController();

        @NotNull val stage = UIUtils.prepareStage("Добавление работника");
        controller.setStage(stage);
        stage.setScene(new Scene(node));
        stage.showAndWait();

        if (controller.isAccepted()) {
            @NotNull val laborant = controller.createLaborant();
            addLaborant(laborant);
        }
    }

    @FXML
    private void removeLaborant_B_action() {
        if (getDataBase().getLaborants().remove(currentLaborant)) {
            workers_TV.getRoot().getChildren().removeIf(item -> item.getValue() == currentLaborant);
        }
    }

    @FXML
    private void periodSearch_B_action() {
        showSerch(controller -> controller.setFilter(periodFrom_DP.getValue(), periodTo_DP.getValue()));
    }

    @FXML
    private void patientNameSearch_B_action() {
        showSerch(controller -> controller.setFilter(patientNameSearch_TF.getText()));
    }

    private void showSerch(@NotNull final Consumer<LaborantSearchResultController> filter) {
        if (Objects.isNull(currentLaborant)) {
            UIUtils.alertInfo("Поиск", "Выбирите лаборанта");
            return;
        }

        @NotNull val pair = FXMLHelper.<LaborantSearchResultController, Parent>loadFXML(ResourcesUtils.WINDOW_LABORANT_NOTES_SEARCH_RESULT);
        @NotNull val stage = UIUtils.prepareStage("Результаты поиска");
        @NotNull val controller = pair.getController();
        controller.setNotes(currentLaborant.getNotes());
        filter.accept(controller);
        stage.setScene(new Scene(pair.getNode()));
        stage.show();
    }

    @FXML
    private void addPatient_B_action() {
        @NotNull val addWorker = FXMLHelper.<CreateLaborantNoteController, Parent>loadFXML(ResourcesUtils.WINDOW_CREATE_LABORANT_NOTE_FXML);
        @NotNull val node = addWorker.getNode();
        @NotNull val controller = addWorker.getController();

        @NotNull val stage = UIUtils.prepareStage("Добавление записи");
        controller.setStage(stage);
        stage.setScene(new Scene(node));
        stage.showAndWait();

        if (controller.isAccepted()) {
            @NotNull val laborantNote = controller.createLaborantNote();
            @NotNull val laborant = controller.getLaborant();
            laborant.getNotes().add(laborantNote);
            setLaborant(laborant);
        }
    }

    @FXML
    private void removePatient_B_action() {
        @NotNull val row = management_TV.getSelectionModel().getSelectedItem();
        @NotNull val note = row.getLaborantNote();

        for (@NotNull val laborant : getDataBase().getLaborants()) {
            if (laborant.getNotes().remove(note)) {
                management_TV.getItems().remove(row);
                return;
            }
        }
    }

    @FXML
    private void chartLaborantNotes_B_action() {
        if (currentLaborant == null) {
            UIUtils.alertInfo("Построение графика", "Выбирите лаборанта");
            return;
        }

        @NotNull val chart = FXMLHelper.<LaborantNotesChartController, Parent>loadFXML(ResourcesUtils.WINDOW_LABORANT_NOTES_CHART);
        @NotNull val node = chart.getNode();
        @NotNull val controller = chart.getController();

        controller.setData(currentLaborant.getNotes());

        @NotNull val stage = UIUtils.prepareStage("График", true);
        stage.setScene(new Scene(node));
        stage.show();
    }

    @FXML
    private void exportLaborantNotes_B_action() throws FileNotFoundException {
        if (currentLaborant == null) {
            UIUtils.alertInfo("Экспорт в CSV", "Выбирите лаборанта");
            return;
        }

        @NotNull val notes = currentLaborant.getNotes();
        if (notes.isEmpty()) {
            UIUtils.alertInfo("Экспорт в CSV", "В таблице нет данных для экспорта");
            return;
        }

        @NotNull val exportFile = exportDataChooser.showSaveDialog(getPrimaryStage());
        if (exportFile == null) {
            UIUtils.alertWarning("Экспорт в CSV", "Файл не выбран. Экспорт отменен");
            return;
        }

        @NotNull val csv = new StringBuilder();
        for (@NotNull val note : notes) {
            csv.append(note.getDate());
            csv.append(';');
            csv.append(note.getTime());
            csv.append(';');
            csv.append(note.getNumber());
            csv.append(';');
            csv.append(note.getPatientName());
            csv.append('\n');
        }

        try (@NotNull val out = new PrintWriter(exportFile)) {
            out.println(csv.toString());
        }
    }

    @FXML
    private void nextAdvice_B_action() {
        adviceOfDay_L.setText(advices[(int) (advices.length * Math.random())]);
        nextAdvice_B.setText(nextNames[(int) (nextNames.length * Math.random())]);
    }
}
