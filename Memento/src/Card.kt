package com.example.memento

import android.content.Context
import android.widget.ImageView
import androidx.core.content.ContextCompat

class Card(context: Context, val imageResId: Int) : ImageView(context) {

    private var isRevealed = false

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        scaleType = ScaleType.CENTER_CROP
        setBackgroundColor(ContextCompat.getColor(context, android.R.color.darker_gray))
    }

    fun reveal() {
        isRevealed = true
        setImageResource(imageResId)
    }

    fun hide() {
        isRevealed = false
        setImageResource(0)
    }
}