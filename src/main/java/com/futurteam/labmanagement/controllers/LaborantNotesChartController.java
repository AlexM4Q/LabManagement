package com.futurteam.labmanagement.controllers;

import com.futurteam.labmanagement.controllers.base.BaseController;
import com.futurteam.labmanagement.entities.models.LaborantNote;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;

public final class LaborantNotesChartController extends BaseController {

    @FXML
    private LineChart<String, Integer> chart_LC;

    public void setData(@NotNull final Collection<LaborantNote> notes) {
        @NotNull val data = new HashMap<String, Integer>();
        for (@NotNull val note : notes) {
            @NotNull val date = note.getDate();
            if (data.containsKey(date)) {
                data.put(date, data.get(date) + 1);
            } else {
                data.put(date, 1);
            }
        }

        @NotNull val series = new XYChart.Series<String, Integer>();
        for (@NotNull val entry : data.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        chart_LC.getData().clear();
        chart_LC.getData().add(series);
    }

}
