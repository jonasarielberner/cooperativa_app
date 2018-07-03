package com.cooperativa.model.datasource.logging;

import java.io.File;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface LoggingDataSource {

    /**
     * Deletes lines off limit of storage
     *
     * @return
     */
    Completable limitNumberOfLinesInLogFile();

    /**
     * Returns a temporary file used to share
     *
     * @return the single with the file
     */
    Single<File> getLogFile();
}