package com.cooperativa.model.usecase.cooperado;

import com.cooperativa.di.threads.PostExecutionThread;
import com.cooperativa.di.threads.ThreadExecutor;
import com.cooperativa.model.repository.database.CooperadoRepository;
import com.cooperativa.model.usecase.database.SingleUseCase;
import com.cooperativa.presentation.cooperado.list.CooperadoSummary;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetCooperadosList extends SingleUseCase <List<CooperadoSummary>, Object> {


    private CooperadoRepository cooperadoRepository;

    @Inject
    public GetCooperadosList(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, CooperadoRepository cooperadoRepository) {
        super( threadExecutor, postExecutionThread );
        this.cooperadoRepository = cooperadoRepository;
    }

    @Override
    public Single<List<CooperadoSummary>> buildUseCaseObservable(Object o) {
        return cooperadoRepository.getCooperadosList();
    }
}


