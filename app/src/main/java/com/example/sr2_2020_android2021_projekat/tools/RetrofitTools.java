package com.example.sr2_2020_android2021_projekat.tools;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class RetrofitTools {

    public static void showConnectionErrorMessage(@NonNull Throwable t, @NonNull View view) {
        Snackbar.make(view, "Connection with server is not established",
                Snackbar.LENGTH_LONG).setAction("Details", viewDetails -> new
                MaterialAlertDialogBuilder(view.getContext())
                .setTitle("Error details")
                .setMessage(t.getMessage())
                .setPositiveButton("OK", (dialogInterface, i) -> {
                }).setCancelable(true).show()).show();
    }

    public static void showResponseErrorMessage(int code, View view) {
        Toast.makeText(view.getContext(), "HTTP returned code " + code,
                Toast.LENGTH_LONG).show();
    }
}
