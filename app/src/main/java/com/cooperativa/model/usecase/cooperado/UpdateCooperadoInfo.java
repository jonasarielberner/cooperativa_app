package com.cooperativa.model.usecase.cooperado;

import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.di.threads.PostExecutionThread;
import com.cooperativa.di.threads.ThreadExecutor;
import com.cooperativa.model.repository.database.CooperadoRepository;
import com.cooperativa.model.usecase.database.CompletableUseCase;
import com.cooperativa.model.usecase.database.SingleUseCase;
import com.cooperativa.presentation.cooperado.list.CooperadoSummary;

import java.util.List;

import javax.inject.Inject;

import dagger.internal.Preconditions;
import io.reactivex.Completable;
import io.reactivex.Single;

public class UpdateCooperadoInfo extends CompletableUseCase<Cooperados> {

    private CooperadoRepository repository;

    @Inject
    public UpdateCooperadoInfo(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CooperadoRepository repository) {
        super( threadExecutor, postExecutionThread );
        this.repository = repository;
    }

    @Override
    public Completable buildUseCaseObservable(Cooperados cooperados) {
        Preconditions.checkNotNull(cooperados);

        return repository.updateCooperadoInformation( cooperados );
    }
}

