package com.pranay.reflectionai.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pranay.reflectionai.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase:RoomDatabase() {

    abstract fun moviesDao() : MoviesDao

    companion object{
        @Volatile
        private var INSTANCE: MoviesDatabase? = null

        fun getDatabase(context:Context) :MoviesDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(context,
                    MoviesDatabase::class.java,
                    "movieDB").build()
                }
            }
            return INSTANCE!!
        }
    }
}