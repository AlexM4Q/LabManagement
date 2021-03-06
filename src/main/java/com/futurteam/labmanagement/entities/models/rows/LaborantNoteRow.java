package com.futurteam.labmanagement.entities.models.rows;

import com.futurteam.labmanagement.entities.models.LaborantNote;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

public final class LaborantNoteRow {

    @Getter
    private final LaborantNote laborantNote;

    private final SimpleStringProperty date;
    private final SimpleStringProperty time;
    private final SimpleStringProperty number;
    private final SimpleStringProperty patientName;

    public LaborantNoteRow(@NotNull final LaborantNote laborantNote) {
        this.laborantNote = laborantNote;
        this.date = new SimpleStringProperty(laborantNote.getDate());
        this.time = new SimpleStringProperty(laborantNote.getTime());
        this.number = new SimpleStringProperty(laborantNote.getNumber());
        this.patientName = new SimpleStringProperty(laborantNote.getPatientName());
    }

    @Nullable
    public Date getDateTime() {
        return LaborantNote.getDateTime(getDate(), getTime());
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getNumber() {
        return number.get();
    }

    public void setNumber(String number) {
        this.number.set(number);
    }

    public String getPatientName() {
        return patientName.get();
    }

    public void setPatientName(String patient) {
        this.patientName.set(patient);
    }
}
