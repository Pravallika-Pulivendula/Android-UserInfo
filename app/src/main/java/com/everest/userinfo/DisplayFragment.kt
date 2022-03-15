package com.everest.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.everest.userinfo.databinding.FragmentDisplayBinding

class DisplayFragment : Fragment() {
    private var _binding: FragmentDisplayBinding? = null
    private val binding get() = _binding!!
    private lateinit var editViewModel: EditViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        editViewModel = activity?.let { ViewModelProvider(it)[EditViewModel::class.java] }
            ?: throw RuntimeException("Not a Activity")
        _binding = FragmentDisplayBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailsTV.text = getString(
            R.string.resultText,
            editViewModel.username.value,
            editViewModel.email.value,
            editViewModel.phoneNumber.value,
            editViewModel.pincode.value,
            editViewModel.address.value
        )
    }
}