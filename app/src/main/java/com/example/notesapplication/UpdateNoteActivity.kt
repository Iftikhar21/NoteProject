package com.example.notesapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notesapplication.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)



        noteId = intent.getIntExtra("note_id", -1)
        if (noteId == -1) {
            finish()
            return
        }

        val note = db.getNoteById(noteId)
        binding.updateTitleEditTxt.setText(note.title)
        binding.updateContentEditTxt.setText(note.content)

        binding.updateSaveButton.setOnClickListener{
            val newTitle = binding.updateTitleEditTxt.text.toString()
            val newContent = binding.updateContentEditTxt.text.toString()
            val updateNote = Note(noteId, newTitle, newContent)
            db.updateNote(updateNote)
            Toast.makeText(this, "Update Berhasil", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}