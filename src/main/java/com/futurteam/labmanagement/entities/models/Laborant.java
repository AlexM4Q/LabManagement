package com.futurteam.labmanagement.entities.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

@RequiredArgsConstructor
public class Laborant implements Serializable {

    private static final long serialVersionUID = -2274387116203449440L;

    @Getter
    @NotNull
    private final String name;

    @Getter
    @NotNull
    private final Set<LaborantNote> notes = new TreeSet<>();

    @Override
    public String toString() {
        return name;
    }
}
