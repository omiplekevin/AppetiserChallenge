package com.android.appetisermasterdetail.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.appetisermasterdetail.App
import com.android.appetisermasterdetail.R
import com.android.appetisermasterdetail.databinding.ActivityMainBinding
import com.android.appetisermasterdetail.di.component.DaggerActivityComponent
import com.android.appetisermasterdetail.di.module.ActivityModule
import com.android.appetisermasterdetail.helper.DatabaseHelper
import com.android.appetisermasterdetail.viewmodel.MainViewModel
import com.google.gson.Gson
import timber.log.Timber
import javax.inject.Inject

/**
 * Main Activity presents listing of the items from the request API
 */
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var databaseHelper: DatabaseHelper

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //inject this activity
        DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .applicationComponent((application as App).getApplicationComponent())
            .build()
            .inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.setDatabaseHelper(databaseHelper)

        binding.storeList.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.storeList.adapter = viewModel.itemRecyclerViewAdapter
        viewModel.itemRecyclerViewAdapter.onItemClick = {
            val intent = Intent(this, ItemDetailActivity::class.java)
            intent.putExtra("item_detail_json", Gson().toJson(it))
            startActivity(intent)
        }
        binding.swipeContainer.setOnRefreshListener {
            if (::viewModel.isInitialized) {
                binding.swipeContainer.isRefreshing = true
                viewModel.requestSongList {
                    binding.swipeContainer.isRefreshing = false
                }
            }
        }

        binding.viewModel = viewModel
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume!")
        //check if view model is initialized
        if (::viewModel.isInitialized) {
            //set swipe refresh UI to refreshing state
            binding.swipeContainer.isRefreshing = true
            viewModel.requestSongList {
                //after receiving data from view model, set swipe refresh UI to false
                binding.swipeContainer.isRefreshing = false
            }
        } else {
            Timber.e("ViewModel is not initialized!")
        }
    }
}