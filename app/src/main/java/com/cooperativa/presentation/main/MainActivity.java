package com.cooperativa.presentation.main;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cooperativa.CooperativaApplication;
import com.cooperativa.R;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.presentation.about.AboutActivity;
import com.cooperativa.presentation.cooperado.visualization.CooperadoActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private static final String TAG = "MainActivity";

    @Inject
    MainActivityContract.Presenter presenter;

    @BindView( R.id.my_toolbar )
    Toolbar myToolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main);

        ButterKnife.bind(this);

        ((CooperativaApplication) getApplication()).component().inject(this);

        setSupportActionBar(myToolbar);

        /*ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }*/

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        return handleMenuItemSelection(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private boolean handleMenuItemSelection(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_about:
                handleMenuAboutClicked();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void handleMenuAboutClicked() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

}
