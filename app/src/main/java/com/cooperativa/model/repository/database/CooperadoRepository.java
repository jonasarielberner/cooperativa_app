package com.cooperativa.model.repository.database;

import com.cooperativa.db.entity.Cooperados;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface CooperadoRepository {

    Single<Cooperados> createNewCooperado();

    Completable deleteCooperado();
}
