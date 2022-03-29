package com.can.ilacasistani.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.can.ilacasistani.model.Ilac

@Database(entities = [Ilac:: class], version = 1)
abstract class IlacDatabase : RoomDatabase(){

    abstract fun ilacDao() : IlacDAO

    //SINGLETON
    companion object{

        @Volatile private var instance : IlacDatabase? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            IlacDatabase::class.java,"ilacdatabase").build()
    }




}