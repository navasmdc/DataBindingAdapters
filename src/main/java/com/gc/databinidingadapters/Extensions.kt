package com.gc.databinidingadapters

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

internal fun ViewGroup.inflate(@LayoutRes layout: Int, attach: Boolean = false): View =
        LayoutInflater.from(context).inflate(layout,this, attach)

