package org.bmsk.cafewithmotionlayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import org.bmsk.cafewithmotionlayout.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        val homeData = context?.readData() ?: return
        binding.appBarTitleTextView.text =
            getString(R.string.appbar_title_text, homeData.user.nickname)

        binding.startCountTextView.text = getString(
            R.string.appbar_start_title, homeData.user.startCount, homeData.user.totalCount
        )

        Glide.with(binding.appBarImageView)
            .load(homeData.appbarImage)
            .into(binding.appBarImageView)

        binding.appbarProgressBar.progress = homeData.user.startCount
        binding.appbarProgressBar.max = homeData.user.totalCount
        binding.recommendMenuList.menuLinearLayout.addView(
            MenuView(context = requireContext()).apply {
                setTitle("디카페인 카페라떼")
                setImageUrl("https://picsum.photos/200/300")
            }
        )
    }
}