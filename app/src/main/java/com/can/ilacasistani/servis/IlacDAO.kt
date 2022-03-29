package com.can.ilacasistani.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.can.ilacasistani.model.Ilac

@Dao
interface IlacDAO {

    @Insert
    suspend fun insertAll(vararg ilac : Ilac) : List<Long>

    @Query("SELECT * FROM ilac")
    suspend fun getAllIlac() : List<Ilac>

    @Query("SELECT * FROM ilac WHERE uuid = :ilacId")
    suspend fun getIlac(ilacId : Int) : Ilac

    @Query("DELETE FROM ilac ")
    suspend fun deleteAllIlac()
}