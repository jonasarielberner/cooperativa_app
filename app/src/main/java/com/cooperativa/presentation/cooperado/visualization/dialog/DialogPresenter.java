package com.cooperativa.presentation.cooperado.visualization.dialog;

import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.widget.BasePresenter;

import javax.inject.Inject;

public class DialogPresenter extends BasePresenter implements DialogContract.Presenter{


    private static final String TAG = "DialogPresenter";

    private DialogContract.View view;

    @Inject
    public DialogPresenter() {
    }

    @Override
    public void onViewResume(DialogContract.View view) {
        CoopLog.d(TAG, "onViewCreated: ");
        attachView(view);

    }

    @Override
    public void onViewPaused(DialogContract.View view) {

    }


    @Override
    public void onUserWantsToUpdateText(String text) {


    }

    private boolean hasViewAttached() {
        return view != null;
    }

    DialogContract.View getView() {
        return view;
    }

    private void attachView(DialogContract.View view) {
        CoopLog.d(TAG, "attachView: " + view.toString());
        this.view = view;
    }

    private void detachView(DialogContract.View view) {
        CoopLog.d(TAG, "detachView: " + view.toString());
        this.view = null;
    }
}
