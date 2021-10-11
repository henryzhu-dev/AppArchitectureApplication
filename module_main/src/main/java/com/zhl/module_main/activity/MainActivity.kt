package com.zhl.module_main.activity

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.zhl.lib_common.constant.ARouterConstant
import com.zhl.lib_core.activity.BaseActivity
import com.zhl.lib_core.adapter.BaseFragmentStatePagerAdapter
import com.zhl.lib_core.fragment.BaseFragment
import com.zhl.module_main.R
import com.zhl.module_main.databinding.ActivityMainBinding
import com.zhl.module_main.fragment.DashboardFragment
import com.zhl.module_main.fragment.HomeFragment
import com.zhl.module_main.fragment.MeFragment
import com.zhl.module_main.fragment.NotificationsFragment
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.CommonPagerTitleView


@Route(path = ARouterConstant.MAIN.INDEX)
class MainActivity : BaseActivity<ActivityMainBinding>() {

    companion object {

        val iconList = mutableListOf<Int>(
            R.mipmap.ic_main_home_normal,
            R.mipmap.ic_main_dashboard_normal,
            R.mipmap.ic_main_notification_normal,
            R.mipmap.ic_main_me_normal
        )

        val iconSelectedList = mutableListOf<Int>(
            R.mipmap.ic_main_home_selected,
            R.mipmap.ic_main_dashboard_selected,
            R.mipmap.ic_main_notification_selected,
            R.mipmap.ic_main_me_selected
        )

        val titleList = mutableListOf<String>("Home", "Dashboard", "Notifications", "Me")

    }

    private lateinit var commonNavigator: CommonNavigator

    private val pagerAdapter by lazy {
        val fragmentList = mutableListOf<BaseFragment<*>>(
            HomeFragment(),
            DashboardFragment(),
            NotificationsFragment(),
            MeFragment()
        )
        BaseFragmentStatePagerAdapter(supportFragmentManager, fragmentList)
    }

    override fun initData() {
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.offscreenPageLimit = 3
        initMagicIndicator()
    }

    private fun initMagicIndicator() {
        binding.magicIndicator.setBackgroundColor(Color.WHITE)
        commonNavigator = CommonNavigator(this)
        commonNavigator.isAdjustMode = true
        commonNavigator.adapter = object : CommonNavigatorAdapter() {
            override fun getCount(): Int {
                return titleList.size
            }

            override fun getTitleView(context: Context?, index: Int): IPagerTitleView {
                val commonPagerTitleView = CommonPagerTitleView(this@MainActivity)
                val customLayout = LayoutInflater.from(this@MainActivity)
                    .inflate(R.layout.layout_main_btm_indicator_item, null)
                val ivIcon = customLayout.findViewById<ImageView>(R.id.ivIcon)
                val tvName = customLayout.findViewById<TextView>(R.id.tvName)
                val tvBadge = customLayout.findViewById<TextView>(R.id.tvBadge)
                tvName.text = titleList[index]
                ivIcon.setImageResource(iconList[index])
                commonPagerTitleView.setContentView(customLayout)

                commonPagerTitleView.onPagerTitleChangeListener =
                    object : CommonPagerTitleView.OnPagerTitleChangeListener {
                        override fun onSelected(index: Int, totalCount: Int) {
                            ivIcon.setImageResource(iconSelectedList[index])
                            tvName.setTextColor(Color.parseColor("#1296db"))
                        }

                        override fun onDeselected(index: Int, totalCount: Int) {
                            ivIcon.setImageResource(iconList[index])
                            tvName.setTextColor(
                                ContextCompat.getColor(
                                    this@MainActivity,
                                    R.color.gray70
                                )
                            )
                        }

                        override fun onLeave(
                            index: Int,
                            totalCount: Int,
                            leavePercent: Float,
                            leftToRight: Boolean
                        ) {

                        }

                        override fun onEnter(
                            index: Int,
                            totalCount: Int,
                            enterPercent: Float,
                            leftToRight: Boolean
                        ) {

                        }

                    }

                commonPagerTitleView.setOnClickListener {
                    binding.viewPager.setCurrentItem(index, false)
                }
                return commonPagerTitleView
            }

            override fun getIndicator(context: Context?): IPagerIndicator? {
                return null
            }

        }

        binding.magicIndicator.navigator = commonNavigator
        ViewPagerHelper.bind(binding.magicIndicator, binding.viewPager)
    }

    override fun loadData() {

    }

    override fun initListener() {

    }

    override fun getLayoutViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}