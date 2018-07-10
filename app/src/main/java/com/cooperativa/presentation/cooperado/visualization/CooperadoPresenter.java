package com.cooperativa.presentation.cooperado.visualization;

import android.view.View;
import android.widget.TextView;

import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.model.usecase.cooperado.UpdateCooperadoInfo;
import com.cooperativa.model.usecase.database.GetCooperadoInformation;
import com.cooperativa.widget.BasePresenter;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class CooperadoPresenter extends BasePresenter implements CooperadoContract.Presenter {

    private static final String TAG = "CooperadoPresenter";



    private CooperadoContract.View view;

    private Long cooperadoId;//tem que pegar a info desse cooperado clicado

    @Inject
    GetCooperadoInformation getCooperadoInformation;

    @Inject
    UpdateCooperadoInfo updateCooperadoInfo;



    @Inject
    public CooperadoPresenter(GetCooperadoInformation getCooperadoInformation, UpdateCooperadoInfo updateCooperadoInfo) {
        this.getCooperadoInformation = getCooperadoInformation;
        this.updateCooperadoInfo = updateCooperadoInfo;
    }

    @Override
    public void onViewResume(CooperadoContract.View view, Long cooperadoId) {
        CoopLog.d(TAG, "onViewResume: ");
        attachView(view);
        if (cooperadoId!=null){
            CoopLog.d(TAG, "onViewResume: ID: " + cooperadoId);
            loadCooperado( cooperadoId );
        }else{
            CoopLog.d(TAG, "onViewResume: no Cooperado selected " + cooperadoId);

        }

    }

    @Override
    public void onViewPause(CooperadoContract.View view) {
        CoopLog.d(TAG, "onViewPause: ");

        detachView( (View) view );

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
            public void onSuccess(Cooperados cooperados) {
                if ((hasViewAttached())) {

                    view.showCooperadoInformation( cooperados );

                } }
            @Override
            public void onError(Throwable e) {
                defaultErrorHandling( TAG, e );
            }
        } );
    }


    @Override
    public void changeTextOnCooperado(Long cooperadoId, String text, String TEXT_VIEW) {
        getCooperadoInformation.execute( cooperadoId, new DisposableSingleObserver<Cooperados>() {
            @Override
            public void onSuccess(Cooperados cooperado) {
                if ((hasViewAttached())) {
                    switch (TEXT_VIEW){
                        case "description":
                            cooperado.setDescription( text );
                            CoopLog.d(TAG, "changeTextOnCooperado: " + TEXT_VIEW);
                            break;

                        case "role":
                            cooperado.setRole( text );
                            CoopLog.d(TAG, "changeTextOnCooperado: " + TEXT_VIEW);
                            break;


                        case "name":
                            cooperado.setName( text );
                            CoopLog.d(TAG, "changeTextOnCooperado: " + TEXT_VIEW);
                            break;
                    }
                }
                updateCooperado(cooperado);
                loadCooperado( cooperadoId );
            }
            @Override
            public void onError(Throwable e) {
                defaultErrorHandling( TAG, e );
            }} );
    }

    private void updateCooperado (Cooperados cooperado){
        updateCooperadoInfo.execute(cooperado , new DisposableCompletableObserver(){
            @Override
            public void onComplete() {
                CoopLog.d(TAG, "updateCooperado: " + cooperado.toString());

            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }




    private void detachView(View view) {
        this.view = null;
    }


    CooperadoContract.View getView() {
        return view;
    }



}
