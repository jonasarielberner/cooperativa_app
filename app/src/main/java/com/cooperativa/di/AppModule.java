package com.cooperativa.di;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;
import android.media.MediaRecorder;

import com.cooperativa.di.threads.JobExecutor;
import com.cooperativa.di.threads.PostExecutionThread;
import com.cooperativa.di.threads.ThreadExecutor;
import com.cooperativa.di.threads.UIThread;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Named("applicationContext")
    @SuppressWarnings({"unused", "WeakerAccess"})
    public Context providesApplication() {
        return application;
    }

    @Provides
    @SuppressWarnings({"unused", "WeakerAccess"})
    public EventBus providesEventBus() {
        return EventBus.getDefault();
    }

    @Provides
    public MediaRecorder providesMediaRecorder() {
        return new MediaRecorder();
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    SimpleDateFormat provideSimpleDateFormat() {
        return new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
    }

    @Provides
    @Singleton
    LocationManager providesLocationManager(@Named("applicationContext") Context context) {
        return (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }




}
