package com.cooperativa.presentation.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cooperativa.CooperativaApplication;
import com.cooperativa.R;
import com.cooperativa.model.datasource.logging.CoopLog;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements AboutContract.View{

    private static final String TAG = "AboutActivity";

    @BindView(R.id.version_tv)
    TextView versionTextView;

    @Inject
    AboutContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_about );

        ButterKnife.bind(this);
        ((CooperativaApplication) getApplication()).component().inject(this);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public void showAppVersion(String appVersion) {

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
}
