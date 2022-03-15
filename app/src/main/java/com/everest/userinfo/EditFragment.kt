package com.everest.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.everest.userinfo.databinding.FragmentEditBinding

class EditFragment : Fragment() {

    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var validator: Validator
    private lateinit var editViewModel: EditViewModel
    private lateinit var displayFragment: DisplayFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (_binding == null) {
            _binding = FragmentEditBinding.inflate(inflater, container, false)
        }
        validator = Validator()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editViewModel = activity?.let { ViewModelProvider(it)[EditViewModel::class.java] }
            ?: throw RuntimeException("Not a Activity")
        displayFragment = DisplayFragment()

        editViewModel.isValid.observe(viewLifecycleOwner) {
            editViewModel.visibility.value?.let { it1 -> modifyViews(it1) }
        }

        binding.validateButton.setOnClickListener {
            validateData()
        }

        binding.cancelButton.setOnClickListener {
            editViewModel.setVisibility(false)
            editViewModel.visibility.value?.let { it1 -> modifyViews(it1) }
        }

        binding.confirmButton.setOnClickListener {
            editViewModel.setUserData(
                binding.usernameET.text.toString(),
                binding.emailET.text.toString(),
                binding.phoneNumberET.text.toString(),
                binding.pincodeET.text.toString(),
                binding.addressET.text.toString()
            )

            replaceFragment(displayFragment)
        }
    }

    private fun validateData() {
        if (validator.areFieldsEmpty(
                binding.layout,
                requireActivity()
            ) && validator.isEmailValid(
                binding.emailET.text.toString(), requireActivity()
            ) && validator.isPhoneNumberValid(
                binding.phoneNumberET.text.toString(), requireActivity()
            ) && validator.isPinCodeValid(binding.pincodeET.text.toString(), requireActivity())
        ) {
            editViewModel.isDataValid(true)
            editViewModel.setVisibility(true)
            editViewModel.visibility.value?.let { modifyViews(it) }
        }
    }

    private fun modifyViews(visibility: Boolean) {
        editViewModel.visibility.value?.let { changeVisibility(it) }
        customizeEditText(!visibility)
    }

    private fun replaceFragment(displayFragment: DisplayFragment) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.fragment_one, displayFragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun customizeEditText(visibility: Boolean) {
        for (i in 0 until binding.layout.childCount) {
            val v = binding.layout.getChildAt(i)
            if (v is EditText) {
                v.isEnabled = visibility
            }
        }
    }

    private fun changeVisibility(visibility: Boolean) {
        binding.buttonsVisibility.isVisible = visibility
        binding.validateButton.isVisible = !visibility
    }

}