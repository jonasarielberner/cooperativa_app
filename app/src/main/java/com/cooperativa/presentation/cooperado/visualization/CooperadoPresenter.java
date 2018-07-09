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

    private Long cooperadoId;//tem que pegar a info desse cooperado clicado

    @Inject
    GetCooperadoInformation getCooperadoInformation;


    @Inject
    public CooperadoPresenter(GetCooperadoInformation getCooperadoInformation) {
        this.getCooperadoInformation = getCooperadoInformation;
    }

    @Override
    public void onViewResume(CooperadoContract.View view, Long cooperadoId) {
        CoopLog.d(TAG, "onViewResume: ");
        attachView(view);
        if (cooperadoId!=null){
            CoopLog.d(TAG, "onViewResume: ID: " + cooperadoId);
            loadCooperado(cooperadoId);
        }else{
            CoopLog.d(TAG, "onViewResume: no Cooperado selected " + cooperadoId);

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
