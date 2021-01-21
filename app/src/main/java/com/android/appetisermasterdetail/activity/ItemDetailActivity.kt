package com.android.appetisermasterdetail.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.appetisermasterdetail.R
import com.android.appetisermasterdetail.data.model.ITunesItemData
import com.android.appetisermasterdetail.databinding.ActivityItemDetailBinding
import com.android.appetisermasterdetail.viewmodel.ItemDetailViewModel
import com.google.gson.Gson

class ItemDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemDetailViewModel
    private lateinit var binding: ActivityItemDetailBinding

    private lateinit var iTunesItemData: ITunesItemData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)

        viewModel = ViewModelProvider(this).get(ItemDetailViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_detail)
        binding.viewModel = viewModel

        iTunesItemData = Gson().fromJson(intent.extras?.getString("item_detail_json"), ITunesItemData::class.java)
    }

    override fun onResume() {
        super.onResume()
        viewModel.bind(iTunesItemData)
    }
}