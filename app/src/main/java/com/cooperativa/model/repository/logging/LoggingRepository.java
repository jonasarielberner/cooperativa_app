package com.cooperativa.model.repository.logging;


import java.io.File;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Repository for Logging
 *
 * @author João Luiz Vieira <joao.vieira@pixida.com.br>.
 */
public interface LoggingRepository {

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
