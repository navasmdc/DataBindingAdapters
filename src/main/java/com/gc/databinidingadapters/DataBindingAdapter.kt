package com.gc.databinidingadapters

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * Simple adaptar that implements databinding to bind every item from list with his item view
 */
class DataBindingAdapter<T>(
        private val list : List<T>,
        private val itemLayout : Int,
        var itemClickListener : OnItemClickListener<T>? = null
        ) : BaseAdapter(), ObservableList.OnListChangedListener<T>{

    init {
        (list as? ObservableList<T>)?.addListener(this)
    }

    override fun onItemChanged(position : Int, item : T) = notifyDataSetChanged()

    override fun onItemRemoved(position : Int, item : T) = notifyDataSetChanged()

    override fun onItemAdded(position : Int, item : T) = notifyDataSetChanged()

    override fun getView(position : Int,
                         recyclerView : View?,
                         parent : ViewGroup
    ) : View {
        val binding : ViewDataBinding =
                if(recyclerView != null) recyclerView.tag as ViewDataBinding
                else {
                    with(parent.inflate(itemLayout)) {
                        DataBindingUtil.bind<ViewDataBinding>(this).apply {
                            root.tag = this
                            setVariable(BR.viewModel, getItem(position))
                        }
                    }
                }
        binding.setVariable(BR.viewModel, getItem(position))
        return binding.root.also {
            if(itemClickListener != null)
                it.setOnClickListener { itemClickListener?.onItemClick(getItem(position)) }
        }
    }

    override fun getItem(position : Int) : T = list[position]

    override fun getItemId(position : Int) : Long = position.toLong()

    override fun getCount() : Int = list.size
}