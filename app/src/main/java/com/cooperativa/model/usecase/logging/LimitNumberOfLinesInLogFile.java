package com.cooperativa.model.usecase.logging;

import com.cooperativa.di.threads.PostExecutionThread;
import com.cooperativa.di.threads.ThreadExecutor;
import com.cooperativa.model.repository.logging.LoggingRepository;
import com.cooperativa.model.usecase.database.CompletableUseCase;

import javax.inject.Inject;

import io.reactivex.Completable;

public class LimitNumberOfLinesInLogFile extends CompletableUseCase<Void> {

    private LoggingRepository repository;

    @Inject
    public LimitNumberOfLinesInLogFile(ThreadExecutor threadExecutor,
                                       PostExecutionThread postExecutionThread,
                                       LoggingRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public Completable buildUseCaseObservable(Void aVoid) {
        return repository.limitNumberOfLinesInLogFile();
    }
}
