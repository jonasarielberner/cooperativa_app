package com.cooperativa.presentation.cooperado.visualization;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cooperativa.CooperativaApplication;
import com.cooperativa.R;
import com.cooperativa.db.entity.Cooperados;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.presentation.cooperado.visualization.dialog.DialogCooperado;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CooperadoActivity extends AppCompatActivity implements CooperadoContract.View {

    private static final String TAG = "CooperadoActivity";


    @Inject
    CooperadoContract.Presenter presenter;

    @BindView(R.id.nome_cooperado)
    TextView nomeCooperadoTextView;

    @BindView(R.id.role_cooperado)
    TextView roleCooperadoTextView;

    @BindView(R.id.suporting_text_cooperado)
    TextView suportingCooperadoTextView;

    @BindView(R.id.image_view_cooperado)
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

    }

    private void setClickListener(View view) {
        CoopLog.d(TAG, "onClickListener: ");
        view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CoopLog.d(TAG, "onClick: ");
                DialogFragment newFragment = new DialogCooperado();
                newFragment.show(getFragmentManager(), "missiles");
            }
        } );
    }

    @Override
    protected void onResume() {
        super.onResume();
        CoopLog.d(TAG, "onResume: ");
        presenter.onViewResume( this );
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
        suportingCooperadoTextView.setText(cooperado.getRole());
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
}