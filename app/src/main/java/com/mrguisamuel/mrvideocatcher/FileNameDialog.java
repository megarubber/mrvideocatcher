package com.mrguisamuel.mrvideocatcher;

import android.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Callable;

public class FileNameDialog {
    public static String name = "";

    public static void createFileNameDialog(
            AppCompatActivity myContext,
            Callable<Void> execute
    ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
        builder.setTitle("Choose a file name");

        View customLayout = myContext.getLayoutInflater().inflate(R.layout.name_alert, null);
        builder.setView(customLayout);

        EditText inputName = customLayout.findViewById(R.id.file_name);
        Button confirm = customLayout.findViewById(R.id.confirm);
        Button cancel = customLayout.findViewById(R.id.cancel);

        /*
        builder.setPositiveButton("Ok", ((dialogInterface, i) -> {
            name = inputName.getText().toString();
        }));
        */

        AlertDialog dialog = builder.create();
        dialog.show();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = inputName.getText().toString();
                if(name.length() > 0) {
                    try {
                        execute.call();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    dialog.dismiss();
                } else {
                    Toast.makeText(
                            myContext, "Invalid name!", Toast.LENGTH_SHORT
                    ).show();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}