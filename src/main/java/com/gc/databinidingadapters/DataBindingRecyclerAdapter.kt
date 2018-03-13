package com.gc.databinidingadapters

import android.content.Context
import android.databinding.*
import android.support.v7.widget.RecyclerView
import android.view.*

class DataBindingRecyclerAdapter<T>(
        var context : Context,
        var list : MutableList<*>,
        var itemLayout : Int,
        var itemClickListener : OnItemClickListener<T>? = null
) : RecyclerView.Adapter<DataBindingRecyclerViewHolder>(), ObservableList.OnListChangedListener<T>{

    init {
        if(list is ObservableList<*>)
            (list as ObservableList<T>).addListener(this)
    }

    override fun onItemChanged(position : Int,
                               item : T
    ) {
        notifyItemChanged(position)
    }

    override fun onItemRemoved(position : Int,
                               item : T
    ) {
        notifyItemRemoved(position)
    }

    override fun onItemAdded(position : Int,
                             item : T
    ) {
        notifyItemInserted(position)
    }
    override fun onCreateViewHolder(parent : ViewGroup?,
                                    viewType : Int
    ) : DataBindingRecyclerViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), itemLayout, parent, false)
        return DataBindingRecyclerViewHolder(binding)
    }

    override fun getItemCount() : Int = list.size

    override fun onBindViewHolder(holder : DataBindingRecyclerViewHolder?,
                                  position : Int
    ) {
        val binding = holder?.binding
        if(itemClickListener != null)
        binding!!.root.setOnClickListener({
            itemClickListener?.onItemClick(list[position] as T)
        })
        binding?.setVariable(BR.viewModel, list[position])
    }

}