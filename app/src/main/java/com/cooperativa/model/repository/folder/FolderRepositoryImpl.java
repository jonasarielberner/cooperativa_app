package com.cooperativa.model.repository.folder;

import com.cooperativa.model.datasource.folder.FolderDataSource;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

public class FolderRepositoryImpl implements FolderRepository {

    private FolderDataSource folderDataSource;

    @Inject
    public FolderRepositoryImpl(FolderDataSource folderDataSource) {
        this.folderDataSource = folderDataSource;
    }

    /*@Override
    public File getIssueFolder(String issueID) {
        return folderDataSource.getIssueFolder(issueID);
    }*/

    @Override
    public File getCooperadoPictureFolder(String cooperadoId) {
        return folderDataSource.getCooperadoPhotoFolder(cooperadoId);
    }

    /*@Override
    public File getIssueVideoFolder(String issueID) {
        return folderDataSource.getIssueVideoFolder(issueID);
    }

    @Override
    public File getIssueAudioFolder(String issueID) {
        return folderDataSource.getIssueAudioFolder(issueID);
    }

    @Override
    public List<AudioItem> getIssueAudioFiles(String issueID) {
        return folderDataSource.getIssueAudioFiles(issueID);
    }

    @Override
    public List<PhotoItem> getIssuePhotoFiles(String issueID) {
        return folderDataSource.getIssuePhotoFiles(issueID);
    }

    @Override
    public List<VideoItem> getIssueVideoFiles(String issueID) {
        return folderDataSource.getIssueVideoFiles(issueID);
    }

    @Override
    public boolean deleteMediaAttachment(File file) {
        return folderDataSource.deleteMediaAttachment(file);
    }*/
}
