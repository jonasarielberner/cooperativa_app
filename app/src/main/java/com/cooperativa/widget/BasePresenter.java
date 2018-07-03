package com.cooperativa.widget;

import android.util.Log;


/**
 * This base class wraps common behaviors from the presenters.
 * Created by cedulio.silva on 12/8/2017.
 */

public class BasePresenter {

    /**
     * Log the error with the default log mechanism.
     */
    protected void defaultErrorHandling(String tag, Throwable e) {
        Log.e(tag, Log.getStackTraceString(e), e);
    }

}
