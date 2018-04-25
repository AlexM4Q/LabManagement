package com.futurteam.labmanagement.utils;

import com.futur.common.helpers.resources.ObjectIOHelper;
import com.futurteam.labmanagement.entities.models.DataBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public final class ObjectIOUtils extends ObjectIOHelper {

    @Nullable
    public static DataBase readDataBase(@NotNull final File file) {
        return readObjectSafely(DataBase.class, file.getAbsolutePath());
    }

    public static boolean writeDataBase(@NotNull final DataBase dataBase, @NotNull final File file) {
        return writeObjectSafely(dataBase, file.getAbsolutePath());
    }

}
