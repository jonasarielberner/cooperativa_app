package com.cooperativa.presentation.cooperado.list;

import java.util.List;

public interface ListCooperadoContract {

    interface View{
        void showEmptyListWarn();

        void showCooperadoScreen();

        void showCooperadoList(List<CooperadoSummary> cooperadoList);

    }

    interface Presenter{

        void onViewResume(View view, boolean dataLoaded);

        void onViewPause(View view);

        void onClickNewCooperado();

        void onViewDestroy(View view);

    }
}
