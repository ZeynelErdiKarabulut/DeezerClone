package com.zeynelerdi.deezer.domain.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zeynelerdi.deezer.data.albumdetails.AlbumData
import com.zeynelerdi.deezer.data.genre.Data
import com.zeynelerdi.deezer.data.search.SearchQuery

@Dao
interface DeezerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenreList(genreList:List<Data>)

    @Query("SELECT * FROM Data")
    suspend fun getGenreList():List<Data>

    /* insert to query */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuery(q:SearchQuery)

    /* recent search */
    @Query("SELECT * FROM SearchQuery ORDER BY time")
    suspend fun getQueryList():List<SearchQuery>

    /* insert to track */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrack(albumData: AlbumData)

    /* recent favorites */
    @Query("SELECT * FROM AlbumData ORDER BY fav_time")
    suspend fun getFavorites():List<AlbumData>


}