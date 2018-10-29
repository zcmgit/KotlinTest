package com.example.kotlin.drawer.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.kotlin.R
import kotlinx.android.synthetic.main.fragment_1.*

/**
 * @author zcm
 * @create 2018/10/29
 * @Describe
 */
class Fragment3 : BaseFragment(){

    override fun getLayoutResources(): Int {
        return R.layout.fragment_1
    }

    override fun initView() {
        fragment_text.text = "Fragment3"
    }

    override fun loadData() {
    }

}