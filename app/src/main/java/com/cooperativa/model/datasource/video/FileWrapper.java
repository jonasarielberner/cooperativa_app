package com.cooperativa.model.datasource.video;

import android.content.Context;
import android.net.Uri;
import android.support.v4.provider.DocumentFile;
import android.webkit.MimeTypeMap;

import com.cooperativa.model.datasource.logging.CoopLog;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.inject.Inject;
import javax.inject.Named;

import static dagger.internal.Preconditions.checkNotNull;

public class FileWrapper {

    private static final String TAG = "FileWrapper";
    private Context applicationContext;

    @Inject
    public FileWrapper(@Named("applicationContext") Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void copyFile(File src, File dst) throws IOException {
        try (InputStream in = new FileInputStream(src)) {
            try (OutputStream out = new FileOutputStream(dst)) {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
        }
    }

    public File newFile(String path) {
        return new File(path);
    }

    public File newFile(String parent, String path) {
        return new File(parent, path);
    }

    public boolean copyFolderContent(File srcDir, File dstDir) {
        try {
            if (srcDir.isDirectory()) {
                for (File file : srcDir.listFiles()) {
                    copyFile(file, new File(dstDir + File.separator + file.getName()));
                }
            } else {
                copyFile(srcDir, dstDir);
            }
        } catch (Exception e) {
            CoopLog.e(TAG, "copyFolderContent: ", e);
            return false;
        }
        return true;
    }

    /**
     * This method make a working copy of the file inside a private apps file.
     */
    public void copyToPrivateDataFolder(Uri localFilePath, String destinationFileName) throws IOException {
        checkNotNull(localFilePath, "Local file path can not be null nor empty");
        checkNotNull(destinationFileName, "Destination file name can not be null nor empty");

        CoopLog.d(TAG, "copyToPrivateDataFolder: ");

        File filesDir = applicationContext.getFilesDir();
        File destination = new File(filesDir.getAbsolutePath() + File.separator + destinationFileName);

        copy(localFilePath, destination);
    }

    private void copy(Uri source, File destination) throws IOException {
        CoopLog.d(TAG, "copy: begin");

        try (InputStream in = applicationContext.getContentResolver().openInputStream(source)) {
            try (OutputStream out = new FileOutputStream(destination)) {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
        }
        CoopLog.d(TAG, "copy: end");
    }

    public File loadFileFromPrivateFolder(String fieldDefinitionFileName) {
        return new File(applicationContext.getFilesDir() + File.separator + fieldDefinitionFileName);
    }

    public boolean saveVideo(Uri videoUri, String videoPath) throws IOException {
        File videoFile = new File(videoPath);
        try (InputStream in = applicationContext.getContentResolver().openInputStream(videoUri)) {
            try (OutputStream outputStream = new FileOutputStream(videoFile)) {
                writeFile(in, outputStream);
                return true;
            }
        }
    }

    private void writeFile(InputStream in, OutputStream outputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int length;
        if (in != null) {
            while ((length = in.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }
    }

    /**
     * Deletes the directory with all files inside
     *
     * @param file the file or folder to delete
     * @return true if deleted
     */
    public boolean deleteFileOrDirectory(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();

            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isDirectory()) {
                        deleteFileOrDirectory(files[i]);
                    } else {
                        if (!files[i].delete()) {
                            return false;
                        }
                    }
                }
            } else {
                return true; // there is no files
            }

            if (!file.delete()) {
                return false;
            }
        } else {
            return file.delete();
        }
        return true;
    }

    public boolean writeToFile(String valueToWrite, File folder, String fileName) {
        try (FileOutputStream stream = new FileOutputStream(folder + File.separator + fileName)) {
            stream.write(valueToWrite.getBytes());
        } catch (IOException e) {
            CoopLog.e(TAG, "writeToFile: ", e);
            return false;
        }
        return true;
    }

    public File createDirectory(File parent, String childFolder) {
        return getFolderFile(parent.toString() + File.separator + childFolder);
    }

    public File getFolderFile(String filePath) {
        File folder = new File(filePath);
        boolean success = true;
        if (!folder.exists()) { //if the folder does not exist, createHandler it.
            success = folder.mkdirs();
        }
        if (success) {
            return folder;
        } else {
            // If it fails, return null.
            return null;
        }
    }

    public boolean copyToDestinationLocation(File fileToCopy, Uri destinationUri, Context con) {
        DocumentFile dir = DocumentFile.fromTreeUri(con, destinationUri);
        String mime = getMime(fileToCopy.toURI().toString());
        DocumentFile copy1 = dir.createFile(mime, fileToCopy.getName());
        try (FileInputStream inStream = new FileInputStream(fileToCopy)) {
            try (OutputStream outStream = con.getContentResolver().openOutputStream(copy1.getUri())) {
                byte[] buffer = new byte[16384];
                int bytesRead;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
        } catch (IOException e) {
            CoopLog.e(TAG, "copyToDestinationLocation: ", e);
            return false;
        }
        return true;
    }

    private String getMime(String uri) {
        String type = "";
        String extention = MimeTypeMap.getFileExtensionFromUrl(uri);
        if (extention != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extention);
        }
        return type;
    }

    public String getSeparator() {
        return File.separator;
    }

    public File[] listFiles(File folder) {
        return folder.listFiles();
    }


    public void removeLines(File file, int numLinesToRemove, String lineSeparator) throws IOException {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            long i = 0;
            while ((line = br.readLine()) != null) {
                i++;
                if (i > numLinesToRemove) {
                    builder.append(line);
                    builder.append(lineSeparator);
                }
            }
        }
        try (OutputStream fileOutputStream = new FileOutputStream(file, false)) {
            fileOutputStream.write(builder.toString().getBytes());
        }
    }


    public int countNumberOfLines(File file) throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if (endsWithoutNewLine) {
                ++count;
            }
            return count;
        }
    }

}
