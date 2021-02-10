package com.example.viewpager2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2.databinding.ActivityMainBinding
import com.example.viewpager2.model.OnBoardingItem
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var onBoardingAdapter: OnBoardingAdapter
    private lateinit var list: ArrayList<OnBoardingItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpOnBoardingItems()
        binding.onBoardingViewPager.adapter = onBoardingAdapter
        TabLayoutMediator(binding.tabIndicator, binding.onBoardingViewPager) { tab, position ->
            //Some implementation
        }.attach()

        binding.onBoardingViewPager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if ((position+1) < list.size){
                    binding.buttonOnBoardingAction.text = "Next"
                }else{
                    binding.buttonOnBoardingAction.text = "Start"
                }
            }
        })

        binding.buttonOnBoardingAction.setOnClickListener {
            if (binding.buttonOnBoardingAction.text == "Next"){
                binding.onBoardingViewPager.currentItem = binding.onBoardingViewPager.currentItem + 1
            }else{
                Toast.makeText(this,"Finish",Toast.LENGTH_LONG).show()
            }
        }

        /**
         * The below commented out code is for view pager with image slider:
         * To implement view pager with image slider just uncomment the below code
         * and add padding start and padding end of 80dp to view pager in xml
         */
        /*
        binding.onBoardingViewPager.clipToPadding = false
        binding.onBoardingViewPager.clipChildren = false
        binding.onBoardingViewPager.offscreenPageLimit = 3
        binding.onBoardingViewPager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(object : ViewPager2.PageTransformer{
            override fun transformPage(page: View, position: Float) {
                val r = 1 - Math.abs(position)
                //for right and left pages smaller than main page
                page.scaleY = 0.85f.plus(r.times(0.15f))

                //for right and left pages of same size
                //page.scaleY = 1f
            }

        })

        binding.onBoardingViewPager.setPageTransformer(compositePageTransformer)
         */

    }

    private fun setUpOnBoardingItems(){
        list = ArrayList<OnBoardingItem>()
        val firstItem = OnBoardingItem(
            R.drawable.bay,
            "Beaches are paradise",
            "A beautiful destination for holiday"
        )
        val secondItem = OnBoardingItem(
            R.drawable.beachbar,
            "Beaches gives solace",
            "The evenings near sea side is mesmerising"
        )
        val thirdItem = OnBoardingItem(
            R.drawable.table,
            "Beaches are filled with love",
            "A perfect holiday destination for couples"
        )
        with(list){
            add(firstItem)
            add(secondItem)
            add(thirdItem)
        }

        onBoardingAdapter = OnBoardingAdapter(this, list)
    }
}