package com.example.glory_3tic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.glory_3tic.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        val btnlogin : Button = findViewById(R.id.btnlogin)
//        val username : EditText = findViewById(R.id.edtusername)
//        val password : EditText = findViewById(R.id.edtpassword)

        binding.btnlogin.setOnClickListener {
            val user = binding.edtusername.text.toString()
            val pass = binding.edtpassword.text.toString()

            //intent untuk memindahkan kehalaman yang lain
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.putExtra("nama", "Glory Natalia Sitompul")
            intent.putExtra("umur", 20)
            startActivity(intent)

            Log.d("username:", user)
            Log.d( "Password :", pass)

            Toast.makeText(this, "Username: $user Password: $pass", Toast.LENGTH_LONG).show()
        }

    }
}