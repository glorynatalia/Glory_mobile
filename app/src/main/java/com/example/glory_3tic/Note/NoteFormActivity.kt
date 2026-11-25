package com.example.glory_3tic.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.glory_3tic.Data.AppDatabase
import com.example.glory_3tic.Data.Entity.NoteEntity
import com.example.glory_3tic.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getInstance(this)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            val content = binding.etContent.text.toString().trim()

            if (title.isEmpty() || content.isEmpty()) {
                Toast.makeText(this, "Judul dan isi catatan harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val note = NoteEntity(title = title, content = content)
                db.noteDao().insert(note)
                Toast.makeText(this@NoteFormActivity, "Catatan tersimpan", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}