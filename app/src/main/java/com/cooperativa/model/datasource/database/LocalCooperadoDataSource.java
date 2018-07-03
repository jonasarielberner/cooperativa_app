package com.cooperativa.model.datasource.database;

import com.cooperativa.db.dao.CooperadosDao;
import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.model.datasource.cooperado.CooperadosMapper;
import com.cooperativa.presentation.cooperado.list.CooperadoSummary;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Single;

public class LocalCooperadoDataSource implements CooperadoDataSource {
    private static final String TAG = "LocalCooperadoDataSource";
    private CooperadosDao cooperadosDao;
    private CooperadosMapper cooperadosMapper;

    @Inject
    public LocalCooperadoDataSource(CooperadosDao cooperadosDao,CooperadosMapper cooperadosMapper ) {
        this.cooperadosDao = cooperadosDao;
        this.cooperadosMapper = cooperadosMapper;
    }

    @Override
    public Single<Cooperados> addCooperadoToDatabase(Cooperados cooperado){

        return Single.create(e -> {
            try {
                Long cooperadoId = cooperadosDao.insertCooperado(cooperado);
                e.onSuccess(cooperadosDao.loadCooperadoSynchronous(cooperadoId));
            } catch (Exception exp) {
                e.onError(exp);
            }
        });
    }



    @Override
    public Single<Cooperados> getCooperadoInformation(Long cooperadoId) {
        return cooperadosDao.loadCooperado( cooperadoId );
    }

    @Override
    public Single<List<CooperadoSummary>> getCooperadosList() {

        return cooperadosDao.loadAllCooperados()
                .map( cooperados -> cooperadosMapper.toCooperadoSummary( cooperados ) );
    }


}
