package com.all_the_best.knock_knock.infant.setting.view

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.databinding.ActivityInfantSelectCharacterBinding
import com.all_the_best.knock_knock.databinding.FragmentParentTalkBinding
import com.all_the_best.knock_knock.infant.cookie.viewmodel.InfantCookieViewModel
import com.all_the_best.knock_knock.infant.gift.viewmodel.InfantGiftBgViewModel
import com.all_the_best.knock_knock.infant.home.view.InfantHomeActivity
import com.all_the_best.knock_knock.infant.setting.adapter.InfantViewPagerAdapter
import com.all_the_best.knock_knock.infant.setting.viewmodel.InfantSelectChViewModel
import com.all_the_best.knock_knock.infant.talk.viewmodel.InfantTalkLottieViewModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_infant_deco.*
import kotlinx.android.synthetic.main.activity_infant_select_character.*


class InfantSelectCharacterActivity : AppCompatActivity() {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
   // private lateinit var binding: ActivityInfantSelectCharacterBinding

    // 데이터베이스의 인스턴스를 가져온다고 생각(즉, Root를 가져온다고 이해하면 쉬움)
    private val databaseReference: DatabaseReference = database.reference
    private lateinit var selectViewPagerAdapter: InfantViewPagerAdapter
    private val infantSelectChViewModel: InfantSelectChViewModel by viewModels()
    private val infantCookieViewModel: InfantCookieViewModel by viewModels()
    private val infantGiftBgViewModel: InfantGiftBgViewModel by viewModels()
    private val infantTalkLottieViewModel: InfantTalkLottieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_infant_select_character)

        //상태바 색상 지정
        window.statusBarColor = Color.parseColor("#74DAFF")

        selectViewPagerAdapter =
            InfantViewPagerAdapter(
                supportFragmentManager
            )

        infant_viewpager_select.adapter = selectViewPagerAdapter

        //getCookieDataFirebase()

        // 아이 계정 선택 화면
        val intent2 = Intent(this, InfantSelectIdActivity::class.java)
        select_btn_back.setOnClickListener {
            infantTalkLottieViewModel.setlottieSelect(0)
            intent2.putExtra("chSelect", infantSelectChViewModel.chSelect.value)
            intent2.putExtra("cookieCount", infantCookieViewModel.cookieCount.value)
            intent2.putExtra("giftSelect", infantGiftBgViewModel.giftSelect.value)
            intent2.putExtra("lottieSelect", infantTalkLottieViewModel.lottieSelect.value)
            startActivity(intent2)
            overridePendingTransition(0, 0)
        }

        infant_viewpager_select.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                if(position == 0){  // if you want the second page, for example
                    //Your code here
                    infantSelectChViewModel.setchSelect(0)
                }else if(position == 1){
                    infantSelectChViewModel.setchSelect(1)
                }else if(position == 2){
                    infantSelectChViewModel.setchSelect(2)
                }
            }
        })

        // 홈화면
        val intent1 = Intent(this, InfantHomeActivity::class.java)
        select_btn_ok.setOnClickListener{
            //infantTalkLottieViewModel.setlottieSelect(0)
            intent1.putExtra("chSelect", infantSelectChViewModel.chSelect.value)
            intent1.putExtra("giftSelect", infantGiftBgViewModel.giftSelect.value)
            intent1.putExtra("lottieSelect", infantTalkLottieViewModel.lottieSelect.value)
            startActivity(intent1)
            overridePendingTransition(0, 0)
        }
    }


//    private fun getCookieDataFirebase(){
//        //lateinit var getcount: String
//        val childId = "아이1"
//        val getCountCookie: DatabaseReference =
//            databaseReference.child(childId).child(childId + "의 쿠키개수 " )
//        Toast.makeText(this, "push", Toast.LENGTH_SHORT).show()
//        getCountCookie.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                binding.txtCookie = snapshot.value as String
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
//    }

}