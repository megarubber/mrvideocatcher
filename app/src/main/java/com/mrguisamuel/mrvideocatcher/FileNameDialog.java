package com.mrguisamuel.mrvideocatcher;

import android.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class FileNameDialog {

    public static String createFileNameDialog(AppCompatActivity myContext) {
        String name = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
        builder.setTitle("Choose a file name");

        final View customLayout = myContext.getLayoutInflater().inflate(R.drawable.name_alert);
        builder.setView(customLayout);

        EditText inputName = (EditText) customLayout.findByViewId(R.id.file_name);

        builder.setPositiveButton("Ok", ((dialogInterface, i) -> {

        }));
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
