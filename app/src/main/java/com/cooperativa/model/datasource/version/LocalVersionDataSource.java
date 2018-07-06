package com.cooperativa.model.datasource.version;

import com.cooperativa.BuildConfig;

public class LocalVersionDataSource implements VersionDataSource {
    @Override
    public String getAppVersion() {
        return BuildConfig.VERSION_NAME;
    }
}
