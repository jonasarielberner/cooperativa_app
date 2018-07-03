package com.cooperativa.presentation.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cooperativa.CooperativaApplication;
import com.cooperativa.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main);

        ButterKnife.bind(this);

        ((CooperativaApplication) getApplication()).component().inject(this);


    }
}
