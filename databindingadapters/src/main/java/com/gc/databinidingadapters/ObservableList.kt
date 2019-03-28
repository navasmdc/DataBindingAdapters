package com.gc.databinidingadapters

class ObservableList<T> : ArrayList<T>() {

    private var listeners = arrayListOf<OnListChangedListener<T>>()

    fun addListener(listener : OnListChangedListener<T>) : Boolean = listeners.add(listener)

    fun removeListener(listener : OnListChangedListener<T>) : Boolean = listeners.remove(listener)

    override fun add(element : T) : Boolean {
        val result = super.add(element)
        listeners.forEach{ it.onItemAdded(size-1, element)}
        return result
    }

    override fun addAll(elements : Collection<T>) : Boolean{
        elements.forEach {add(it)}
        return true
    }

    override fun remove(element : T) : Boolean {
        val index = indexOf(element)
        val result = super.remove(element)
        if(result) listeners.forEach{ it.onItemRemoved(index, element)}
        return result
    }

    override fun removeAt(index : Int) : T {
        val result = super.removeAt(index)
        listeners.forEach{ it.onItemRemoved(index, result)}
        return result
    }

    override fun removeAll(elements : Collection<T>) : Boolean {
        elements.forEach {remove(it)}
        return true
    }

    fun notifyItemChanged(index : Int){
        val element = get(index)
        listeners.forEach{ it.onItemChanged(index, element)}
    }

    fun notifyItemChanged(element : T){
        val index = indexOf(element)
        listeners.forEach{ it.onItemChanged(index, element)}
    }


    interface OnListChangedListener<in T>{
        fun onItemChanged(position : Int, item : T)
        fun onItemRemoved(position : Int, item : T)
        fun onItemAdded(position : Int, item : T)
    }
}