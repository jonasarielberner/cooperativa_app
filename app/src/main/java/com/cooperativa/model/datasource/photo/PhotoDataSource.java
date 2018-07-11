package com.cooperativa.model.datasource.photo;

import java.io.File;

public interface PhotoDataSource {

    /**
     * create a new file for saving a new image
     *
     * @param issueID the issue to save the image
     * @return a file for the image to be stored
     */
    File createNewPhotoFile(String issueID);
}