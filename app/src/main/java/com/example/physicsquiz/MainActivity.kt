package com.example.physicsquiz

import android.content.Intent
import android.content.Intent.CATEGORY_BROWSABLE
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView

const val ANSWER_KEY = "com.example.physicsquiz.ANSWER"

class MainActivity : AppCompatActivity() {
  private val questions = PhysicsQuestions()
  private val textView: TextView by lazy { findViewById(R.id.textView) }
  private var state: PhysicsQuizState = PhysicsQuizState(
    questions = questions.getAll(),
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (savedInstanceState != null) {
      val quizState: PhysicsQuizState? =
        savedInstanceState.getParcelable("quizState")
      if (quizState != null) {
        state = quizState
      }
    }

    updateView()
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putParcelable("quizState", state)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.options_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      R.id.search_button -> {
        navigateToGoogle()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }

  fun onClickYesButton(view: View?) {
    checkAnswer(true)
  }

  fun onClickNoButton(view: View?) {
    checkAnswer(false)
  }

  fun onClickCheatButton(view: View?) {
    if (!state.isGameFinished) {
      navigateToCheatActivity()
      state.onAnswerChecked()
    }
  }

  private fun checkAnswer(answer: Boolean) {
    state.onQuestionAnswered(answer)
    updateView()
  }

  private fun navigateToCheatActivity() {
    val answer: Boolean = state.getAnswerForCurrentQuestion()
    val intent = Intent(this, CheatActivity::class.java).putExtra(
      ANSWER_KEY,
      convertBooleanToUI(answer),
    )
    startActivity(intent)
  }

  private fun navigateToGoogle() {
    val url = "http://google.com/"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).addCategory(
      CATEGORY_BROWSABLE
    )
    if (intent.resolveActivity(packageManager) != null) {
      startActivity(intent)
    }
  }

  private fun updateView() {
    if (state.isGameFinished) {
      displayResult()
    } else {
      updateQuestion()
    }
  }

  private fun convertBooleanToUI(value: Boolean): String {
    return if (value) {
      "TAK"
    } else {
      "NIE"
    }
  }

  private fun updateQuestion() {
    val questionNumber: Int = state.getQuestionIndex() + 1
    val questionText = "$questionNumber. ${state.getCurrentQuestion()}"
    textView.text = questionText
  }

  private fun displayResult() {
    var resultText = "Liczba punktów: ${state.getPoints()}"
    resultText += "\nPoprawne odpowiedzi: ${state.getAmountOfCorrectAnswers()}/10"
    resultText += "\nLiczba oszustw: ${state.getAmountOfFrauds()}"
    textView.text = resultText
  }
}