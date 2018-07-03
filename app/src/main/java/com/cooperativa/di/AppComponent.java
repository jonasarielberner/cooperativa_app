package com.cooperativa.di;


import com.cooperativa.CooperativaApplication;
import com.cooperativa.presentation.about.AboutActivity;
import com.cooperativa.presentation.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * App Component that injects key classes.
 * Created by cedulio.silva on 12/8/2017.
 */
@Singleton
@Component(modules = {AppModule.class, PresentationModule.class, DataSourceModule.class, RepositoryModule.class, DatabaseModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(AboutActivity aboutActivity);

    void inject(CooperativaApplication cooperativaApplication);


}
