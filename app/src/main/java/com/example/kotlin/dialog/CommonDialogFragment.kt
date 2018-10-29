package com.example.kotlin.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.DisplayMetrics
import android.view.*
import android.widget.Button
import android.widget.TextView
import com.example.kotlin.R
import com.example.kotlin.adapter.MyAdapter
import com.example.kotlin.tool.ScreenUtil
import kotlinx.android.synthetic.main.common_dialog.*

/**
 * @author zcm
 * @create 2018/10/29
 * @Describe
 */
class CommonDialogFragment : DialogFragment() {

    var myInter: MyClickInter? = null
    override fun onStart() {
        super.onStart()
        if (dialog != null) {
            val metrics = DisplayMetrics()
            activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
//            val width = (metrics.widthPixels * 0.75F).toInt()
            var width = ScreenUtil.dp2px(dialog.context,260f)
            var height = ScreenUtil.dp2px(dialog.context,220f)
            dialog.window?.setLayout(width, height)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, R.style.SimpleDialogStyle)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)//设置窗体为无标题
        var view: View = inflater.inflate(R.layout.common_dialog, container, false)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    private fun initView(view: View){
        var contentTxt = view.findViewById(R.id.content_txt) as TextView
        var continueBtn = view.findViewById(R.id.continue_txt) as TextView
        var closeBtn = view.findViewById(R.id.close_txt) as TextView

        contentTxt.text = "确定继续操作？"
        continueBtn.setOnClickListener {
            dismiss()
            myInter?.onClickContinue()
        }
        closeBtn.setOnClickListener {
            dismiss()
            myInter?.onClickClose()
        }
    }
    interface MyClickInter{    //自定义的接口（测试接口方式的使用）
        fun onClickContinue()
        fun onClickClose()
    }
    fun setOnClickListener(inter:MyClickInter){
        this.myInter = inter
    }

}