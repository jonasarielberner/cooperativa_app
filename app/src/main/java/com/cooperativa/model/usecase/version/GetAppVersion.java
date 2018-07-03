package com.cooperativa.model.usecase.version;

import com.cooperativa.model.UseCaseCallback;
import com.cooperativa.model.datasource.logging.CoopLog;
import com.cooperativa.model.repository.version.VersionRepository;

import javax.inject.Inject;

public class GetAppVersion {

    private static final String TAG = "GetAppVersion";

    private VersionRepository versionRepository;

    @Inject
    public GetAppVersion(VersionRepository versionRepository) {
        this.versionRepository = versionRepository;
    }

    public void execute(UseCaseCallback<String> callback) {
        CoopLog.d(TAG, "execute: ");
        try {
            callback.onSuccess(versionRepository.getAppVersion());
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}

