package com.cooperativa.model.repository.database;

import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.model.datasource.database.CooperadoDataSource;
import com.cooperativa.model.datasource.database.LocalCooperadoDataSource;

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
    public Single<Cooperados> createNewCooperado() {
        return null;
    }

    @Override
    public Completable deleteCooperado() {
        return null;
    }
}
