package com.futurteam.labmanagement.utils;

import com.futur.common.annotations.PrepareURL;

import java.net.URL;

import static com.futur.common.helpers.resources.ResourcesHelper.checkURL;
import static com.futur.common.helpers.resources.ResourcesHelper.getInternalUrl;

public final class ResourcesUtils {

    @PrepareURL
    public static final URL LAYOUT_MAIN_FXML = getInternalUrl("layout_main/layout_main.fxml");
    @PrepareURL
    public static final URL WINDOW_ADD_WORKER_FXML = getInternalUrl("window_add_worker/window_add_worker.fxml");
    @PrepareURL
    public static final URL WINDOW_ADD_PATIENT_FXML = getInternalUrl("window_add_patient/window_add_patient.fxml");

    static {
        checkURL(ResourcesUtils.class);
    }

}
