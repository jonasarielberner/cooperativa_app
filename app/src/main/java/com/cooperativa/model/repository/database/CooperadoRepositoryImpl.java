package com.cooperativa.model.repository.database;

import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.model.datasource.database.CooperadoDataSource;
import com.cooperativa.model.datasource.database.LocalCooperadoDataSource;
import com.cooperativa.presentation.cooperado.list.CooperadoSummary;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;

public class CooperadoRepositoryImpl implements CooperadoRepository {
    private CooperadoDataSource localCooperadoDataSource;

    @Inject
    public CooperadoRepositoryImpl (LocalCooperadoDataSource localCooperadoDataSource){
        this.localCooperadoDataSource = localCooperadoDataSource;
    }
    @Override
    public Single<Cooperados> createNewCooperado(Cooperados cooperados) {
        return localCooperadoDataSource.addCooperadoToDatabase(cooperados);
    }

    @Override
    public Completable deleteCooperado() {
        return null;
    }

    @Override
    public Single<Cooperados> getCooperadoInformation(Long cooperadoId) {
        return localCooperadoDataSource.getCooperadoInformation( cooperadoId );
    }

    @Override
    public Single<List<CooperadoSummary>> getCooperadosList() {
        return localCooperadoDataSource.getCooperadosList();
    }

    @Override
    public Completable updateCooperadoInformation(Cooperados cooperado) {
        return localCooperadoDataSource.update(cooperado);
    }


}
