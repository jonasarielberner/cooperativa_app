package com.cooperativa.model.datasource.photo;


import com.cooperativa.model.datasource.database.DateWrapper;
import com.cooperativa.model.datasource.folder.FolderDataSource;

import java.io.File;

import javax.inject.Inject;

public class PhotoDataSourceImpl implements PhotoDataSource {

    private static final String FILE_EXTENSION = ".jpg";
    private FolderDataSource folderDataSource;
    private DateWrapper dateWrapper;

    @Inject
    public PhotoDataSourceImpl(FolderDataSource folderDataSource, DateWrapper dateWrapper) {
        //Empty constructor
        this.folderDataSource = folderDataSource;
        this.dateWrapper = dateWrapper;
    }

    @Override
    public File createNewPhotoFile(String cooperadoId) {
        String timeStamp = dateWrapper.getCurrentDateFormatted();
        File storageDir = folderDataSource.getCooperadoPhotoFolder(cooperadoId);
        return new File(storageDir + File.separator + timeStamp + FILE_EXTENSION);
    }

    private String 


}