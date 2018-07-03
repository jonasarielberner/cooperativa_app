package com.cooperativa.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.cooperativa.db.entity.Cooperados;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CooperadosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertCooperado(Cooperados cooperados);

    @Query( "SELECT * FROM cooperados WHERE id = :cooperadoId" )
    Single<Cooperados> loadCooperado(Long cooperadoId);

    @Query("SELECT * FROM cooperados")
    Single<List<Cooperados>> loadAllCooperados();

    @Query("SELECT * FROM cooperados WHERE id = :cooperadoId")
    Cooperados loadCooperadoSynchronous(Long cooperadoId);

    @Delete
    int deleteCooperado(Cooperados... cooperados);

    /**
     * Update the issue.
     *
     * @param cooperados The issue to update.
     * @return A number of cooperados updated. This should always be {@code 1}.
     */
    @Update
    int updateSynchronous(Cooperados cooperados);
}
