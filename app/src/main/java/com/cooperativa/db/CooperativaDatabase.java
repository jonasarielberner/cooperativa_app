package com.cooperativa.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.support.annotation.VisibleForTesting;

import com.cooperativa.db.dao.CooperadosDao;
import com.cooperativa.db.entity.Cooperados;

@Database(entities = {Cooperados.class}, version = 1)
public abstract class CooperativaDatabase extends RoomDatabase {

    @VisibleForTesting
    public static final String DATABASE_NAME = "cooperativa-db";

    public abstract CooperadosDao cooperadosDao();
}
