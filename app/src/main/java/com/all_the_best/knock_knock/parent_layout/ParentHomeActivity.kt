package com.all_the_best.knock_knock.parent_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.all_the_best.knock_knock.R
import kotlinx.android.synthetic.main.activity_parent_home.*
import kotlin.properties.Delegates

class ParentHomeActivity : AppCompatActivity() {
    private lateinit var viewPagerAdapter: ParentViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent_home)


        viewPagerAdapter = ParentViewPagerAdapter(supportFragmentManager)

        parent_viewpager.adapter = viewPagerAdapter

        parent_viewpager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}

            override fun onPageSelected(position: Int) {
                parent_bottom_navi.menu.getItem(position).isChecked = true
            }
        })

        parent_bottom_navi.setOnNavigationItemSelectedListener {
            var index by Delegates.notNull<Int>()
            when(it.itemId){
                R.id.menu_home -> index = 0
                R.id.menu_talk -> index = 1
                R.id.menu_faq -> index = 2
            }
            parent_viewpager.currentItem = index
            true
        }

    }
}