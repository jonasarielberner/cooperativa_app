package com.cooperativa.presentation.mediaplayer;

import java.io.File;

/**
 * Base class for media
 *
 * @author João Luiz Vieira <joao.vieira@pixida.com.br>.
 */

public class MediaItem {
    private final File file;
    private final String fileName;

    public MediaItem(File file, String fileName) {
        this.file = file;
        this.fileName = fileName;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }
}