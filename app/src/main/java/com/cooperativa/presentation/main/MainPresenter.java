package com.cooperativa.presentation.main;

import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.widget.BasePresenter;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter implements MainActivityContract.Presenter{
    private static final String TAG = "MainPresenter";

    private MainActivityContract.View view;


    @Inject
    public MainPresenter() {
        //Empty Constructor for DI
    }

    @Override
    public void onViewResume(MainActivityContract.View view) {
        CoopLog.d(TAG, "onViewResume: ");
        attachView(view);
    }

    private void attachView(MainActivityContract.View view) {
        this.view = view;
    }
    private boolean hasViewAttached() {
        return view != null;
    }


        @Override
    public void onViewPause(MainActivityContract.View view) {
            CoopLog.d(TAG, "onViewPause: ");

            detachView(view);
        }

    private void detachView(MainActivityContract.View view) {
        this.view = null;
    }


        MainActivityContract.View getView() {
        return view;
    }

}
