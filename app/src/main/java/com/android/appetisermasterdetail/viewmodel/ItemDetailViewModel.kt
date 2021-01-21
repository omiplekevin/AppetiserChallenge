package com.android.appetisermasterdetail.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.appetisermasterdetail.data.model.ITunesItemData

class ItemDetailViewModel: ViewModel() {

    private val mutableItemName = MutableLiveData<String>("")
    private val mutableItemGenre = MutableLiveData<String>("")
    private val mutableItemPrice = MutableLiveData<String>("")
    private val mutableItemArtist = MutableLiveData<String>("")
    private val mutableItemCover = MutableLiveData<String>("")
    private val mutableItemMaturityRating = MutableLiveData<String>("")
    private val mutableItemLongDescription = MutableLiveData<String>("")

    fun bind(iTunesItemData: ITunesItemData) {
        mutableItemName.value = iTunesItemData.trackName
        mutableItemGenre.value = iTunesItemData.primaryGenreName
        mutableItemArtist.value = iTunesItemData.artistName
        mutableItemPrice.value = "${iTunesItemData.trackPrice} ${iTunesItemData.currency}"
        mutableItemCover.value = iTunesItemData.artworkUrl100
        mutableItemMaturityRating.value = iTunesItemData.contentAdvisoryRating
        mutableItemLongDescription.value = iTunesItemData.longDescription
    }

    fun getMutableItemCover(): MutableLiveData<String> {
        return mutableItemCover
    }

    fun getMutableItemName(): MutableLiveData<String> {
        return mutableItemName
    }

    fun getMutableItemArtist(): MutableLiveData<String> {
        return mutableItemArtist
    }

    fun getMutableItemGenre(): MutableLiveData<String> {
        return mutableItemGenre
    }

    fun getMutableItemMaturityRating(): MutableLiveData<String> {
        return mutableItemMaturityRating
    }

    fun getMutableItemLongDescription(): MutableLiveData<String> {
        return mutableItemLongDescription
    }

    fun getMutableItemPrice(): MutableLiveData<String> {
        return mutableItemPrice
    }

}