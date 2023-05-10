package org.bmsk.cafewithmotionlayout

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.bmsk.cafewithmotionlayout.databinding.FragmentOrderBinding

class OrderFragment: Fragment(R.layout.fragment_order) {
    lateinit var binding: FragmentOrderBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOrderBinding.bind(view)
    }
}