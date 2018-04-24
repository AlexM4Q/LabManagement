package com.futurteam.labmanagement.utils;

import com.futur.common.annotations.PrepareURL;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.net.URL;

import static com.futur.common.helpers.resources.ResourcesHelper.checkURL;
import static com.futur.common.helpers.resources.ResourcesHelper.getInternalUrl;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResourcesUtils {

    @PrepareURL
    public static final URL LAYOUT_MAIN_FXML = getInternalUrl("layout_main/layout_main.fxml");
    @PrepareURL
    public static final URL WINDOW_CREATE_LABORANT_FXML = getInternalUrl("window_create_laborant/window_create_laborant.fxml");
    @PrepareURL
    public static final URL WINDOW_CREATE_LABORANT_NOTE_FXML = getInternalUrl("window_create_laborant_note/window_create_laborant_note.fxml");
    @PrepareURL
    public static final URL WINDOW_ALERT_WARNING_FXML = getInternalUrl("window_alert_warning/window_alert_warning.fxml");

    static {
        checkURL(ResourcesUtils.class);
    }

}
