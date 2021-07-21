package com.zhl.rxjavaarchitecture.activity

import android.content.Context
import android.graphics.Color
import android.view.View
import com.zhl.baselibrary.activity.BaseActivity
import com.zhl.baselibrary.adapter.BaseFragmentStatePagerAdapter
import com.zhl.baselibrary.fragment.BaseFragment
import com.zhl.rxjavaarchitecture.databinding.ActivityMagicIndicatorSampleBinding
import com.zhl.rxjavaarchitecture.fragment.ViewPager2SampleFragment
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView


/**
 *    author : zhuhl
 *    date   : 2021/7/21
 *    desc   :
 *    refer  :
 */
class MagicIndicatorSampleActivity : BaseActivity<ActivityMagicIndicatorSampleBinding>() {

    private val titleList = arrayOf("热门", "精华0")

    private val commonNavigatorAdapter = object : CommonNavigatorAdapter() {
        override fun getCount(): Int {
            return titleList.size
        }

        override fun getIndicator(context: Context?): IPagerIndicator {
            val indicator = LinePagerIndicator(context)
            indicator.mode = LinePagerIndicator.MODE_WRAP_CONTENT
            return indicator
        }

        override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
            val colorTransitionPagerTitleView = ColorTransitionPagerTitleView(context)
            colorTransitionPagerTitleView.normalColor = Color.GRAY
            colorTransitionPagerTitleView.selectedColor = Color.BLACK
            colorTransitionPagerTitleView.setText(titleList.get(index))
            colorTransitionPagerTitleView.setOnClickListener {
                binding.viewPager.setCurrentItem(index)
            }
            return colorTransitionPagerTitleView
        }
    }

    override fun initData() {
        val commonNavigator = CommonNavigator(this)
        commonNavigator.adapter = commonNavigatorAdapter
        binding.magicIndicator.navigator = commonNavigator;

        val fragmentList: MutableList<BaseFragment<*>> =
            mutableListOf(ViewPager2SampleFragment.newInstance(1),
                ViewPager2SampleFragment.newInstance(2)
                )
        binding.viewPager.adapter = BaseFragmentStatePagerAdapter(
            supportFragmentManager,
            fragmentList
        )
        ViewPagerHelper.bind(binding.magicIndicator, binding.viewPager)
    }

    override fun loadData() {
    }

    override fun initListener() {
    }

    override fun getLayoutViewBinding(): ActivityMagicIndicatorSampleBinding {
        return ActivityMagicIndicatorSampleBinding.inflate(layoutInflater)
    }


}