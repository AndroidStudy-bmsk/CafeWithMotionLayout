package org.bmsk.cafewithmotionlayout

import android.os.Bundle
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

        binding.appBarTitleTextView.text =
            getString(R.string.appbar_title_text, homeData.user.nickname)

        binding.startCountTextView.text = getString(
            R.string.appbar_start_title, homeData.user.startCount, homeData.user.totalCount
        )

        binding.appbarProgressBar.progress = homeData.user.startCount
        binding.appbarProgressBar.max = homeData.user.totalCount

        Glide.with(binding.appBarImageView)
            .load(homeData.appbarImage)
            .into(binding.appBarImageView)

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

        binding.bannerLayout.bannerImageView.apply {
            Glide.with(this)
                .load(homeData.banner.image)
                .into(this)
            this.contentDescription = homeData.banner.contentDescription
        }

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
}