package com.cooperativa.model.usecase.database;

import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.di.threads.PostExecutionThread;
import com.cooperativa.di.threads.ThreadExecutor;
import com.cooperativa.model.repository.database.CooperadoRepository;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import io.reactivex.Single;

public class AddNewCooperado extends SingleUseCase<Cooperados, Cooperados> {

    private CooperadoRepository cooperadoRepository;

    @Inject
    public AddNewCooperado(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CooperadoRepository cooperadoRepository) {
        super(threadExecutor, postExecutionThread);
        this.cooperadoRepository = cooperadoRepository;
    }

    @Override
    public Single<Cooperados> buildUseCaseObservable(Cooperados cooperado) {
        //Preconditions.checkNotNull(cooperado);
        return cooperadoRepository.createNewCooperado(cooperado);
    }
}

