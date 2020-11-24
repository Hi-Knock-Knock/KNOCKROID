package com.all_the_best.knock_knock.parent_layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.fragment_parent_home.*
import kotlinx.android.synthetic.main.rcv_parent_home.*

class ParentHomeFragment : Fragment() {
    private lateinit var parentHomeAdapter: ParentHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        parentHomeAdapter = ParentHomeAdapter(view.context)
        parent_home_rcv.apply{
            adapter = parentHomeAdapter
            layoutManager = LinearLayoutManager(view.context).also { it.orientation = LinearLayoutManager.HORIZONTAL }
        }
        parentHomeAdapter.data = mutableListOf(
            ParentHomeData(R.drawable.rcv_parent_home_img),
            ParentHomeData(R.drawable.rcv_parent_home_img),
            ParentHomeData(R.drawable.rcv_parent_home_img)
        )
        super.onViewCreated(view, savedInstanceState)
    }

}