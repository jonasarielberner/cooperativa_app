package com.cooperativa.model.datasource.logging;


import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import timber.log.Timber;

/**
 * Writes log to the file
 *
 * @author João Luiz Vieira <joao.vieira@pixida.com.br>.
 */

public class FileLoggingTree extends Timber.DebugTree {

    static final String LOG_FOLDER = "logs";
    private static final String TAG = FileLoggingTree.class.getSimpleName();
    private static final String LINE_SEPARATOR = "\r\n";
    private static final String VALUE_SEPARATOR = " | ";

    private Context context;


    public FileLoggingTree(Context context) {
        this.context = context;
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        super.log(priority, tag, message, t);

        try {
            File folder = new File(context.getFilesDir().getAbsolutePath() + File.separator + LOG_FOLDER);
            String fileName = "log_debug" + ".txt";
            File file = new File(folder, fileName);

            if (!folder.exists()) {
                folder.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            if (file.exists()) {
                try (OutputStream fileOutputStream = new FileOutputStream(file, true)) {
                    StringBuilder builder = new StringBuilder();
                    String logTimeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS", Locale.getDefault()).format(new Date());
                    builder.append(logTimeStamp);
                    if (!message.isEmpty()) {
                        builder.append(VALUE_SEPARATOR);
                        builder.append(message);
                    }
                    if (t != null) {
                        builder.append(VALUE_SEPARATOR);
                        builder.append(t.getMessage());
                        builder.append(VALUE_SEPARATOR);
                        builder.append(Arrays.toString(t.getStackTrace()));
                    }

                    builder.append(LINE_SEPARATOR);
                    fileOutputStream.write((builder.toString()).getBytes());
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "Error while logging into file : " + e);
        }

    }


}
