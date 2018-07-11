package com.cooperativa.model.repository.photo;

import java.io.File;

public interface PhotoRepository {

    /**
     * create a new file for saving a new image
     *
     * @param issueID the issue to save the image
     * @return a file for the image to be stored
     */
    File createNewPictureFile(String issueID);

}