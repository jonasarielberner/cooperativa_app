package com.cooperativa.presentation.cooperado.visualization.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.cooperativa.R;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.presentation.cooperado.visualization.CooperadoActivity;

public class TextViewDialog extends DialogFragment {

    private static final String TAG = "CooperadoDialog";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        CoopLog.d(TAG, "onCreateDialog: ");
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage( R.string.dialog_textview)
            .setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CoopLog.d(TAG, "onClick: ");
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it

        return builder.create();
    }



}
