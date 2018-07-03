package com.cooperativa;

import android.app.Application;
import android.util.Log;

import com.cooperativa.di.AppComponent;
import com.cooperativa.di.AppModule;
import com.cooperativa.di.DaggerAppComponent;
import com.cooperativa.di.DataSourceModule;
import com.cooperativa.di.DatabaseModule;
import com.cooperativa.di.PresentationModule;
import com.cooperativa.di.RepositoryModule;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.model.datasource.logging.FileLoggingTree;
import com.cooperativa.model.usecase.configuration.LoadConfiguration;
import com.cooperativa.model.usecase.logging.LimitNumberOfLinesInLogFile;


import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import timber.log.Timber;


/**
 * This Android Application is responsible for keep state of the app and general configurations,
 * such as hockey app setup, dependency injection and so on.
 * <p>
 * Created by cedulio.silva on 07/12/2017.
 */

public class CooperativaApplication extends Application {
    private static final String TAG = "Cooperativa-Application";

    static CooperativaApplication sInstance = null;

    @Inject
    LoadConfiguration loadConfiguration;
    @Inject
    LimitNumberOfLinesInLogFile limitNumberOfLinesInLogFile;

    private AppComponent appComponent = createComponent();

    /**
     * Keep this method separated to enable override on android instrumented tests package.
     */
    AppComponent createComponent() {

        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataSourceModule(new DataSourceModule())
                .presentationModule(new PresentationModule())
                .repositoryModule(new RepositoryModule())
                .databaseModule(new DatabaseModule())
                .build();
    }

    public static CooperativaApplication getsInstance() {
        return sInstance;
    }

    private void loadConfiguration() {
        CoopLog.d(TAG, "loadConfiguration: ");
        loadConfiguration.execute(new DisposableSingleObserver<Boolean>() {
            @Override
            public void onSuccess(Boolean loaded) {
                CoopLog.d(TAG, "loadConfiguration onSuccess: " + loaded);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "loadConfiguration onError: ", e);
            }
        });
    }

    private void limitNumberOfLinesLog() {
        CoopLog.d(TAG, "limitNumberOfLinesLog: ");
        limitNumberOfLinesInLogFile.execute(new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                Log.d(TAG, "limitNumberOfLinesLog onComplete: ");
            }

            @Override
            public void onError(Throwable e) {
                CoopLog.e(TAG, "limitNumberOfLinesLog onError: ", e);
            }
        });
    }

    public AppComponent component() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sInstance = this;

        if (BuildConfig.DEBUG) {
            Timber.plant(new FileLoggingTree(getApplicationContext()));
        }

        CoopLog.d(TAG, "onCreate: ");

        component().inject(this);

        loadConfiguration();

        limitNumberOfLinesLog();
    }
}
