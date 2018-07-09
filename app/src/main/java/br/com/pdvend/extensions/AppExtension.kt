package br.com.pdvend.extensions

import android.content.Context
import android.support.annotation.StringRes
import android.view.View
import br.com.pdvend.app.PDVendApplication

fun Any.getAppContext(): Context = PDVendApplication.getApplication()

fun Any.getString(@StringRes resourceId: Int) = getAppContext().resources.getString(resourceId)

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun View.visible(){
    visibility = View.VISIBLE
}