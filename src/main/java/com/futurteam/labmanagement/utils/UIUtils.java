package com.futurteam.labmanagement.utils;

import com.futur.common.helpers.resources.FXMLHelper;
import com.futur.common.models.FXMLPair;
import com.futurteam.labmanagement.controllers.AlertWarningController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UIUtils {

    @NotNull
    public static FXMLPair<AlertWarningController, Parent> alertWarning(@NotNull final String cause, @NotNull final String description) {
        @NotNull val pair = FXMLHelper.<AlertWarningController, Parent>loadFXML(ResourcesUtils.WINDOW_ALERT_WARNING_FXML);

        @NotNull val node = pair.getNode();
        @NotNull val controller = pair.getController();
        @NotNull val stage = new Stage();
        controller.setStage(stage);
        controller.setCause(cause);
        controller.setDescription(description);

        stage.setTitle("Внимание!");
        stage.setScene(new Scene(node));
        stage.showAndWait();

        return pair;
    }

}
