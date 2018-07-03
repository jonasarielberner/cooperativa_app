package com.cooperativa.presentation.about;

import com.cooperativa.widget.BasePresenter;

import javax.inject.Inject;

public class AboutPresenter extends BasePresenter implements AboutContract.Presenter {

    private AboutContract.View view;

    @Inject
    public AboutPresenter(){

    }

    @Override
    public void onViewResume(AboutContract.View view) {
        attachView(view);
    }

    private void attachView(AboutContract.View view) {
        this.view = view;
    }
    private boolean hasViewAttached() {
        return view != null;
    }

    @Override
    public void onViewPause(AboutContract.View view) {
        detachView(view);
    }
    private void detachView(AboutContract.View view) {
        this.view = null;
    }

    AboutContract.View getView() {
        return view;
    }

}
