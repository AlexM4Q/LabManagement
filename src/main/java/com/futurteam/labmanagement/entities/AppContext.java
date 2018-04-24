package com.futurteam.labmanagement.entities;

import com.futurteam.labmanagement.entities.models.DataBase;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppContext {

    @Getter
    @NotNull
    private static final AppContext INSTANCE = new AppContext();

    @Setter
    @Nullable
    private Stage primaryStage;

    @Nullable
    private DataBase dataBase;

    @Getter
    @Setter
    @Nullable
    private File nowInWorkFile;

    @NotNull
    public Stage getPrimaryStage() {
        assert primaryStage != null;
        return primaryStage;
    }

    @NotNull
    public DataBase getDataBase() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }

        return dataBase;
    }

    public boolean existsData() {
        return Objects.nonNull(dataBase);
    }

    public void disposeData() {
        dataBase = null;
    }
}
