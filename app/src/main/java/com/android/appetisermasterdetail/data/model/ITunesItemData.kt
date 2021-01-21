package com.android.appetisermasterdetail.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.appetisermasterdetail.data.dao.ITunesItemDao

@Entity(tableName = ITunesItemDao.iTunesItemTable)
data class ITunesItemData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val wrapperType: String? = "",
    val explicitness: String? = "",
    val kind: String? = "",
    val trackName: String? = "",
    val artistName: String? = "",
    val collectionName: String? = "",
    val censoredName: String? = "",
    val artworkUrl100: String? = "",
    val artworkUrl60: String? = "",
    val artworkUrl30: String? = "",
    val trackPrice: String? = "",
    val currency: String? = "",
    val releaseDate: String? = "",
    val collectionExplicitness: String? = "",
    val trackExplicitness: String? = "",
    val contentAdvisoryRating: String? = "",
    val primaryGenreName: String? = "",
    val longDescription: String? = "",
    val shortDescription: String? = "",
    val viewURL: String? = "",
    val previewUrl: String? = "",
    val trackTimeMillis: String? = ""
)