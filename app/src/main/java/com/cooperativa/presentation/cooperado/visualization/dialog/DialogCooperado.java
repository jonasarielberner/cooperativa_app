package com.cooperativa.presentation.cooperado.visualization.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
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
import com.cooperativa.db.entity.Cooperados;
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
    EditText dialogTextInput;

    private OnListFragmentInteractionListener mListener;



    public DialogCooperado( ) {
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

        builder.setMessage( R.string.dialog_textview);
        builder.setView(rootView);
        builder.setPositiveButton(R.string.accept, (dialog, which) -> {
            onAcceptButton();
        });

        builder.setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss());

        return builder.create();
    }

    private void onAcceptButton(){
        CoopLog.d(TAG, "onClick: ");
        mListener.onListFragmentInteraction(
                dialogTextInput.getText()
                        .toString());

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

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnListFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(String text);
    }
}
