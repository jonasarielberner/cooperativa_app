package com.cooperativa.di;

import android.content.Context;

import com.cooperativa.db.CooperativaDatabase;
import com.cooperativa.db.dao.CooperadosDao;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    CooperativaDatabase providesDatabase(@Named("applicationContext") Context context) {
        return DatabaseFactory.create(context, CooperativaDatabase.DATABASE_NAME);
    }

    @Provides
    @Singleton
    CooperadosDao providesCooperadosDao(CooperativaDatabase database){return database.cooperadosDao();}
}
