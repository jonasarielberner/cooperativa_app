package com.cooperativa.model.datasource.database;

import com.cooperativa.db.dao.CooperadosDao;
import com.cooperativa.db.entity.Cooperados;

import io.reactivex.Single;

public class LocalCooperadoDataSource implements CooperadoDataSource {
    private static final String TAG = "LocalCooperadoDataSource";
    private CooperadosDao cooperadosDao;

    public Single<Cooperados> createNewCooperado(Cooperados cooperado) {
        return Single.create( e -> {
            try{
                //something
            }catch (Exception exp){
                e.onError(exp);
            }

        } );
    }

}
