package com.futurteam.labmanagement.controllers;

import com.futurteam.labmanagement.controllers.base.BaseController;
import com.futurteam.labmanagement.entities.models.LaborantNote;
import com.futurteam.labmanagement.entities.models.rows.LaborantNoteRow;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import lombok.Setter;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public final class LaborantSearchResultController extends BaseController {

    @FXML
    private TableView<LaborantNoteRow> management_TV;

    @Setter
    @Nullable
    private Iterable<LaborantNote> notes;

    public void setFilter(@NotNull final LocalDate from,
                          @NotNull final LocalDate to) {
        assert notes != null;

        @NotNull val fromDate = Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
        @NotNull val toDate = Date.from(to.atStartOfDay(ZoneId.systemDefault()).toInstant());
        for (@NotNull val note : notes) {
            @Nullable val dateTime = note.getDateTime();
            if (Objects.isNull(dateTime)) {
                continue;
            }

            if (dateTime.before(fromDate) || toDate.before(dateTime)) {
                continue;
            }

            management_TV.getItems().add(new LaborantNoteRow(note));
        }
    }

    public void setFilter(@NotNull final String patientName) {
        assert notes != null;

        for (@NotNull val note : notes) {
            @Nullable val dateTime = note.getDateTime();
            if (Objects.isNull(dateTime)) {
                continue;
            }

            if (!note.getPatientName().contains(patientName)) {
                continue;
            }

            management_TV.getItems().add(new LaborantNoteRow(note));
        }
    }

}
