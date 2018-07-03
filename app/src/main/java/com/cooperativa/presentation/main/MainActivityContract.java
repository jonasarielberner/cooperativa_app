package com.cooperativa.presentation.main;

public interface MainActivityContract {

    interface View{


    }

    interface Presenter{

        /**
         * Called when the view enters on resume state, generally this are loaded here.
         */
        void onViewResume(View view);

        /**
         * Called when then view enters on pause state, usually this is the time to release strong
         * references to an Activity or fragment for example.
         */
        void onViewPause(View view);


    }
}
