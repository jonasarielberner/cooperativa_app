package com.cooperativa.presentation.main;

import com.cooperativa.widget.BasePresenter;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter implements MainActivityContract.Presenter{

    private MainActivityContract.View view;

    @Inject
    public MainPresenter() {
        //Empty Constructor for DI
    }

    @Override
    public void onViewResume(MainActivityContract.View view) {
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
        detachView(view);
        }

    private void detachView(MainActivityContract.View view) {
        this.view = null;
    }


        MainActivityContract.View getView() {
        return view;
    }

}
