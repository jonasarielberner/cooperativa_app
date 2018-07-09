package com.cooperativa.model.datasource.database;

import android.provider.MediaStore;
import android.support.annotation.NonNull;

import com.cooperativa.db.dao.CooperadosDao;
import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.model.datasource.cooperado.CooperadosMapper;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.presentation.cooperado.list.CooperadoSummary;

import java.util.ArrayList;
import java.util.Date;
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
    public Single<Cooperados> addCooperadoToDatabase(Cooperados cooperados){

        return Single.create(e -> {
            try {

                Cooperados cooperado = newCooperado();

                Long cooperadoId = cooperadosDao.insertCooperado(cooperado);

                e.onSuccess(cooperadosDao.loadCooperadoSynchronous(cooperadoId));
                CoopLog.d( TAG, "onResume: " + cooperadoId );

            } catch (Exception exp) {
                e.onError(exp);
            }
        });
    }

    @NonNull
    private Cooperados newCooperado() {

        Cooperados newCooperado = new Cooperados("Jonas",
                "",
                "");

        return newCooperado;
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
