package com.example.kotlin.bottomnav.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin.R
import kotlinx.android.synthetic.main.bottom_fragment.*

/**
 * @author zcm
 * @create 2018/10/31
 * @Describe
 */
class MeFragment : Fragment(){

    private var param : String ? = ""
    companion object {
        fun newInstance(param : String) : MeFragment{
            var meFragment = MeFragment()
            var bundle = Bundle()
            bundle.putString("param",param)
            meFragment.arguments = bundle
            return meFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        param = arguments!!.getString("param")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.bottom_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_content.text = param
    }
}