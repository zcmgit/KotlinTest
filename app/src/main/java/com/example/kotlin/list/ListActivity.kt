package com.example.kotlin.list

import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.TextView
import com.example.kotlin.R
import com.example.kotlin.adapter.MyAdapter
import com.example.kotlin.dialog.CommonDialog
import com.example.kotlin.dialog.CommonDialogFragment
import com.example.kotlin.tool.Preference
import kotlinx.android.synthetic.main.common_dialog.*
import kotlinx.android.synthetic.main.list.*
import org.jetbrains.anko.toast

/**
 * @author zcm
 * @create 2018/10/26
 * @Describe
 */
class ListActivity : AppCompatActivity() {
    private var variable by Preference("login_time", "10")
    var listDatas = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list)
        this.show_txt?.text = "登录时间：" + variable
        var adapter = MyAdapter(this, initData()!!)
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        adapter.setOnClickListener(object : MyAdapter.MyInter {     //自定义接口回调
            override fun onclick(p0: Int) {
                when(p0){
                    0 -> showDialog()
                    1 -> showCommonDialogFragment()
                    else -> toast("click：：" + p0 + "::" + listDatas.get(p0))
                }
            }
        })
    }

    private fun initData(): ArrayList<String>? {
        listDatas.add("提示对话框")
        listDatas.add("自定义DialogFragment")

        for (i in 2..50) {
            var test = "测试Test"
            listDatas.add(i, test)
        }
        Log.d("tag", "list.size------" + listDatas.size)
        return listDatas
    }

    private fun showCommonDialogFragment(){
        var dialog: CommonDialogFragment = CommonDialogFragment()
        dialog.show(supportFragmentManager, "tag")
        dialog.setOnClickListener(object : CommonDialogFragment.MyClickInter{
            override fun onClickContinue() {
                toast("点击继续")
            }

            override fun onClickClose() {
                toast("点击关闭")
            }

        })
    }
    private fun showDialog(){
        CommonDialog.Builder(this)
                .setImageHeader(R.mipmap.ic_launcher_round)
                .setTitle("提示")
                .setMessage("是否需要继续？")
                .setPositiveButton("继续", DialogInterface.OnClickListener { p0, p1 ->
                    p0?.dismiss()
                    toast("点击了继续")
                })
                .setNegativeButton("取消", DialogInterface.OnClickListener { p0, p1 ->
                    p0?.dismiss()
                    toast("点击了取消")
                })
                .create()
                .show()
    }
}