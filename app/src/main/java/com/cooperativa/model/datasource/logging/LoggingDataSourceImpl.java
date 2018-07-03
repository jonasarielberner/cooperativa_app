package com.cooperativa.model.datasource.logging;

import android.content.Context;
import android.support.annotation.NonNull;

import com.cooperativa.model.datasource.video.FileWrapper;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Completable;
import io.reactivex.Single;

import static com.cooperativa.model.datasource.logging.FileLoggingTree.LOG_FOLDER;

public class LoggingDataSourceImpl implements LoggingDataSource {

    private static final String LINE_SEPARATOR = "\r\n";
    private static final int MAX_LINES = 10_000;
    private static final String TAG = "LoggingDataSourceImpl";

    @Inject
    @Named("applicationContext")
    Context context;
    @Inject
    FileWrapper fileWrapper;

    @Inject
    public LoggingDataSourceImpl() {
        //empty constructor
    }

    @Override
    public Completable limitNumberOfLinesInLogFile() {
        return Completable.create(emitter -> {
            File file = getLogDebugFile();
            if (file.exists()) {
                limitNumberOfLinesFile(file);
            }
            emitter.onComplete();
        });
    }

    @NonNull
    private File getLogDebugFile() {
        String fileName = "log_debug" + ".txt";
        return fileWrapper.newFile(context.getFilesDir().getAbsolutePath() + fileWrapper.getSeparator() + LOG_FOLDER, fileName);
    }

    @Override
    public Single<File> getLogFile() {
        return Single.create(e -> e.onSuccess(getLogDebugFile()));
    }

    private void limitNumberOfLinesFile(File file) throws IOException {
        int numLines = fileWrapper.countNumberOfLines(file);
        if (numLines > MAX_LINES) {
            int numLinesToRemove = numLines - MAX_LINES;
            CoopLog.d(TAG, "removing: " + numLinesToRemove + " lines from log file");
            fileWrapper.removeLines(file, numLinesToRemove, LINE_SEPARATOR);
        }
    }


}
