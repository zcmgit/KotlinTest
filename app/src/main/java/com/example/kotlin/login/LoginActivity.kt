package com.example.kotlin.login

import android.content.Intent
import android.util.Log
import com.example.kotlin.R
import com.example.kotlin.list.ListActivity
import com.example.kotlin.tool.Preference
import com.zhangqie.mvphttp.mvp_retrofit_rxjava.base.BaseActivity
import com.zhangqie.mvphttp.mvp_retrofit_rxjava.presenter.LoginPresenter
import com.zhangqie.mvphttp.mvp_retrofit_rxjava.view.IView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity : BaseActivity<IView, LoginPresenter>(), IView {

    private var variable by Preference("login_time", "10")

    override fun onError(error: String) {
        Log.d("test==",error)
        toast(error)
    }

    override fun onLoadContributorComplete(data: String) {
        toast(data)
        var time = Date().getNowDateTime()
        variable = time
        val intent = Intent()
        //获取intent对象
        intent.setClass(this, ListActivity::class.java)
        // 获取class是使用::反射(那么问题来了,反射是个什么鬼?👻👻👻👻小白的悲哀啊,赶紧研究研究去)
        startActivity(intent)
    }

    override fun setMainLayout(): Int {
        return R.layout.activity_main
    }

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }
    fun Date.getNowDateTime(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(this)
    }
    override fun initView() {
        commit_btn.setOnClickListener{
            var name = name_edit.text.toString()
            var pwd = pwd_edit.text.toString()
            if(name == null || pwd == null){
                toast("请输入完整信息")
                return@setOnClickListener
            }
            val map = HashMap<String,String>()
            map.put("login", name)
            map.put("pwd", pwd)
            map.put("uniqueCode","T203P87V40445")
            map.put("posName","0C:25:76:1F:06:21")
            p!!.onRequest(map)
        }
    }

    override fun initBeforeData() {
    }

}
