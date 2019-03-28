package com.gc.databinidingadapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class DataBindingRecyclerAdapter<T>(
        var list: MutableList<T>,
        var itemLayout: Int,
        var itemClickListener: OnItemClickListener<T>? = null
) : RecyclerView.Adapter<DataBindingRecyclerViewHolder>(), ObservableList.OnListChangedListener<T> {

    init { (list as? ObservableList<T>)?.addListener(this) }

    override fun onItemChanged(position: Int, item: T) = notifyItemChanged(position)

    override fun onItemRemoved(position: Int, item: T) = notifyItemRemoved(position)

    override fun onItemAdded(position: Int, item: T) = notifyItemRemoved(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataBindingRecyclerViewHolder(DataBindingUtil.bind(parent.inflate(itemLayout)))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DataBindingRecyclerViewHolder, position: Int) {
        with(holder.binding){
            if (itemClickListener != null)
                root.setOnClickListener{ itemClickListener?.onItemClick(list[position])}
            setVariable(BR.viewModel, list[position])
        }
    }

}