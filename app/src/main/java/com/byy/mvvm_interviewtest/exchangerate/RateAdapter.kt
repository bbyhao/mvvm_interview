package com.byy.mvvm_interviewtest.exchangerate

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import com.byy.mvvm_interviewtest.R
import com.byy.mvvm_interviewtest.data.RateItem
import com.byy.mvvm_interviewtest.databinding.ItemExchangRateBinding
import com.chad.library.adapter.base.BaseItemDraggableAdapter
import com.chad.library.adapter.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_exchang_rate.view.*

/**
 * @author Created by fq on 2018/4/28.
 */
class RateAdapter(mList: List<RateItem>?) : BaseItemDraggableAdapter<RateItem,
        RateAdapter.ViewHolder>(R.layout.item_exchang_rate, mList) {
    fun notifyData(mList: List<RateItem>) {
        mData.apply {
            clear()
            addAll(mList)
        }
        notifyDataSetChanged()
    }

    override fun convert(holder: ViewHolder?, item: RateItem?) {
        holder?.binding?.rate = item!!

    }

    inner class ViewHolder(view: View?) : BaseViewHolder(view) {
        var binding: ItemExchangRateBinding = ItemExchangRateBinding.bind(view!!)
        var l: TextWatcher

        init {
            l = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }

                override fun afterTextChanged(s: Editable?) {
                    itemView.et_item_rate.removeTextChangedListener(this)
                    val value = itemView.et_item_rate.text.toString().trim()
                    if (!TextUtils.isEmpty(value)) {
                        for ((index, it) in mData.withIndex()) {
                            if (index == adapterPosition) continue
                            val money = it.data.div(mData[adapterPosition].data).times(value.toDouble())
                            it.result = String.format("%.3f %n",money)
                        }
                        notifyItemRangeChanged(0, adapterPosition)
                        notifyItemRangeChanged(adapterPosition + 1, mData.size - adapterPosition - 1)
                    }
                    itemView.et_item_rate.addTextChangedListener(this)
                }

            }
            itemView.et_item_rate.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) itemView.et_item_rate.addTextChangedListener(l)
                else itemView.et_item_rate.removeTextChangedListener(l)
            }
        }


    }
}