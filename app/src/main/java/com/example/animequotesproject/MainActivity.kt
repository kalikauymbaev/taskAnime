package com.example.animequotesproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.animequotesproject.databinding.ActivityMainBinding
import com.example.animequotesproject.presentation.fragment.MainPageFragment
import com.example.animequotesproject.presentation.fragment.QuotePageFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MainPageFragment()).commit()
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            var temp: Fragment? = null
            when (item.itemId) {
                R.id.action_dashboard -> temp = MainPageFragment()
                R.id.action_quote -> temp = QuotePageFragment()
            }
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, temp!!).commit()
            true
        }
    }
}