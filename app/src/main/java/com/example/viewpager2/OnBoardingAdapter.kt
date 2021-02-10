package com.example.viewpager2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpager2.databinding.ItemOnboardingBinding
import com.example.viewpager2.model.OnBoardingItem

class OnBoardingAdapter(private val context: Context,private val list:List<OnBoardingItem>):RecyclerView.Adapter<OnBoardingAdapter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnBoardingAdapter.ViewHolder {
        val binding = DataBindingUtil.inflate<ItemOnboardingBinding>(LayoutInflater.from(context),R.layout.item_onboarding,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingAdapter.ViewHolder, position: Int) {
        holder.bindItems(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(private val binding: ItemOnboardingBinding):RecyclerView.ViewHolder(binding.root){
        fun bindItems(obj:OnBoardingItem){
            binding.imageOnBoarding.setImageResource(obj.image)
            binding.textTitle.text = obj.title
            binding.textDescription.text = obj.description
        }
    }
}