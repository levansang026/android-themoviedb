package com.bill.android.themoviedb.view

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val spaceSize: Int,
    private val orientation: Int = LinearLayout.VERTICAL,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        if (parent.adapter != null) {
            if (parent.getChildAdapterPosition(view) == parent.adapter!!.itemCount - 1) {
                return
            }

            with(outRect) {
                when (orientation) {
                    LinearLayout.HORIZONTAL -> {
                        right = spaceSize
                    }

                    LinearLayout.VERTICAL -> {
                        bottom = spaceSize
                    }
                }
            }
        }
    }
}