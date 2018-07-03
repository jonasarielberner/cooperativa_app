package com.cooperativa.presentation.cooperado.visualization;

import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.model.usecase.database.AddNewCooperado;
import com.cooperativa.model.usecase.database.GetCooperadoInformation;
import com.cooperativa.widget.BasePresenter;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;

public class CooperadoPresenter extends BasePresenter implements CooperadoContract.Presenter {

    private static final String TAG = "CooperadoPresenter";

    private CooperadoContract.View view;

    private Long cooperadoId;

    @Inject
    GetCooperadoInformation getCooperadoInformation;

    @Inject
    AddNewCooperado addNewCooperado;

    @Inject
    public CooperadoPresenter(GetCooperadoInformation getCooperadoInformation, AddNewCooperado addNewCooperado) {
        this.getCooperadoInformation = getCooperadoInformation;
        this.addNewCooperado = addNewCooperado;
    }

    @Override
    public void onViewResume(CooperadoContract.View view) {
        CoopLog.d(TAG, "onViewResume: ");
        attachView(view);
        if (cooperadoId!=null){
            loadCooperado(cooperadoId);
        }else{
            newCooperado();
        }
    }

    private void attachView(CooperadoContract.View view) {
        this.view = view;
    }
    private boolean hasViewAttached() {
        return view != null;
    }

    private void loadCooperado(Long cooperadoId) {
        CoopLog.d(TAG, "loadCooperado: ");

        getCooperadoInformation.execute( cooperadoId, new DisposableSingleObserver<Cooperados>() {
            @Override
            public void onSuccess(Cooperados cooperado) {
                if ((hasViewAttached())) {
                    view.showCooperadoInformation( cooperado );
                }

            }

            @Override
            public void onError(Throwable e) {
                defaultErrorHandling( TAG, e );

            }
        } );
    }

    private void newCooperado( ){
        CoopLog.d(TAG, "newCooperado: ");

        addNewCooperado.execute(new DisposableSingleObserver<Cooperados>() {
            @Override
            public void onSuccess(Cooperados cooperado) {
                if ((hasViewAttached())) {
                    view.showCooperadoInformation( cooperado );
                }

            }

            @Override
            public void onError(Throwable e) {
                defaultErrorHandling( TAG, e );

            }
        } );

        Cooperados cooperado = new Cooperados(  );
        //setar um id
        cooperado.setId( 0l );
        cooperado.setName( "Jonas" );
        cooperado.setRole( "Boss" );

    }


    @Override
    public void onViewPause(CooperadoContract.View view) {
        CoopLog.d(TAG, "onViewPause: ");

        detachView(view);
    }

    private void detachView(CooperadoContract.View view) {
        this.view = null;
    }


    CooperadoContract.View getView() {
        return view;
    }
}
