package br.com.pdvend.ui.fragments

import android.support.v4.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class PDVendBaseFragment : Fragment() {

    abstract fun fragmentTag(): String

    abstract fun fragmentTitle(): String

    val subscriptions = CompositeDisposable()

    fun subscribe(disposable: Disposable): Disposable {
        subscriptions.add(disposable)
        return disposable
    }

    override fun onStop() {
        super.onStop()
        subscriptions.clear()
    }
}