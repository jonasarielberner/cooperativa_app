package com.cooperativa.model.usecase.database;


import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.di.threads.PostExecutionThread;
import com.cooperativa.di.threads.ThreadExecutor;
import com.cooperativa.model.repository.database.CooperadoRepository;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import io.reactivex.Single;


public class GetCooperadoInformation extends SingleUseCase<Cooperados, Long> {


    private CooperadoRepository repository;

    @Inject
    public GetCooperadoInformation(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CooperadoRepository repository) {
        super( threadExecutor, postExecutionThread );
        this.repository = repository;
    }


    @Override
    public Single<Cooperados> buildUseCaseObservable(Long cooperadoId) {
        Preconditions.checkNotNull(cooperadoId);
        return repository.getCooperadoInformation(cooperadoId);
    }
}
