package com.cooperativa.model.repository.logging;

import com.cooperativa.model.datasource.logging.LoggingDataSource;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class LoggingRepositoryImpl implements LoggingRepository {

    private LoggingDataSource dataSource;

    @Inject
    public LoggingRepositoryImpl(LoggingDataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Completable limitNumberOfLinesInLogFile() {
        return dataSource.limitNumberOfLinesInLogFile();
    }

    @Override
    public Single<File> getLogFile() {
        return dataSource.getLogFile();
    }
}
