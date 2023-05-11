package org.bmsk.cafewithmotionlayout

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.bmsk.cafewithmotionlayout.data.model.Home
import org.bmsk.cafewithmotionlayout.data.model.Menu
import org.bmsk.cafewithmotionlayout.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val homeData = context?.readData("home.json", Home::class.java) ?: return
        val menuData = context?.readData("menu.json", Menu::class.java) ?: return

        initAppBar(homeData)
        initRecommendMenuList(homeData, menuData)
        initBanner(homeData)
        initFoodList(menuData)

        binding.scrollView.setOnScrollChangeListener { v, _, scrollY, _, oldScrollY ->
            if (scrollY == 0) {
                binding.floatingActionButton.extend()
            } else {
                binding.floatingActionButton.shrink()
            }
        }
    }

    private fun initFoodList(menuData: Menu) {
        binding.foodMenuList.titleTextView.text =
            getString(R.string.food_menu_title)
        menuData.food.forEach { menuItem ->
            binding.foodMenuList.menuLinearLayout.addView(
                MenuView(context = requireContext()).apply {
                    setTitle(menuItem.name)
                    setImageUrl(menuItem.image)
                }
            )
        }
    }

    private fun initBanner(homeData: Home) {
        binding.bannerLayout.bannerImageView.apply {
            Glide.with(this)
                .load(homeData.banner.image)
                .into(this)
            this.contentDescription = homeData.banner.contentDescription
        }
    }

    private fun initRecommendMenuList(
        homeData: Home,
        menuData: Menu
    ) {
        binding.recommendMenuList.titleTextView.text =
            getString(R.string.recommend_title, homeData.user.nickname)
        menuData.coffee.forEach { menuItem ->
            binding.recommendMenuList.menuLinearLayout.addView(
                MenuView(context = requireContext()).apply {
                    setTitle(menuItem.name)
                    setImageUrl(menuItem.image)
                }
            )
        }
    }

    private fun initAppBar(homeData: Home) {
        binding.appBarTitleTextView.text =
            getString(R.string.appbar_title_text, homeData.user.nickname)

        binding.startCountTextView.text = getString(
            R.string.appbar_start_title, homeData.user.starCount, homeData.user.totalCount
        )

        binding.appbarProgressBar.max = homeData.user.totalCount

        Glide.with(binding.appBarImageView)
            .load(homeData.appbarImage)
            .into(binding.appBarImageView)

        // 값 자체가 start에서 end로 바뀔 때 중간 중간 값을 리턴해주는 방식으로 애니메이션을 사용
        // 0부터 1까지 주었다면, 0.3,, 0.7,, 등등 중간에 값이 리턴.
        // startCount는 정수이므로 ofInt를 사용함
        ValueAnimator.ofInt(0, homeData.user.starCount).apply {
            duration = 1000
            addUpdateListener {
                binding.appbarProgressBar.progress = it.animatedValue as Int
            }
            start()
        }
    }
}