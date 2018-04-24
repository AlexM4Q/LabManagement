package com.futurteam.labmanagement.entities.models.rows;

import javafx.beans.property.SimpleStringProperty;
import org.jetbrains.annotations.NotNull;

public final class ManagementRow {

    private final SimpleStringProperty date;
    private final SimpleStringProperty time;
    private final SimpleStringProperty number;
    private final SimpleStringProperty patient;

    public ManagementRow() {
        this.date = new SimpleStringProperty();
        this.time = new SimpleStringProperty();
        this.number = new SimpleStringProperty();
        this.patient = new SimpleStringProperty();
    }

    public ManagementRow(@NotNull final String date,
                         @NotNull final String time,
                         @NotNull final String number,
                         @NotNull final String patient) {
        this.date = new SimpleStringProperty(date);
        this.time = new SimpleStringProperty(time);
        this.number = new SimpleStringProperty(number);
        this.patient = new SimpleStringProperty(patient);
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

    public String getPatient() {
        return patient.get();
    }

    public void setPatient(String patient) {
        this.patient.set(patient);
    }
}
