package com.cooperativa.di;


import android.content.Context;
import android.content.SharedPreferences;

import com.cooperativa.db.dao.CooperadosDao;
import com.cooperativa.model.datasource.configuration.ConfigurationDataSource;
import com.cooperativa.model.datasource.configuration.LocalConfigurationDataSource;
import com.cooperativa.model.datasource.cooperado.CooperadosMapper;
import com.cooperativa.model.datasource.database.CooperadoDataSource;
import com.cooperativa.model.datasource.database.DateWrapper;
import com.cooperativa.model.datasource.database.LocalCooperadoDataSource;
import com.cooperativa.model.datasource.folder.FolderDataSource;
import com.cooperativa.model.datasource.folder.FolderDataSourceImpl;
import com.cooperativa.model.datasource.logging.LoggingDataSource;
import com.cooperativa.model.datasource.logging.LoggingDataSourceImpl;
import com.cooperativa.model.datasource.photo.PhotoDataSource;
import com.cooperativa.model.datasource.photo.PhotoDataSourceImpl;
import com.cooperativa.model.datasource.version.LocalVersionDataSource;
import com.cooperativa.model.datasource.version.VersionDataSource;
import com.cooperativa.model.datasource.video.FileWrapper;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataSourceModule {

    private static final String APP_PREFERENCES = "APP_PREFERENCES";

    /*@Provides
    @Singleton
    XStream providesXStream() {
        return XStreamFactory.create();
    }



   */

    @Provides
    @Singleton
    ConfigurationDataSource providesConfigurationDataSource(LocalConfigurationDataSource localConfigurationDataSource) {
        return localConfigurationDataSource;
    }

    @Provides
    @Singleton
    @Named("appSharedPreferences")
    SharedPreferences providesAppSharedPreferences(@Named("applicationContext") Context context) {
        return context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    /*@Provides
    @Singleton
    ExportDataSource providesExportDataSource(ExportDataSourceImpl dataSource) {
        return dataSource;
    }*/

    @Provides
    @Singleton
    LoggingDataSource providesLoggingDataSource(LoggingDataSourceImpl dataSource) {
        return dataSource;
    }
    @Provides
    @Singleton
    VersionDataSource providesVersionDataSource() {
        return new LocalVersionDataSource();
    }

    @Provides
    @Singleton
    CooperadoDataSource providesCooperadoDataSource(LocalCooperadoDataSource localCooperadoDataSource, CooperadosMapper cooperadosMapper) {
        return new LocalCooperadoDataSource( (CooperadosDao) localCooperadoDataSource, cooperadosMapper );
    }

    @Provides
    @Singleton
    PhotoDataSource providesPictureDataSource(FolderDataSource folderDataSource, DateWrapper dateWrapper) {
        return new PhotoDataSourceImpl(folderDataSource, dateWrapper, context );
    }
    @Provides
    @Singleton
    FolderDataSource providesVideoDataSource(FileWrapper fileWrapper) {
        return new FolderDataSourceImpl(fileWrapper);
    }



}
