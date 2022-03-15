package com.everest.userinfo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.everest.userinfo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
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