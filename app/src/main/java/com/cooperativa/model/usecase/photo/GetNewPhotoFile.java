package com.cooperativa.model.usecase.photo;


import com.cooperativa.model.UseCaseCallback;
import com.cooperativa.model.repository.photo.PhotoRepository;

import java.io.File;

import javax.inject.Inject;

public class GetNewPhotoFile {

    private PhotoRepository repository;

    @Inject
    public GetNewPhotoFile(PhotoRepository repository) {
        this.repository = repository;
    }

    public void execute(String cooperadoId, UseCaseCallback<File> callback) {
        try {
            callback.onSuccess(repository.createNewPictureFile(cooperadoId));
        } catch (Exception e) {
            callback.onError(e);
        }
    }
}