package com.futurteam.labmanagement.entities.models;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataBase implements Serializable {

    private static final long serialVersionUID = -8192938742236107798L;

    @Getter
    private final List<Laborant> laborants = new ArrayList<>();

}
