package com.example.kotlin.edittext

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputFilter
import com.example.kotlin.R
import com.example.kotlin.tool.NumberInputFilter
import kotlinx.android.synthetic.main.edittext_test.*
import org.jetbrains.anko.toast

/**
 * @author zcm
 * @create 2018/11/15
 * @Describe
 */
class EdittextTestActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edittext_test)

        edittext.filters = arrayOf(NumberInputFilter(0.00, 100.00, 2, false))

        btn.setOnClickListener {
            var max = max_edittext.text
            var min = min_edittext.text

            if(max == null || min == null){
                toast("请输入完整信息")
            }else{
                var maxDouble : Double = max.toString().toDouble()
                var minDouble : Double = min.toString().toDouble()
                edittext.filters = arrayOf(NumberInputFilter(minDouble, maxDouble, 2, false))
                toast("设置完成")
            }
        }
    }
}