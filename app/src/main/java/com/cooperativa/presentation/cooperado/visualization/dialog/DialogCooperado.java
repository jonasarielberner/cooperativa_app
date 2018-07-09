package com.cooperativa.presentation.cooperado.visualization.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.cooperativa.CooperativaApplication;
import com.cooperativa.R;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.presentation.cooperado.visualization.CooperadoActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DialogCooperado extends DialogFragment implements DialogContract.View {

    private static final String TAG = "CooperadoDialog";



    @Inject
    DialogContract.Presenter presenter;

    @BindView(R.id.dialog_edit_text)
    TextInputLayout dialogTextInput;

    public DialogCooperado() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        CoopLog.d(TAG, "onCreateDialog: ");

        // Use the Builder class for convenient dialog construction

        ((CooperativaApplication) getActivity().getApplication()).component().inject(this);

        View rootView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_cooperado, null);
        ButterKnife.bind(this, rootView);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        CoopLog.d(TAG, "onCreateDialog: Ponto 2");


        builder.setMessage( R.string.dialog_textview);
        builder.setView(rootView);
        builder.setPositiveButton(R.string.accept, (dialog, which) -> {

                        CoopLog.d(TAG, "onClick: ");
                        presenter.onUserWantsToUpdateText(
                                dialogTextInput.getEditText().getText().toString());
        });

        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onViewResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onViewPaused(this);
    }



}
