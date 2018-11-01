package com.example.kotlin.tablayout.fragment

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
class TabHomeFragment : Fragment(){

    private var param : String ? = ""

    companion object {
        fun newInstance (param : String) : TabHomeFragment{
            var fragment = TabHomeFragment();
            var args = Bundle()
            args.putString("param",param)
            fragment.arguments = args
            return fragment
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