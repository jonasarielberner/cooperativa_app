package com.cooperativa.model.repository.folder;

import java.io.File;
import java.util.List;

public interface FolderRepository {

    /**
     * Get the Issue Folder. If it does not exist. It creates it
     *
     * @return the file of the folder
     */

    /*File getIssueFolder(String issueID);

    *//**
     * Get the Picture folder of the Issue. If it does not exist. It creates it
     *
     * @return the file of the folder
     */

    File getCooperadoPictureFolder(String cooperadoId);

   /* *//**
     * Get the Video folder of the Issue. If it does not exist. It creates it
     *
     * @return the file of the folder
     *//*

    File getIssueVideoFolder(String issueID);

    *//**
     * Get the Audios folder of the Issue. If it does not exist. It creates it
     *
     * @param issueID the Issue ID to get the folder
     * @return the file of the folder
     *//*

    File getIssueAudioFolder(String issueID);


    *//**
     * Get the Audios files from a Issue
     *
     * @return the list of audio files for the folder
     *//*

    List<AudioItem> getIssueAudioFiles(String issueID);

    *//**
     * Get the Photo files from a Issue
     *
     * @return the list of photo files for the folder
     *//*

    List<PhotoItem> getIssuePhotoFiles(String issueID);

    *//**
     * Get the Photo files from a Issue
     *
     * @return the list of photo files for the folder
     *//*

    List<VideoItem> getIssueVideoFiles(String issueID);


    *//**
     * delete the media
     *
     * @param file the file to be deleted
     * @return true if successful deleted
     *//*
    boolean deleteMediaAttachment(File file);*/
}
