package com.cooperativa.presentation.about;


public interface AboutContract {

    interface View {

        void showAppVersion(String appVersion);
    }

    interface Presenter {
        void onViewResume(View view);

        void onViewPause(View view);
    }
}
