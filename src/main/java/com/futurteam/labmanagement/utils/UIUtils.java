package com.futurteam.labmanagement.utils;

import com.futur.common.helpers.resources.FXMLHelper;
import com.futur.common.models.FXMLPair;
import com.futurteam.labmanagement.controllers.AlertController;
import com.futurteam.labmanagement.entities.AppContext;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

@SuppressWarnings("UnusedReturnValue")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UIUtils {

    @NotNull
    public static FXMLPair<AlertController, Parent> alertInfo(@NotNull final String title, @NotNull final String description) {
        return alert(ResourcesUtils.WINDOW_ALERT_INFO_FXML, title, description);
    }

    @NotNull
    public static FXMLPair<AlertController, Parent> alertWarning(@NotNull final String title, @NotNull final String description) {
        return alert(ResourcesUtils.WINDOW_ALERT_WARNING_FXML, title, description);
    }

    @NotNull
    private static FXMLPair<AlertController, Parent> alert(@NotNull final URL url, @NotNull final String title, @NotNull final String description) {
        @NotNull val pair = FXMLHelper.<AlertController, Parent>loadFXML(url);

        @NotNull val node = pair.getNode();
        @NotNull val controller = pair.getController();
        @NotNull val stage = prepareStage(title);
        controller.setStage(stage);
        controller.setDescription(description);

        stage.setScene(new Scene(node));
        stage.showAndWait();

        return pair;
    }

    public static void openHelp() {
        openWindow(ResourcesUtils.WINDOW_HELP, "Помощь");
    }

    public static void openAboutProgram() {
        openWindow(ResourcesUtils.WINDOW_ABOUT_PROGRAM, "О Программе");
    }

    public static void openAboutDev() {
        openWindow(ResourcesUtils.WINDOW_ABOUT_DEV, "О Разработчике");
    }

    private static void openWindow(@NotNull final URL url, @NotNull final String title) {
        @NotNull val node = FXMLHelper.<Parent>loadNode(url);

        @NotNull val stage = prepareStage(title);
        stage.setScene(new Scene(node));
        stage.showAndWait();
    }


    public static Stage prepareStage(@NotNull final String title) {
        return prepareStage(title, false);
    }

    public static Stage prepareStage(@NotNull final String title, final boolean resizeable) {
        @NotNull val stage = new Stage();
        stage.initOwner(AppContext.getINSTANCE().getPrimaryStage());
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(ResourcesUtils.ICON.toString()));
        stage.setResizable(resizeable);
        stage.setTitle(title);
        return stage;
    }

}
