package com.cooperativa.presentation.cooperado.visualization;


import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.cooperativa.db.entity.Cooperados;

import java.io.File;
import java.io.IOException;

public interface CooperadoContract {

    interface View{

        void showCooperadoInformation(Cooperados cooperado);


        void setClickListener(TextView suportingCooperadoTextView);

        void startPictureCaptureIntent( File file );
    }

    interface Presenter{


        void onViewResume(View view, Long cooperadoId);

        void onViewPause(View view);

        void changeTextOnCooperado(Long cooperadoId, String text, String TEXT_VIEW);

        void onUserWantsToRecordPhoto( Long cooperadoId);

        Bitmap onPhotoLoad(String cooperadoId, ImageView mImageView);
    }
}
