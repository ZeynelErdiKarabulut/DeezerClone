package com.zeynelerdi.deezer.domain.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zeynelerdi.deezer.core.Env
import com.zeynelerdi.deezer.core.RoomConverter
import com.zeynelerdi.deezer.data.albumdetails.AlbumData
import com.zeynelerdi.deezer.data.genre.Data
import com.zeynelerdi.deezer.data.search.SearchQuery

@Database(entities = [Data::class,SearchQuery::class,AlbumData::class], version = Env.DATABASE_VERSION, exportSchema = false)
@TypeConverters(value = [RoomConverter::class])
abstract class DeezerDatabase : RoomDatabase(){
    abstract fun deezerDao() : DeezerDao
}