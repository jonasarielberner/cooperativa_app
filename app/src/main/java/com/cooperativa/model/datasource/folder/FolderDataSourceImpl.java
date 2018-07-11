package com.cooperativa.model.datasource.folder;

import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.cooperativa.model.datasource.video.FileWrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static java.lang.Integer.parseInt;

public class FolderDataSourceImpl implements FolderDataSource {

    public static final String APP_FOLDER = "COOPERATIVA";
    private static final String TICKET_FOLDER_PHOTO = "Photo";
    private static final String COOPERADO = "Cooperado_";
    private static final String EXPORT_TEMP_FOLDER = "EXPORT_TEMP";
    private FileWrapper fileWrapper;

    @Inject
    public FolderDataSourceImpl(FileWrapper fileWrapper) {
        this.fileWrapper = fileWrapper;
    }

    @Override
    public File getCooperadoPhotoFolder(String cooperadoId) {
        String path = getCooperadoFolderPath(cooperadoId) + fileWrapper.getSeparator() + TICKET_FOLDER_PHOTO;
        return fileWrapper.getFolderFile(path);
    }

    @NonNull
    private String getCooperadoFolderPath(String cooperadoId) {
        return getBaseFolderPath() + COOPERADO + cooperadoId;
    }



    private String getBaseFolderPath() {
        return Environment.getExternalStorageDirectory() + fileWrapper.getSeparator() +
                APP_FOLDER + fileWrapper.getSeparator();
    }





}
