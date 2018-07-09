package com.cooperativa.presentation.cooperado.visualization;


import com.cooperativa.db.entity.Cooperados;

public interface CooperadoContract {

    interface View{

        void showCooperadoInformation(Cooperados cooperado);


    }

    interface Presenter{


        void onViewResume(View view, Long cooperadoId);

        void onViewPause(View view);

    }
}
