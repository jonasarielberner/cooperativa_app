package com.cooperativa.model.usecase.logging;



import com.cooperativa.di.threads.PostExecutionThread;
import com.cooperativa.di.threads.ThreadExecutor;
import com.cooperativa.model.repository.logging.LoggingRepository;
import com.cooperativa.model.usecase.database.SingleUseCase;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Get the log file
 *
 * @author João Luiz Vieira <joao.vieira@pixida.com.br>.
 */

public class GetLogFile extends SingleUseCase<File, Void> {

    private LoggingRepository repository;

    @Inject
    public GetLogFile(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, LoggingRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    public Single<File> buildUseCaseObservable(Void aVoid) {
        return repository.getLogFile();
    }
}
