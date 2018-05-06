package com.futurteam.labmanagement;

import com.futur.common.helpers.resources.FXMLHelper;
import com.futurteam.labmanagement.entities.AppContext;
import com.futurteam.labmanagement.utils.ResourcesUtils;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.val;
import org.jetbrains.annotations.NotNull;

public final class App extends Application {

    public static void main(@NotNull final String[] args) {
        launch(App.class, args);
    }

    @Override
    public void start(@NotNull final Stage primaryStage) {
        AppContext.getINSTANCE().setPrimaryStage(primaryStage);

        val root = FXMLHelper.<Parent>loadNode(ResourcesUtils.LAYOUT_MAIN_FXML);
        primaryStage.setTitle("Лаборатория");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
