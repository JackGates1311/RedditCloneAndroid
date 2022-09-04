package com.example.sr2_2020_android2021_projekat.tools;

import android.app.Dialog;
import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class DialogHelper {

    private Dialog currentDialog = null;
    private MaterialAlertDialogBuilder dialogBuilder = null;

    public void showDialog(Context context, String title, int view, Runnable onPositiveButtonClick,
                           Runnable onNegativeButtonClick, Runnable onDialogShowListener) {

        dialogBuilder(context, title, view, onPositiveButtonClick, onNegativeButtonClick);

        createDialog(onDialogShowListener);
    }

    public void showDialog(Context context, String title, int view, Runnable onPositiveButtonClick,
                             Runnable onNegativeButtonClick, Runnable onDialogShowListener,
                             String neutralButtonName, Runnable onNeutralButtonClick) {

        dialogBuilder(context, title, view, onPositiveButtonClick, onNegativeButtonClick).
                setNeutralButton(neutralButtonName, (dialogInterface, i) ->
                        onNeutralButtonClick.run());

        createDialog(onDialogShowListener);

    }

    private void createDialog(Runnable onDialogShowListener) {

        Dialog dialog = dialogBuilder.create();
        currentDialog = dialog;
        dialog.setOnShowListener(dialogInterface -> onDialogShowListener.run());
        dialog.show();
    }

    private MaterialAlertDialogBuilder dialogBuilder(Context context, String title, int view,
                                                     Runnable onPositiveButtonClick, Runnable onNegativeButtonClick) {
        dialogBuilder = new MaterialAlertDialogBuilder(context);

        return dialogBuilder.setTitle(title).setView(view)
                .setPositiveButton("OK",
                        (dialogInterface, i) -> onPositiveButtonClick.run())
                .setNegativeButton("Cancel", (dialogInterface, i) ->
                        onNegativeButtonClick.run());
    }

    public Dialog getCurrentDialog() {
        return currentDialog;
    }

    public void setCurrentDialog(Dialog currentDialog) {
        this.currentDialog = currentDialog;
    }

    public MaterialAlertDialogBuilder getDialogBuilder() {
        return dialogBuilder;
    }

    public void setDialogBuilder(MaterialAlertDialogBuilder dialogBuilder) {
        this.dialogBuilder = dialogBuilder;
    }
}
