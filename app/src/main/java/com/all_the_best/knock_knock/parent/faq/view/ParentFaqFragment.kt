package com.all_the_best.knock_knock.parent.faq.view


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.util.FragmentOnBackPressed
import com.all_the_best.knock_knock.parent.faq.adapter.ParentFaqAdapter
import com.all_the_best.knock_knock.parent.faq.adapter.ParentFaqData
import com.all_the_best.knock_knock.parent.faq.adapter.ParentFaqItemDeco
import kotlinx.android.synthetic.main.fragment_parent_faq.*


class ParentFaqFragment : Fragment(), FragmentOnBackPressed {
    private lateinit var parentFaqAdapter: ParentFaqAdapter
    private lateinit var rcvLayoutManager: GridLayoutManager

    override fun onBackPressed(): Boolean {
        if (faq_drawer_layout.isDrawerOpen(GravityCompat.START)) {
            Log.d("프래그먼트","FAQ if")
            faq_drawer_layout.closeDrawers()
            return true
        } else {
            return false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parent_faq, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        parentFaqAdapter = ParentFaqAdapter(view.context)
//        parentFaqAdapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver(){
//            override fun onChanged() {
//                super.onChanged()
//            }
//        })

        val spaceDecoration = ParentFaqItemDeco(10, 16) //리사이클러뷰 아이템간 간격 설정
        rcvLayoutManager = GridLayoutManager(view.context, 2)
        //rcvLayoutManager.
        parent_faq_rcv.apply{
            adapter = parentFaqAdapter
            layoutManager = GridLayoutManager(view.context, 2)
        }

        var faqList = mutableListOf(
                ParentFaqData(0,"자존감이\n" + "낮은 아이",false),
                ParentFaqData(1,"아이를\n" + "바르게\n" + "대하는 방법",false),
                ParentFaqData(2,"자존감이\n" + "낮은 아이",false),
                ParentFaqData(3,"자존감이\n" + "낮은 아이",false),
                ParentFaqData(4,"아이가\n" + "싫어하는 행동을\n" + "표현한 경우",false),
                ParentFaqData(5,"아이가\n" + "싫어하는 행동을\n" + "표현한 경우",false),
                ParentFaqData(6,"참는 성향이\n" + "있는 아이",false),
                ParentFaqData(7,"참는 성향이\n" + "있는 아이",false),
                ParentFaqData(8,"아이를\n" + "바르게\n" + "대하는 방법",false)
        )

        parentFaqAdapter.data = faqList

        parentFaqAdapter.notifyDataSetChanged()

        //햄버거바 클릭시 DrawerLayout 열림
        faq_btn_hamburger.setOnClickListener {
            faq_drawer_layout.openDrawer(GravityCompat.START) //네비게이션 드로어 열기
        }

        super.onViewCreated(view, savedInstanceState)
    }

}