package com.futurteam.labmanagement.utils;

import com.futur.common.helpers.resources.FXMLHelper;
import com.futur.common.models.FXMLPair;
import com.futurteam.labmanagement.controllers.AlertController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

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
        @NotNull val stage = new Stage();
        controller.setStage(stage);
        controller.setDescription(description);

        stage.setTitle(title);
        stage.setScene(new Scene(node));
        stage.showAndWait();

        return pair;
    }

}
