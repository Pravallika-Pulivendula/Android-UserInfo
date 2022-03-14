package com.everest.userinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.everest.userinfo.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

        val editFragment = EditFragment()

        if (savedInstanceState == null) {
            loadFragment(editFragment)
        }

    }

    private fun loadFragment(editFragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_one, editFragment)
            commit()
        }
    }

}