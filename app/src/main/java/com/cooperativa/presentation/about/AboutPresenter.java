package com.cooperativa.presentation.about;

import com.cooperativa.model.UseCaseCallback;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.model.usecase.version.GetAppVersion;
import com.cooperativa.widget.BasePresenter;

import javax.inject.Inject;

public class AboutPresenter extends BasePresenter implements AboutContract.Presenter {

    private static final String TAG = "AboutPresenter";

    private AboutContract.View view;

    @Inject
    GetAppVersion getAppVersion;

    @Inject
    public AboutPresenter(GetAppVersion getAppVersion) {
        this.getAppVersion = getAppVersion;
    }

    @Override
    public void onViewResume(AboutContract.View view) {
        attachView(view);
        loadProperties();
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


    private void loadProperties() {
        loadAppVersion();
    }
    void loadAppVersion() {
        CoopLog.d(TAG, "loadAppVersion: ");
        getAppVersion.execute(new UseCaseCallback<String>() {
            @Override
            public void onSuccess(String data) {
                CoopLog.d(TAG, "loadAppVersion:  onSuccess: " + data);
                if (hasViewAttached()) {
                    view.showAppVersion(data);
                }
            }

            @Override
            public void onError(Exception e) {
                CoopLog.d(TAG, "loadAppVersion: onError: ");
                defaultErrorHandling(TAG, e);
            }
        });
    }

}
