package com.byy.mvvm_interviewtest

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.byy.mvvm_interviewtest.exchangerate.ExchangeRateActivity
import com.byy.mvvm_interviewtest.html.HtmlActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(MainPresenter())
        button.setOnClickListener { startActivity<HtmlActivity>() }
        button2.setOnClickListener { startActivity<ExchangeRateActivity>() }
    }


}
