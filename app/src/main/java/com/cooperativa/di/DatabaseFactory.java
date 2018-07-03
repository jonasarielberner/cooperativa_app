package com.cooperativa.di;

import android.content.Context;
import android.arch.persistence.room.Room;


import com.cooperativa.db.CooperativaDatabase;

public class DatabaseFactory {

    public static CooperativaDatabase create (Context context, String databaseName){
        return Room.databaseBuilder (context, CooperativaDatabase.class, databaseName)
                .build();
    }
}
