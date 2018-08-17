package com.gc.databinidingadapters

import android.content.Context
import android.support.v7.widget.*
import android.util.AttributeSet

class DataBindingRecyclerView(context : Context, attrs : AttributeSet)
    : RecyclerView(context, attrs) {

    private enum class LayoutManager {
        VERTICAL, HORIZONTAL, GRID
    }

    var data : MutableList<Any>? = null
        set(value){
            data = value
            if(value != null && itemLayout != -1)
                adapter = DataBindingRecyclerAdapter(value, itemLayout, onItemClickListener as OnItemClickListener<Any>?)
    }
    var itemLayout: Int = -1
        set(value) {
            field = value
            data?.let {
                if(value != -1)
                    adapter = DataBindingRecyclerAdapter(it, itemLayout, onItemClickListener as com.gc.databinidingadapters.OnItemClickListener<Any>?)
            }
        }
    var onItemClickListener : OnItemClickListener<*>? = null

    init {
        context.obtainStyledAttributes(attrs, R.styleable.DataBindingRecyclerView)?.let{ styleAttributes ->
            itemLayout = styleAttributes.getResourceId(R.styleable.DataBindingRecyclerView_recyclerItemLayout, -1)
            val lm = styleAttributes.getInteger(R.styleable.DataBindingRecyclerView_type, -1)
            layoutManager = when (LayoutManager.values()[lm]) {
                DataBindingRecyclerView.LayoutManager.HORIZONTAL -> LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
                DataBindingRecyclerView.LayoutManager.GRID -> {
                    val colums = styleAttributes.getInteger(R.styleable.DataBindingRecyclerView_columns, 1)
                    GridLayoutManager(getContext(), colums)
                }
                else -> LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
            }
            styleAttributes.recycle()
        }
    }

//    fun setData(data : MutableList<*>) {
//        this.data = data
//        if (itemLayout != -1) {
//            adapter = DataBindingRecyclerAdapter(data, context, itemLayout, onItemClickListener)
//        }
//    }

}