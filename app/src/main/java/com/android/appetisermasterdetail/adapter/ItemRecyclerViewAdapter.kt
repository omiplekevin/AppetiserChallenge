package com.android.appetisermasterdetail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.appetisermasterdetail.R
import com.android.appetisermasterdetail.data.model.ITunesItemData
import com.android.appetisermasterdetail.databinding.ListItemViewBinding
import com.android.appetisermasterdetail.viewmodel.ListItemViewModel
import timber.log.Timber

class ItemRecyclerViewAdapter : RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder>(){

    private var iTunesDataList: List<ITunesItemData> = listOf()
    var onItemClick: ((ITunesItemData) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemListBinding: ListItemViewBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_view, parent, false
            )

        return ViewHolder(itemListBinding)
    }

    override fun getItemCount(): Int {
        return iTunesDataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(iTunesDataList[position])
    }

    fun updateItunesDataList(iTunesDataList: List<ITunesItemData>?) {
        if (iTunesDataList != null) {
            this.iTunesDataList = iTunesDataList
            notifyDataSetChanged()
        } else {
            Timber.e("Itunes data list is empty!")
        }
    }

    inner class ViewHolder (
        private val binding: ListItemViewBinding
    ):RecyclerView.ViewHolder(binding.root) {

        private val viewModel = ListItemViewModel()

        fun bind(iTunesItemData: ITunesItemData) {
            viewModel.bind(iTunesItemData)
            binding.viewModel = viewModel
            binding.root.setOnClickListener {
                onItemClick?.invoke(iTunesItemData)
            }
        }

    }
}