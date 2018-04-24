package com.futurteam.labmanagement.controllers.base;

import com.futurteam.labmanagement.entities.AppContext;
import com.futurteam.labmanagement.entities.models.DataBase;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public abstract class BaseController {

    @NotNull
    protected final AppContext getAppContext() {
        return AppContext.getINSTANCE();
    }

    @NotNull
    public final Stage getPrimaryStage() {
        return getAppContext().getPrimaryStage();
    }

    @NotNull
    protected final DataBase getDataBase() {
        return getAppContext().getDataBase();
    }
}
