package com.example.glory_3tic.Catatan

import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.glory_3tic.Data.Api.RetrofitClient
import com.example.glory_3tic.R
import com.example.glory_3tic.databinding.ActivityCatatanFormBinding
import com.example.glory_3tic.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class CatatanFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCatatanFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCatatanFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setContentView(R.layout.activity_catatan_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val isEdit = intent.hasExtra("id")
        val noteId = intent.getLongExtra("id", 0)

        binding.btnSaveNote.text = if (isEdit) "Update" else "Simpan"
        if (isEdit) {
            binding.etTitle.setText(intent.getStringExtra("title"))
            binding.etContent.setText(intent.getStringExtra("content"))
        }
        binding.btnSaveNote.setOnClickListener {

            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()
            if (title.isBlank() || content.isBlank()) {

                Toast.makeText(this, "Isi judul dan catatan", Toast.LENGTH_SHORT).show()

                return@setOnClickListener

            }

            val data = mapOf("title" to title, "content" to content)

            binding.btnSaveNote.isEnabled = false
            lifecycleScope.launch {
                try {
                    val api = RetrofitClient.service
                    val response = if (isEdit) {

                        api.editNote(RetrofitClient.API_KEY, "eq.$noteId", data)
                    } else {
                        api.addNote(RetrofitClient.API_KEY, data)
                    }

                    if (response.isSuccessful) {

                        finish()
                    } else {
                        Toast.makeText(this@CatatanFormActivity,

                            "Gagal: ${response.code()}", Toast.LENGTH_SHORT).show()

                    }
                } catch (e: Exception) {
                    Toast.makeText(this@CatatanFormActivity,
                        "Koneksi gagal", Toast.LENGTH_SHORT).show()

                }
                binding.btnSaveNote.isEnabled = true
            }
        }
    }
}