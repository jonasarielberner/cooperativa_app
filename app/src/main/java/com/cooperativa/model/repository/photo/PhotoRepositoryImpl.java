package com.cooperativa.model.repository.photo;

import com.cooperativa.model.datasource.photo.PhotoDataSource;

import java.io.File;

import javax.inject.Inject;

public class PhotoRepositoryImpl implements PhotoRepository {

    private PhotoDataSource dataSource;

    @Inject
    public PhotoRepositoryImpl(PhotoDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public File createNewPictureFile(String cooperadoId) {
        return dataSource.createNewPhotoFile(cooperadoId);
    }
}