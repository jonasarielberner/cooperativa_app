package com.cooperativa.model.usecase.configuration;



import com.cooperativa.di.threads.PostExecutionThread;
import com.cooperativa.di.threads.ThreadExecutor;
import com.cooperativa.model.repository.configuration.ConfigurationRepository;
import com.cooperativa.model.usecase.database.SingleUseCase;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by cedulio.silva on 1/30/2018.
 */

public class LoadConfiguration extends SingleUseCase<Boolean, Void> {

    private ConfigurationRepository configurationRepository;

    @Inject
    public LoadConfiguration(ThreadExecutor threadExecutor,
                             PostExecutionThread postExecutionThread,
                             ConfigurationRepository configurationRepository) {
        super(threadExecutor, postExecutionThread);
        this.configurationRepository = configurationRepository;
    }

    @Override
    public Single<Boolean> buildUseCaseObservable(Void aVoid) {
        return configurationRepository.loadPreviousConfiguration();
    }
}
