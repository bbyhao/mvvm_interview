package com.byy.mvvm_interviewtest

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.byy.mvvm_interviewtest.util.obtainViewModel
import java.lang.reflect.ParameterizedType

/**
 * @author Created by fq on 2018/4/28.
 */
abstract class BaseActivity<T : ViewModel> : AppCompatActivity() {
    private var mDialog: SweetAlertDialog? = null
    private var i = -1
    lateinit var mViewModel: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = obtainViewModel(initViewModel())
    }

    private fun initViewModel(): Class<T> {
        var temp: Class<*> = this.javaClass
        while (temp != null) {
            val genericSuperclass = temp.genericSuperclass
            if (genericSuperclass is ParameterizedType) {
                for (type in genericSuperclass.actualTypeArguments) {
                    if (type is Class<*> && ViewModel::class.java.isAssignableFrom(type)) {
                       return type as Class<T>
                    }else if (type is ParameterizedType) {
                        val rawType = type.rawType
                        if (rawType is Class<*> && ViewModel::class.java.isAssignableFrom(rawType)) {
                            return rawType as Class<T>
                        }
                    }
                }
            }
            temp = temp.superclass
        }
        return ViewModel::class.java as Class<T>
    }

    fun showProgress(title: String = "loading", cancelable: Boolean = false, onCancelListener: (() -> Unit) = {}) {
        i = -1
        mDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE).apply {
            titleText = title
            setCancelable(cancelable)
            setOnDismissListener {
                //                timer.cancel()
                onCancelListener()
            }
        }
//        timer.start()
    }

    fun changeAlertType(title: String = "Success!", comfirmText: String = "OK", type: Int = SweetAlertDialog.SUCCESS_TYPE) {
//        timer.cancel()
        mDialog?.setTitleText(title)
                ?.setConfirmText(comfirmText)
                ?.changeAlertType(type)
    }

//    private var timer = object : CountDownTimer((Int.MAX_VALUE).toLong(), 800) {
//        override fun onTick(millisUntilFinished: Long) {
//            i++
//            when (i % 7) {
//                0 -> mDialog?.progressHelper?.barColor = resources.getColor(R.color.blue_btn_bg_color)
//                1 -> mDialog?.progressHelper?.barColor = resources.getColor(R.color.material_deep_teal_50)
//                2 -> mDialog?.progressHelper?.barColor = resources.getColor(R.color.success_stroke_color)
//                3 -> mDialog?.progressHelper?.barColor = resources.getColor(R.color.material_deep_teal_20)
//                4 -> mDialog?.progressHelper?.barColor = resources.getColor(R.color.material_blue_grey_80)
//                5 -> mDialog?.progressHelper?.barColor = resources.getColor(R.color.warning_stroke_color)
//                6 -> mDialog?.progressHelper?.barColor = resources.getColor(R.color.success_stroke_color)
//            }
//        }
//
//        override fun onFinish() {
//
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        mDialog?.dismiss()
//        timer.cancel()
    }
}