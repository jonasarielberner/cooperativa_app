package com.cooperativa.model.datasource.configuration;


import javax.inject.Inject;

import io.reactivex.Single;

public class LocalConfigurationDataSource implements ConfigurationDataSource {


    @Inject
    public LocalConfigurationDataSource( ) {

    }

    @Override
    public Single<Boolean> loadPreviousConfiguration() {
        return Single.create(emitter -> {
            try {

                emitter.onSuccess(true);
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}