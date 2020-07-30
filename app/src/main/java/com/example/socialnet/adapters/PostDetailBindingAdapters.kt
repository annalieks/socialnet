package com.example.socialnet.adapters

import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.example.socialnet.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter("isFabGone")
fun bindIsFabGone(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.hide()
    } else {
        view.show()
    }
}

@BindingAdapter("renderHtml")
fun bindRenderHtml(view: TextView, description: String?) {
    if (description != null) {
        view.text = HtmlCompat.fromHtml(description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        view.movementMethod = LinkMovementMethod.getInstance()
    } else {
        view.text = ""
    }
}