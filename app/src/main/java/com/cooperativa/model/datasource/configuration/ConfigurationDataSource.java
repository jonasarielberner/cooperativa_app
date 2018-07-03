package com.cooperativa.model.datasource.configuration;
import io.reactivex.Single;

/**
 * Created by cedulio.silva on 1/30/2018.
 */

public interface ConfigurationDataSource {

    Single<Boolean> loadPreviousConfiguration();
}
