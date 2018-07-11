package com.cooperativa.presentation.cooperado.visualization;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cooperativa.BuildConfig;
import com.cooperativa.CooperativaApplication;
import com.cooperativa.R;
import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.presentation.cooperado.visualization.dialog.DialogCooperado;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static java.security.AccessController.getContext;

public class CooperadoActivity extends AppCompatActivity implements CooperadoContract.View, DialogCooperado.OnListFragmentInteractionListener{

    private static final String TAG = "CooperadoActivity";
    public static final String COOPERADO_ID = "cooperado_id";
    public static final int CAMERA_REQUEST = 1;
    private static final int REQUEST_PERMISSIONS_CODE = 10;


    private  String TEXT_VIEW = "text_view";


    private Long cooperadoId;

    @Inject
    CooperadoContract.Presenter presenter;

    @BindView(R.id.nome_cooperado)
    TextView nomeCooperadoTextView;

    @BindView(R.id.role_cooperado)
    TextView roleCooperadoTextView;

    @BindView(R.id.suporting_text_cooperado)
    TextView suportingCooperadoTextView;

    @BindView( R.id.image_view_cooperado )
    ImageView cooperadoImageView;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_cooperado );

        ButterKnife.bind(this);
        ((CooperativaApplication) getApplication()).component().inject(this);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }

        setClickListener(suportingCooperadoTextView);
        setClickListener(roleCooperadoTextView);
        setClickListener(nomeCooperadoTextView);


        interceptIntent();
    }


    private void interceptIntent() {
        if (hasIssueIdExtra()) {
            cooperadoId = getIntent().getExtras().getLong(COOPERADO_ID);
            CoopLog.d( TAG, "interceptIntent: ok: " + cooperadoId);

        } else {
            CoopLog.d( TAG, "interceptIntent: nok: ");
        }
    }

    private boolean hasIssueIdExtra() {
        return getIntent() != null && getIntent().hasExtra(COOPERADO_ID);
    }


    @Override
    protected void onResume() {
        super.onResume();
        CoopLog.d(TAG, "onResume: " + cooperadoId);
        presenter.onViewResume( this , cooperadoId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        CoopLog.d(TAG, "onPause: ");
        presenter.onViewPause( this );
    }

    public void showCooperadoInformation(Cooperados cooperado){

        nomeCooperadoTextView.setText(cooperado.getName());
        roleCooperadoTextView.setText(cooperado.getRole());
        suportingCooperadoTextView.setText(cooperado.getDescription());

        Bitmap bitmap = presenter.onPhotoLoad(cooperadoId.toString(), cooperadoImageView);

        cooperadoImageView.setImageBitmap(bitmap);

        //cooperadoImageView;
        //tratar o cooperado
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(String text) {
        presenter.changeTextOnCooperado(cooperadoId, text, TEXT_VIEW);

    }


    @Override
    public void setClickListener(TextView view) {
        CoopLog.d(TAG, "onClickListener: ");
        view.setOnClickListener( v -> {
            CoopLog.d(TAG, "onClick: " + v.getTag());
            DialogFragment newFragment = new DialogCooperado();
            newFragment.show(getFragmentManager(), "missiles");
            TEXT_VIEW = v.getTag().toString();

        } );
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @OnClick(R.id.image_view_cooperado)
    public void onRecordPhotoClick() {
        if (checkPermission()) {
            presenter.onUserWantsToRecordPhoto(cooperadoId);
        } else {
            requestPermissions();
        }
    }

    public boolean checkPermission() {
        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermissions() {

        requestPermissions(new
                String[]{WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSIONS_CODE);
    }


    @Override
    public void startPictureCaptureIntent(File file) {
        dispatchRecordPhotoIntent(file);
    }


    private void dispatchRecordPhotoIntent(File file) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT < 24) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        } else {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file));
        }
        startActivityForResult(takePictureIntent, CAMERA_REQUEST);
    }

    private void handleActivityResultPhoto(int resultCode) {
        if (resultCode == RESULT_OK) {
            CoopLog.i(TAG, "onActivityResult: Image saved!");
            Toast.makeText(this, getString(R.string.picture_save_successful), Toast.LENGTH_SHORT).show();
        } else if (resultCode == RESULT_CANCELED) {
            CoopLog.d(TAG, "onActivityResult: User cancelled the image recording");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CAMERA_REQUEST:
                handleActivityResultPhoto(resultCode);
                break;
            default:
                CoopLog.d(TAG, "onActivityResult: default");
        }
    }


}
