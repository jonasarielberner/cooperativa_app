package com.cooperativa.model.datasource.folder;

import java.io.File;
import java.util.List;

public interface FolderDataSource {

    /**
     * Get the Picture folder of the Issue. If it does not exist. It creates it
     *
     * @return the file of the folder
     */

    File getCooperadoPhotoFolder(String cooperadoId);

}

