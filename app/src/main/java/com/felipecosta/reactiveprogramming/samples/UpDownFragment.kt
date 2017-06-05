package com.felipecosta.reactiveprogramming.samples


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.rxbinding2.view.clicks
import io.reactivex.Observable.just
import io.reactivex.Observable.merge

class UpDownFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_up_down, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val upButton = view!!.findViewById(R.id.up)
        val downButton = view.findViewById(R.id.down)
        val resultTextView = view.findViewById(R.id.result) as TextView

        val upTaps = upButton.clicks()
        val downTaps = downButton.clicks()

        merge(just(0), upTaps.map { 1 }, downTaps.map { -1 })
                .scan(0, { t1, t2 -> t1 + t2 })
                .map { it.toString() }
                .subscribe(resultTextView::setText)
    }

    companion object {
        fun newInstance() = UpDownFragment()
    }

}
