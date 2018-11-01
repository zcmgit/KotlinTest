package com.example.kotlin.banner

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bigkoo.convenientbanner.ConvenientBanner
import com.example.kotlin.R

/**
 * @author zcm
 * @create 2018/11/01
 * @Describe
 */
class BannerActivity : AppCompatActivity() {

    private lateinit var convenientBanner: ConvenientBanner<Int>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.banner_activity)
        initBanner()
    }
    fun initBanner(){
        convenientBanner = findViewById(R.id.banner)
        convenientBanner.setPages( { BannerImageHolderView() },initList())
                .setPageIndicator(intArrayOf(R.mipmap.select_icon,R.mipmap.unselect_icon))
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(2000)
    }

    fun initList() : List<Int>{
        var list : List<Int>  = listOf(R.mipmap.bottom_me,R.mipmap.bottom_home,R.mipmap.bottom_shop)
        return list!!
    }
}