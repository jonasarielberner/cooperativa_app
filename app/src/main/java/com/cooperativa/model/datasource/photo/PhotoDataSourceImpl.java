package com.cooperativa.model.datasource.photo;


import android.content.Context;

import com.cooperativa.R;
import com.cooperativa.model.datasource.database.DateWrapper;
import com.cooperativa.model.datasource.folder.FolderDataSource;

import java.io.File;

import javax.inject.Inject;


public class PhotoDataSourceImpl implements PhotoDataSource {

    private static final String FILE_EXTENSION = ".jpg";
    private FolderDataSource folderDataSource;
    private DateWrapper dateWrapper;

    private Context context;


    @Inject
    public PhotoDataSourceImpl(FolderDataSource folderDataSource, DateWrapper dateWrapper, Context context) {
        this.folderDataSource = folderDataSource;
        this.dateWrapper = dateWrapper;
        this.context = context;
    }

    @Override
    public File createNewPhotoFile(String cooperadoId) {
        //String timeStamp = dateWrapper.getCurrentDateFormatted();
        String photoName = createProfilePhotoFile();
        File storageDir = folderDataSource.getCooperadoPhotoFolder(cooperadoId);
        return new File(storageDir + File.separator + photoName + FILE_EXTENSION);
    }

    private String createProfilePhotoFile(){
        String profileString = context.getString (R.string.profile_photo) ;
        return profileString;
    }

}