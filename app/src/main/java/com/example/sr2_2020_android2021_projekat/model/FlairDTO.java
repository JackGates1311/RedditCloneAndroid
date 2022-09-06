package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

public class FlairDTO {

    private Long flairId;
    private String name;

    public FlairDTO(Long flairId, String name) {
        this.flairId = flairId;
        this.name = name;
    }

    public FlairDTO(String name) {
        this.name = name;
    }

    public Long getFlairId() {
        return flairId;
    }

    public void setFlairId(Long flairId) {
        this.flairId = flairId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "FlairDTO{" +
                "flairId=" + flairId +
                ", name='" + name + '\'' +
                '}';
    }
}
