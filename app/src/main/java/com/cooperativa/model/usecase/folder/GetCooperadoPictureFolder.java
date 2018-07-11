package com.cooperativa.model.usecase.folder;

import com.cooperativa.model.UseCaseCallback;
import com.cooperativa.model.repository.folder.FolderRepository;

import java.io.File;

import javax.inject.Inject;

public class GetCooperadoPictureFolder {

        private FolderRepository folderRepository;

        @Inject
        public GetCooperadoPictureFolder(FolderRepository folderRepository) {
            this.folderRepository = folderRepository;
        }

        public void execute(String cooperadoId, UseCaseCallback<File> callback) {
            try {
                callback.onSuccess(folderRepository.getCooperadoPictureFolder(cooperadoId));
            } catch (Exception e) {
                callback.onError(e);
            }
        }
    }