package com.cooperativa.presentation.cooperado.visualization;


import android.widget.TextView;

import com.cooperativa.db.entity.Cooperados;

public interface CooperadoContract {

    interface View{

        void showCooperadoInformation(Cooperados cooperado);


        void setClickListener(TextView suportingCooperadoTextView);
    }

    interface Presenter{


        void onViewResume(View view, Long cooperadoId);

        void onViewPause(View view);

        void changeTextOnCooperado(Long cooperadoId, String text, String TEXT_VIEW);

    }
}
