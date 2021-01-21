package com.android.appetisermasterdetail.viewmodel

import com.android.appetisermasterdetail.adapter.ItemRecyclerViewAdapter
import com.android.appetisermasterdetail.data.model.ITunesItemData
import com.android.appetisermasterdetail.helper.DatabaseHelper
import com.android.appetisermasterdetail.network.AppleAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class MainViewModel: BaseViewModel() {

    @Inject
    @field:Named("AppleNoAuthAPI")
    lateinit var appleAPI: AppleAPI

    private lateinit var subscription: Disposable
    private lateinit var databaseHelper: DatabaseHelper

    val itemRecyclerViewAdapter: ItemRecyclerViewAdapter = ItemRecyclerViewAdapter()

    init {
        Timber.tag(javaClass.name)
    }

    override fun onCleared() {
        super.onCleared()
        if (::subscription.isInitialized) {
            //dispose subscriptions from Observables
            subscription.dispose()
        }
    }

    /**
     * set database helper for caching purposes
     */
    fun setDatabaseHelper(databaseHelper: DatabaseHelper) {
        this.databaseHelper = databaseHelper
    }

    /**
     * Perform GET request to gather information from the configured API.
     * This will also query database for saved entries
     */
    fun requestSongList(callback: () -> Unit) {
        getITunesDataListFromDatabase { result ->
            Timber.d("DB has result!")
            itemRecyclerViewAdapter.updateItunesDataList(result)
        }
        if (::appleAPI.isInitialized) {
            subscription = appleAPI.getSongList("star", "au", "movie")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    Timber.d("API has result!")
                    //save to database for caching
                    saveToDatabase(result.results)
                    //update the recyclerview adapter for presentation
                    itemRecyclerViewAdapter.updateItunesDataList(result.results)
                    //invoke caller for completed operation
                    callback.invoke()
                }, {
                    Timber.e(it)
                    //invoke caller for completed operation
                    callback.invoke()
                })
        } else {
            Timber.e("API is not initialized!")
        }
    }

    /**
     * Saving items through Room database with
     * strategy set to OnConflictStrategy.REPLACE
     */
    private fun saveToDatabase(iTunesListData: List<ITunesItemData>) {
        if (::databaseHelper.isInitialized) {
            subscription = databaseHelper.iTunesItemDao()
                .insertAll(iTunesListData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("saved to database!")
                }, {
                    Timber.e(it)
                })
        }
    }

    /**
     * Fetch save entries from the database
     */
    private fun getITunesDataListFromDatabase(callback: (iTunesDataList: List<ITunesItemData>) -> Unit) {
        if (::databaseHelper.isInitialized) {
            subscription = databaseHelper.iTunesItemDao()
                .getAllTopicData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({result ->
                    //invoke the result to the caller
                    callback.invoke(result)
                }, {
                    Timber.e(it)
                })
        }
    }
}