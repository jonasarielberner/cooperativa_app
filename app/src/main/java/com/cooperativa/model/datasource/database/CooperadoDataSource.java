package com.cooperativa.model.datasource.database;

import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.presentation.cooperado.list.CooperadoSummary;

import java.util.List;

import io.reactivex.Single;

public interface CooperadoDataSource  {

    Single<Cooperados> addCooperadoToDatabase(Cooperados cooperados);

    Single<Cooperados> getCooperadoInformation(Long cooperadoId);

    Single<List<CooperadoSummary>> getCooperadosList();

}
