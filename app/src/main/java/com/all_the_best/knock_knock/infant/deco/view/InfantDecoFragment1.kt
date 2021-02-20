package com.all_the_best.knock_knock.infant.deco.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant.deco.adapter.InfantDecoRcvAdapter
import com.all_the_best.knock_knock.infant.deco.adapter.InfantDecoItemChar
import com.all_the_best.knock_knock.parent.faq.adapter.ParentFaqItemDeco
import kotlinx.android.synthetic.main.activity_infant_deco_fragment1.*
import kotlinx.android.synthetic.main.fragment_parent_faq.*

class InfantDecoFragment1 : Fragment() {
    /*
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager*/
    private var recyclerView:RecyclerView?=null
    private var gridLayoutManager:GridLayoutManager?=null
    private var arrayList:ArrayList<InfantDecoItemChar>?=null
    private var decoAdapters: InfantDecoRcvAdapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_infant_deco_fragment1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = rcv_deco_frg1
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        arrayList = ArrayList()
        arrayList = setDateInList()
        decoAdapters = InfantDecoRcvAdapter(view.context ,arrayList!!)
        recyclerView?.adapter = decoAdapters


        val spaceDecoration = ParentFaqItemDeco(1) //리사이클러뷰 아이템간 간격 설정
        gridLayoutManager = GridLayoutManager(view.context, 3)
        //rcvLayoutManager.
        parent_faq_rcv.apply{
            adapter = decoAdapters
            layoutManager = GridLayoutManager(view.context, 2)
            addItemDecoration(spaceDecoration) //간격 설정한거 데코로 설정
        }


        super.onViewCreated(view, savedInstanceState)
    }
    private fun setDateInList():ArrayList<InfantDecoItemChar>{
        var items: ArrayList<InfantDecoItemChar> = ArrayList()
        items.add(InfantDecoItemChar(R.drawable.img_infant_itembox_false_rcv))
        items.add(InfantDecoItemChar(R.drawable.img_infant_itembox_false_rcv))
        items.add(InfantDecoItemChar(R.drawable.img_infant_itembox_false_rcv))
        items.add(InfantDecoItemChar(R.drawable.img_infant_itembox_false_rcv))
        items.add(InfantDecoItemChar(R.drawable.img_infant_itembox_false_rcv))
        items.add(InfantDecoItemChar(R.drawable.img_infant_itembox_false_rcv))
        return items
    }
}
        /*리사이클러뷰
        viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        viewAdapter = InfantDecoRcvAdapter()


        recyclerView = rcv_deco_frg1.apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter
        }*/
