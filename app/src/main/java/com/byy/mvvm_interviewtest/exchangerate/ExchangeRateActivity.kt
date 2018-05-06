package com.byy.mvvm_interviewtest.exchangerate

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.byy.mvvm_interviewtest.BaseActivity
import com.byy.mvvm_interviewtest.R
import com.byy.mvvm_interviewtest.databinding.ActivityExchangerateBinding
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_exchangerate.*
import org.jetbrains.anko.toast


/**
 * @author Created by fq on 2018/4/28.
 */
class ExchangeRateActivity : BaseActivity<ExchangeRateViewMode>() {

    companion object {
         val Rate_ORDER="rate_order"
    }

//    override fun initViewModel(): Class<ExchangeRateViewMode> = ExchangeRateViewMode::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityExchangerateBinding>(this, R.layout.activity_exchangerate)
                .apply {
                    viewModel = mViewModel
                }

        initData()
        initListener()
    }

    private fun initListener() {
        tv_rate_update.setOnClickListener {
            mViewModel.update()
        }

    }

    private fun initData() {
        recycleView.layoutManager = LinearLayoutManager(this)
        val mAdapter = RateAdapter(mViewModel.item.get())
        recycleView.adapter = mAdapter
        mViewModel.update()

        val itemDragAndSwipeCallback = ItemDragAndSwipeCallback(mAdapter)
        val itemTouchHelper = ItemTouchHelper(itemDragAndSwipeCallback)
        itemTouchHelper.attachToRecyclerView(recycleView)

        // 开启拖拽
        mAdapter.enableDragItem(itemTouchHelper)
        mAdapter.setOnItemDragListener(object :OnItemDragListener{
            override fun onItemDragMoving(source: RecyclerView.ViewHolder?, from: Int, target: RecyclerView.ViewHolder?, to: Int) {
            }

            override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
            }

            override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder?, pos: Int) {
                val map=HashMap<String,Int>()
                for ((index,item) in mAdapter.data.withIndex()) {
                    map[item.country] = index
                }
                mViewModel.mOrder=Gson().toJson(map)
            }

        })

        // 开启滑动删除
//        mAdapter.enableSwipeItem()
//        mAdapter.setOnItemSwipeListener(onItemSwipeListener)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            toast("1111111")
        }

    }

}