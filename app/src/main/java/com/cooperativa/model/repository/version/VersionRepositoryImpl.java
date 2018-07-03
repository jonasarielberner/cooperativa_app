package com.cooperativa.model.repository.version;


import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.model.datasource.version.VersionDataSource;

import javax.inject.Inject;

public class VersionRepositoryImpl implements VersionRepository {

    private static final String TAG = "VersionRepositoryImpl";
    private VersionDataSource versionDataSource;

    @Inject
    public VersionRepositoryImpl(VersionDataSource versionDataSource) {
        this.versionDataSource = versionDataSource;
    }

    @Override
    public String getAppVersion() {
        CoopLog.d(TAG, "getAppVersion: ");
        return versionDataSource.getAppVersion();
    }
}