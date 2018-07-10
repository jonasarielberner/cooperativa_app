package com.cooperativa.model.repository.database;

import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.presentation.cooperado.list.CooperadoSummary;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface CooperadoRepository {

    Single<Cooperados> createNewCooperado(Cooperados cooperado);

    Completable deleteCooperado();

    Single<Cooperados> getCooperadoInformation(Long cooperadoId);

    Single<List<CooperadoSummary>> getCooperadosList();


    Completable updateCooperadoInformation(Cooperados cooperadoId);
}
