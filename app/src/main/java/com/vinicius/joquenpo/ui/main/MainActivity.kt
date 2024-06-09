package com.vinicius.joquenpo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vinicius.joquenpo.R
import com.vinicius.joquenpo.databinding.ActivityMainBinding
import com.vinicius.joquenpo.ui.joquenpo.JoquenpoFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnNext.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fcv_joquenpo, JoquenpoFragment())
                .commit()
        }
    }

}