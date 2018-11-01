package com.example.kotlin.banner

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bigkoo.convenientbanner.holder.Holder

/**
 * @author zcm
 * @create 2018/11/01
 * @Describe
 */
class BannerImageHolderView : Holder<Int>{

    private var imageView: ImageView? = null
    override fun createView(context: Context): View {
        //此处可以根据需求创建任何你想要的布局，不一定是imageView控件
        imageView = ImageView(context)
        imageView!!.scaleType = ImageView.ScaleType.FIT_XY
        return imageView as ImageView
    }

    override fun UpdateUI(context: Context, position: Int, data: Int) {
        imageView!!.setImageResource(data)
    }
}