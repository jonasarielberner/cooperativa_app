package com.cooperativa.model.datasource.logging;


import android.support.annotation.NonNull;

import timber.log.Timber;

/**
 * Responsible to encapsulate logs
 *
 * @author João Luiz Vieira <joao.vieira@pixida.com.br>.
 */

public final class CoopLog {
    private CoopLog() {
        //required private constructor
    }

    /**
     * Log a debug message with optional format args.
     */
    public static void i(@NonNull String tag, @NonNull String message, Object... args) {
        Timber.tag(tag);
        Timber.i(message, args);
    }

    /**
     * Log a debug message with optional format args.
     */
    public static void d(@NonNull String tag, @NonNull String message, Object... args) {
        Timber.tag(tag);
        Timber.d(message, args);
    }

    /**
     * Log an error exception and a message with optional format args.
     */
    public static void e(@NonNull String tag, String message, @NonNull Throwable t) {
        Timber.tag(tag);
        Timber.e(t, message);
    }

    /**
     * Log a warning exception and a message with optional format args.
     */
    public static void w(@NonNull String tag, Throwable t, String message, Object... args) {
        Timber.tag(tag);
        Timber.w(t, message, args);
    }

}
