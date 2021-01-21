package com.android.appetisermasterdetail.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.appetisermasterdetail.data.model.ITunesItemData
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ITunesItemDao {
    companion object {
        const val iTunesItemTable = "itunes_item_data"
    }

    @Query("SELECT * FROM $iTunesItemTable")
    fun getAllTopicData(): Single<List<ITunesItemData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(topicList: List<ITunesItemData>): Completable

    @Query("DELETE FROM $iTunesItemTable")
    fun deleteAllTopicData(): Completable
}