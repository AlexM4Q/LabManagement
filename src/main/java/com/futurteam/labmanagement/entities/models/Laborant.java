package com.futurteam.labmanagement.entities.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Laborant implements Serializable {

    private static final long serialVersionUID = -2274387116203449440L;

    @Getter
    @NotNull
    private final String name;

    @Getter
    @NotNull
    private final List<LaborantNote> notes = new ArrayList<LaborantNote>() {

        private static final long serialVersionUID = -964456510123567524L;

        @Override
        public boolean add(LaborantNote o) {
            boolean add = super.add(o);
            sort(new LaborantNote.LaborantNoteComparator());
            return add;
        }
    };

    @Override
    public String toString() {
        return name;
    }
}
