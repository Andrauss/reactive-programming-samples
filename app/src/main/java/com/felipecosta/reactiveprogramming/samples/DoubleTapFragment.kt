package com.felipecosta.reactiveprogramming.samples


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class DoubleTapFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_double_tap, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val container = view!!.findViewById(R.id.container)
        val textView = view.findViewById(R.id.text) as TextView

        container.clicks()
                .buffer(700L, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map { it.size }
                .map { if (it == 2) "Double Tap" else "" }
                .subscribe(textView::setText)
//                .filter { it == 2 }
//                .subscribe { textView.text = "Double Tap" }
    }

    companion object {
        fun newInstance() = DoubleTapFragment()
    }

}
