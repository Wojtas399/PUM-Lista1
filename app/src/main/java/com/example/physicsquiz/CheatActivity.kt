package com.example.physicsquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class CheatActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_cheat)

    createBackButton()
    displayAnswer()
  }

  override fun onSupportNavigateUp(): Boolean {
    onBackPressed()
    return true
  }

  private fun createBackButton() {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  private fun displayAnswer() {
    val answer: String? = intent.getStringExtra(ANSWER_KEY)
    if (answer != null) {
      val text = "Odpowiedź to: $answer"
      findViewById<TextView>(R.id.answerTextView).text = text
    }
  }
}