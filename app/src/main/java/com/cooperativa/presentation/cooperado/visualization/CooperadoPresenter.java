package com.cooperativa.presentation.cooperado.visualization;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.model.UseCaseCallback;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.model.usecase.cooperado.UpdateCooperadoInfo;
import com.cooperativa.model.usecase.database.GetCooperadoInformation;
import com.cooperativa.model.usecase.folder.GetCooperadoPictureFolder;
import com.cooperativa.model.usecase.photo.GetNewPhotoFile;
import com.cooperativa.widget.BasePresenter;

import java.io.File;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

public class CooperadoPresenter extends BasePresenter implements CooperadoContract.Presenter {

    private static final String TAG = "CooperadoPresenter";

    private CooperadoContract.View view;

    @Inject
    GetCooperadoInformation getCooperadoInformation;

    @Inject
    UpdateCooperadoInfo updateCooperadoInfo;

    @Inject
    GetNewPhotoFile getNewPhotoFile;
    @Inject
    GetCooperadoPictureFolder getCooperadoPictureFolder;


    public String mCurrentPhotoPath;




    @Inject
    public CooperadoPresenter(GetCooperadoInformation getCooperadoInformation, UpdateCooperadoInfo updateCooperadoInfo, GetNewPhotoFile getNewPhotoFile, GetCooperadoPictureFolder getCooperadoPictureFolder) {
        this.getCooperadoInformation = getCooperadoInformation;
        this.updateCooperadoInfo = updateCooperadoInfo;
        this.getNewPhotoFile = getNewPhotoFile;
        this.getCooperadoPictureFolder = getCooperadoPictureFolder;

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

        detachView(view);

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




    private void detachView(CooperadoContract.View view) {
        this.view = null;
    }



    @Override
    public void onUserWantsToRecordPhoto( Long cooperadoId ) {
        CoopLog.d( TAG, "onUserWantsToRecordPhoto: " + cooperadoId );

        getNewPhotoFile.execute( String.valueOf( cooperadoId ), new UseCaseCallback<File>() {
            @Override
            public void onSuccess(File file) {
                CoopLog.d( TAG, "getNewPhotoFile: onSuccess: " + file.toString() );
                if (hasViewAttached()) {
                    view.startPictureCaptureIntent( file );
                }
            }

            @Override
            public void onError(Exception e) {
                CoopLog.d( TAG, "getNewPhotoFile: onError: " );
                defaultErrorHandling( TAG, e );
            }
        } );
    }

    @Override
    public Bitmap onPhotoLoad(String cooperadoId, ImageView mImageView) {
        getCooperadoPictureFolder.execute( cooperadoId, new UseCaseCallback<File>() {

            @Override
            public void onSuccess(File data) {
                CoopLog.d( TAG, "onPhotoLoad: onSuccess: " +  data.listFiles() );


                mCurrentPhotoPath= data.getPath();
                CoopLog.d( TAG, "onPhotoLoad: onSuccess: " + mCurrentPhotoPath );

            }

            @Override
            public void onError(Exception e) {

            }
        } );
        BitmapFactory.Options bmOptions = loadPhotoOptions(mImageView);
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        return bitmap;
    }



    private BitmapFactory.Options loadPhotoOptions(ImageView mImageView){
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();

        /*int targetW = mImageView.getWidth();
        int targetH = mImageView.getHeight();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);*/

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        //bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return bmOptions;

    }


    CooperadoContract.View getView() {
        return view;
    }

}
