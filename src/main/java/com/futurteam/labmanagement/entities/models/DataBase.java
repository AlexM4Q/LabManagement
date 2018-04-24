package com.futurteam.labmanagement.entities.models;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataBase implements Serializable {

    @Getter
    private final List<Worker> workers;

    public DataBase() {
        this.workers = new ArrayList<Worker>();
    }


}
