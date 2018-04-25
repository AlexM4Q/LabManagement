package com.futurteam.labmanagement.entities.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

@RequiredArgsConstructor
public class LaborantNote implements Serializable, Comparable<LaborantNote> {

    private static final long serialVersionUID = 7213151863445829452L;

    @NotNull
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

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

    @Nullable
    public static Date getDateTime(@NotNull final String date, @NotNull final String time) {
        try {
            return simpleDateFormat.parse(date + " " + time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Nullable
    public Date getDateTime() {
        return getDateTime(date, time);
    }

    @Override
    public int compareTo(@NotNull final LaborantNote note) {
        return new LaborantNoteComparator().compare(this, note);
    }

    public static class LaborantNoteComparator implements Comparator<LaborantNote> {

        @Override
        public int compare(LaborantNote o1, LaborantNote o2) {
            @Nullable val thisDateTime = o1.getDateTime();
            @Nullable val thatDateTime = o2.getDateTime();

            return Objects.isNull(thisDateTime) || Objects.isNull(thatDateTime) ? 0 : thisDateTime.compareTo(thatDateTime);
        }
    }

}
