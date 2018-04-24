package com.futurteam.labmanagement.entities.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RequiredArgsConstructor
public class LaborantNote implements Serializable, Comparable<LaborantNote> {

    private static final long serialVersionUID = 7213151863445829452L;

    @Getter
    @NotNull
    private final String date;
    @Getter
    @NotNull
    private final String time;
    @Getter
    @NotNull
    private final String number;
    @Getter
    @NotNull
    private final String patientName;

    @Override
    public int compareTo(@NotNull final LaborantNote note) {
        @NotNull val formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        try {
            @NotNull val thisDate = formatter.parse(date + " " + time);
            @NotNull val thatDate = formatter.parse(note.date + " " + note.time);

            return thisDate.compareTo(thatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
