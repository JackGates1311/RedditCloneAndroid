package com.example.sr2_2020_android2021_projekat.model;

import androidx.annotation.NonNull;

public class FileResponse {

    private String fileNames;
    private String message;

    public FileResponse(String fileNames, String message) {
        this.fileNames = fileNames;
        this.message = message;
    }

    public String getFileNames() {
        return fileNames;
    }

    public void setFileNames(String fileNames) {
        this.fileNames = fileNames;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @NonNull
    @Override
    public String toString() {
        return "FileResponse{" +
                "fileNames='" + fileNames + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
