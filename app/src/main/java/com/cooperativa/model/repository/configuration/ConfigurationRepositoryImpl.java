package com.cooperativa.model.repository.configuration;

import com.cooperativa.model.datasource.configuration.ConfigurationDataSource;

import javax.inject.Inject;

import io.reactivex.Single;

public class ConfigurationRepositoryImpl implements ConfigurationRepository {

    private ConfigurationDataSource configurationDataSource;

    @Inject
    public ConfigurationRepositoryImpl(ConfigurationDataSource configurationDataSource) {
        this.configurationDataSource = configurationDataSource;
    }

    @Override
    public Single<Boolean> loadPreviousConfiguration() {
        return configurationDataSource.loadPreviousConfiguration();
    }
}