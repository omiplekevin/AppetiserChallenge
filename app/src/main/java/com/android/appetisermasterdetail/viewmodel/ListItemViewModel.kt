package com.android.appetisermasterdetail.viewmodel

import androidx.lifecycle.MutableLiveData
import com.android.appetisermasterdetail.data.model.ITunesItemData

class ListItemViewModel: BaseViewModel() {

    private val mutableItemName = MutableLiveData<String>("")
    private val mutableItemGenre = MutableLiveData<String>("")
    private val mutableItemPrice = MutableLiveData<String>("")
    private val mutableItemCover = MutableLiveData<String>("")

    fun bind(iTunesItemData: ITunesItemData) {
        mutableItemName.value = iTunesItemData.trackName
        mutableItemGenre.value = iTunesItemData.primaryGenreName
        mutableItemPrice.value = "${iTunesItemData.trackPrice} ${iTunesItemData.currency}"
        mutableItemCover.value = iTunesItemData.artworkUrl100
    }

    fun getMutableItemName(): MutableLiveData<String> {
        return mutableItemName
    }

    fun getMutableItemGenre(): MutableLiveData<String> {
        return mutableItemGenre
    }

    fun getMutableItemPrice(): MutableLiveData<String> {
        return mutableItemPrice
    }

    fun getMutableItemCover(): MutableLiveData<String> {
        return mutableItemCover
    }
}