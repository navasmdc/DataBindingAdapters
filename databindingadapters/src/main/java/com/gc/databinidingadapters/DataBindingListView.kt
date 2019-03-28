package com.gc.databinidingadapters

import android.content.Context
import android.util.AttributeSet
import android.widget.ListView

class DataBindingListView(context: Context, attrs: AttributeSet)
    : ListView(context, attrs) {

    var data: List<Any>? = null
        set(value) {
            field = value
            if (value != null && itemLayout != -1)
                adapter = DataBindingAdapter(value, itemLayout, onItemClickListener as com.gc.databinidingadapters.OnItemClickListener<Any>?)
        }
    var itemLayout: Int = -1
        set(value) {
            field = value
            data?.let {
                if(value != -1)
                    adapter = DataBindingAdapter(it, itemLayout, onItemClickListener as com.gc.databinidingadapters.OnItemClickListener<Any>?)
            }
        }

    var onItemClickListener: com.gc.databinidingadapters.OnItemClickListener<*>? = null

    init {
        context.obtainStyledAttributes(attrs, R.styleable.DataBindingListView)?.let {
            itemLayout = it.getResourceId(R.styleable.DataBindingListView_itemLayout, -1)
            it.recycle()
        }
    }


}