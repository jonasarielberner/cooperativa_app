package com.cooperativa.presentation.cooperado.list;

import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.model.usecase.cooperado.GetCooperadosList;
import com.cooperativa.model.usecase.database.AddNewCooperado;
import com.cooperativa.widget.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class ListCooperadoPresenter extends BasePresenter implements ListCooperadoContract.Presenter {

    private static final String TAG = "ListCooperadoPresenter";

    private GetCooperadosList getCooperadosList;

    private AddNewCooperado addNewCooperado;

    @Inject
    public ListCooperadoPresenter(GetCooperadosList getCooperadosList, AddNewCooperado addNewCooperado) {
       this.getCooperadosList = getCooperadosList;
       this.addNewCooperado = addNewCooperado;
    }

    private ListCooperadoContract.View view;

    @Override
    public void onViewResume(ListCooperadoContract.View view, boolean dataLoaded) {
        CoopLog.d(TAG, "onViewCreated: " + view);
        attachView(view);
        if (!dataLoaded) {
            loadProperties();
        }

    }

    private void loadProperties() {
        CoopLog.d(TAG, "loadIssueSummariesFirstPage: ");

        loadCooperadosList();
    }

    private void loadCooperadosList() {
        CoopLog.d(TAG, "loadCooperadosList");
        getCooperadosList.execute( new DisposableSingleObserver<List<CooperadoSummary>>(){
            @Override
            public void onSuccess(List<CooperadoSummary> cooperadoList) {
                CoopLog.d(TAG, "loadIssueSummariesFirstPage: onSuccess: " + cooperadoList);
                CoopLog.i(TAG, "loadIssueSummariesFirstPage: onSuccess: size=" + cooperadoList.size());
                if (hasViewAttached()) {
                    if (cooperadoList.isEmpty() ) {
                        view.showEmptyListWarn();
                    } else {
                        view.showCooperadoList(cooperadoList);
                    }
                }
            }

            @Override
            public void onError(Throwable e) {

                CoopLog.e(TAG, "loadIssueSummariesFirstPage: onError: ", e);
                defaultErrorHandling(TAG, e);
                if (hasViewAttached()) {
                    view.showEmptyListWarn();
                }
            }
        });
    }

    private boolean hasViewAttached() {
        return view != null;
    }

    private void attachView(ListCooperadoContract.View view) {
        this.view = view;
    }

    @Override
    public void onViewPause(ListCooperadoContract.View view) {
        CoopLog.d(TAG, "onViewPause: " + view);
        detachView();
    }
    private void detachView() {
        this.view = null;
    }

    public void onClickNewCooperado(){
        CoopLog.d(TAG, "onClickNewIssue: ");
        addNewCooperado.execute( new DisposableSingleObserver<Cooperados>(){

            @Override
            public void onSuccess(Cooperados cooperados) {
                CoopLog.d(TAG, "loadIssueSummariesFirstPage: onSuccess: ");
                view.showCooperadoScreen();
            }

            @Override
            public void onError(Throwable e) {
                CoopLog.e(TAG, "loadIssueSummariesFirstPage: onError: ", e);
                defaultErrorHandling(TAG, e);
            }
        } );
    }

    @Override
    public void onViewDestroy(ListCooperadoContract.View view) {
        CoopLog.d(TAG, "onViewDestroy: ");
            }

    ListCooperadoContract.View getView() {
        return view;
    }
}
