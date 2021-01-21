package com.android.appetisermasterdetail.helper

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.appetisermasterdetail.BuildConfig
import com.android.appetisermasterdetail.data.dao.ITunesItemDao
import com.android.appetisermasterdetail.data.model.ITunesItemData

@Database(
    entities = [
        ITunesItemData::class],
    version = BuildConfig.DATABASE_VERSION,
    exportSchema = true
)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun iTunesItemDao(): ITunesItemDao

    companion object {
        const val DATABASE_NAME = "ITunesStoreList"
    }

}