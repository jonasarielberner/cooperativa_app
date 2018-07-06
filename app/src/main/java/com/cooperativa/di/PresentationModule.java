package com.cooperativa.di;

import com.cooperativa.presentation.about.AboutContract;
import com.cooperativa.presentation.about.AboutPresenter;
import com.cooperativa.presentation.cooperado.list.ListCooperadoContract;
import com.cooperativa.presentation.cooperado.list.ListCooperadoPresenter;
import com.cooperativa.presentation.cooperado.visualization.CooperadoContract;
import com.cooperativa.presentation.cooperado.visualization.CooperadoPresenter;
import com.cooperativa.presentation.main.MainActivityContract;
import com.cooperativa.presentation.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    @Provides
    @SuppressWarnings("WeakerAccess")
    public MainActivityContract.Presenter providesMainPresenter(MainPresenter presenter) {
        return presenter;
    }

    @Provides
    @SuppressWarnings("WeakerAccess")
    public AboutContract.Presenter providesAboutPresenter(AboutPresenter presenter) {
        return presenter;
    }

    @Provides
    @SuppressWarnings("WeakerAccess")
    public CooperadoContract.Presenter providesCooperadoPresenter(CooperadoPresenter presenter) {
        return presenter;
    }

    @Provides
    @SuppressWarnings("WeakerAccess")
    public ListCooperadoContract.Presenter providesListCooperadoContract (ListCooperadoPresenter presenter){
        return presenter;
    }
}
