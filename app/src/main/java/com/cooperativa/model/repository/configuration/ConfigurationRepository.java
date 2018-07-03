package com.cooperativa.model.repository.configuration;


import io.reactivex.Single;

/**
 * Created by cedulio.silva on 1/30/2018.
 */

public interface ConfigurationRepository {
    Single<Boolean> loadPreviousConfiguration();
}
