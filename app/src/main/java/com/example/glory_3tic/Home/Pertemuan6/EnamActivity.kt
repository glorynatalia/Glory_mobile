package com.example.glory_3tic.Home.Pertemuan6

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.glory_3tic.R
import com.example.glory_3tic.databinding.ActivityEnamBinding

class EnamActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityEnamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            // ini untuk sub title dibawah  judul
            title = "Halaman Pertemuan Enam"
            subtitle = "Ini adalah subtitle"

            // ini untuk menampilkan panah back
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
//            setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        binding.btnfragsatu.setOnClickListener {
            replaceFragment(SatuFragment())
        }
        binding.btnfragdua.setOnClickListener {
            replaceFragment(DuaFragment())
        }
        binding.btnfragtiga.setOnClickListener {
            replaceFragment(TigaFragment())
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.containerFragment.id, fragment)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}