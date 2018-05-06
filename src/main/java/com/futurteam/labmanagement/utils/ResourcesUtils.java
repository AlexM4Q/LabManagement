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
    public static final URL ICON = getInternalUrl("icon.png");
    @PrepareURL
    public static final URL LAYOUT_MAIN_FXML = getInternalUrl("layout_main/layout_main.fxml");
    @PrepareURL
    public static final URL WINDOW_CREATE_LABORANT_FXML = getInternalUrl("window_create_laborant/window_create_laborant.fxml");
    @PrepareURL
    public static final URL WINDOW_CREATE_LABORANT_NOTE_FXML = getInternalUrl("window_create_laborant_note/window_create_laborant_note.fxml");
    @PrepareURL
    public static final URL WINDOW_ALERT_INFO_FXML = getInternalUrl("window_alert_info/window_alert_info.fxml");
    @PrepareURL
    public static final URL WINDOW_ALERT_WARNING_FXML = getInternalUrl("window_alert_warning/window_alert_warning.fxml");
    @PrepareURL
    public static final URL WINDOW_LABORANT_NOTES_SEARCH_RESULT = getInternalUrl("window_laborant_notes_search_result/window_laborant_notes_search_result.fxml");
    @PrepareURL
    public static final URL WINDOW_LABORANT_NOTES_CHART = getInternalUrl("window_laborant_notes_chart/window_laborant_notes_chart.fxml");
    @PrepareURL
    public static final URL WINDOW_ABOUT_DEV = getInternalUrl("window_about_dev/window_about_dev.fxml");
    @PrepareURL
    public static final URL WINDOW_ABOUT_PROGRAM = getInternalUrl("window_about_program/window_about_program.fxml");
    @PrepareURL
    public static final URL WINDOW_HELP = getInternalUrl("window_help/window_help.fxml");

    static {
        checkURL(ResourcesUtils.class);
    }

}
