package com.byy.mvvm_interviewtest.html

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.byy.mvvm_interviewtest.BaseActivity
import com.byy.mvvm_interviewtest.R
import com.byy.mvvm_interviewtest.databinding.ActivityHtmlBinding
import com.byy.mvvm_interviewtest.util.check
import kotlinx.android.synthetic.main.activity_html.*
import org.jetbrains.anko.toast



class HtmlActivity : BaseActivity<HtmlViewModel>(){
//    override fun initViewModel(): Class<HtmlViewModel> = HtmlViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityHtmlBinding>(this, R.layout.activity_html).apply {
            viewModel=mViewModel
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        mViewModel.liveData.observe(this, Observer {
            changeAlertType()
            when(it){
                null->{}
                ""->toast("未找到图片地址")
                else->mViewModel.url.set("https:$it")
            }

        })
    }

    fun go(v: View){
        val url=et_url.text.toString().trim()
        if (check(url)) {
            showProgress()
            mViewModel.parseDesImage(url)
        }
        else toast("非法地址")
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }


}
