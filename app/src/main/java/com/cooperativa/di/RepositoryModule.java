package com.cooperativa.di;

import com.cooperativa.model.repository.configuration.ConfigurationRepository;
import com.cooperativa.model.repository.configuration.ConfigurationRepositoryImpl;
import com.cooperativa.model.repository.database.CooperadoRepository;
import com.cooperativa.model.repository.database.CooperadoRepositoryImpl;
import com.cooperativa.model.repository.logging.LoggingRepository;
import com.cooperativa.model.repository.logging.LoggingRepositoryImpl;
import com.cooperativa.model.repository.version.VersionRepository;
import com.cooperativa.model.repository.version.VersionRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    /*@Provides
    VersionRepository providesVersionRepository(VersionDataSource versionDataSource) {
        return new VersionRepositoryImpl(versionDataSource);
    }

    @Provides
    AudioPlayerRepository providesMediaPlayerRepository(AudioPlayerDataSource audioPlayerDataSource) {
        return new AudioPlayerRepositoryImpl(audioPlayerDataSource);
    }

    @Provides
    AudioRecorderRepository providesMediaRecorderRepository(AudioRecorderDataSource audioRecorderDataSource) {
        return new AudioRecorderRepositoryImpl(audioRecorderDataSource);
    }

    @Provides
    PhotoRepository providesPictureRepository(PhotoDataSource photoDataSource) {
        return new PhotoRepositoryImpl(photoDataSource);
    }

    @Provides
    VideoRepository providesVideoRepository(VideoDataSource videoDataSource) {
        return new VideoRepositoryImpl(videoDataSource);
    }*/

    @Provides
    @Singleton
    ConfigurationRepository providesConfigurationRepository(ConfigurationRepositoryImpl configurationRepository) {
        return configurationRepository;
    }

    /*@Provides
    @Singleton
    ExportRepository providesExportRepository(ExportRepositoryImpl exportRepository) {
        return exportRepository;
    }*/

    @Provides
    @Singleton
    LoggingRepository providesLoggingRepository(LoggingRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    VersionRepository providesVersionRepository(VersionRepositoryImpl repository) {
        return repository;
    }

    @Provides
    @Singleton
    CooperadoRepository providesCooperadoRepository(CooperadoRepositoryImpl repository) {
        return repository;
    }
}
