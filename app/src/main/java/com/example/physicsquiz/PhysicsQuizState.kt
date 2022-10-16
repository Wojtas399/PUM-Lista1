package com.example.physicsquiz

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class PhysicsQuizState(
  private val questions: List<Question>,
  private var questionIndex: Int = 0,
  private var points: Int = 0,
  private var amountOfCorrectAnswers: Int = 0,
  private var amountOfFrauds: Int = 0,
  private var hasAnswerBeenChecked: Boolean = false
) : Parcelable {

  @IgnoredOnParcel
  var isGameFinished: Boolean = questionIndex >= questions.size

  fun getQuestionIndex(): Int = questionIndex

  fun getPoints(): Int = if (points < 0) 0 else points

  fun getAmountOfCorrectAnswers(): Int = amountOfCorrectAnswers

  fun getAmountOfFrauds(): Int = amountOfFrauds

  fun getCurrentQuestion(): String = questions[questionIndex].question

  fun getAnswerForCurrentQuestion(): Boolean = questions[questionIndex].answer

  fun onAnswerChecked() {
    subtractPointsForCheckingAnswer()
    hasAnswerBeenChecked = true
  }

  fun onQuestionAnswered(answer: Boolean) {
    if (!isGameFinished) {
      if (!hasAnswerBeenChecked && isAnswerCorrect(answer)) {
        addPointsForCorrectAnswer()
      }
      if (doNextQuestionsExist()) {
        questionIndex++
      } else {
        isGameFinished = true
      }
    }
    hasAnswerBeenChecked = false
  }

  private fun subtractPointsForCheckingAnswer() {
    amountOfFrauds++
    points -= 15
  }

  private fun addPointsForCorrectAnswer() {
    amountOfCorrectAnswers++
    points += 10
  }

  private fun isAnswerCorrect(answer: Boolean): Boolean {
    return answer == getAnswerForCurrentQuestion()
  }

  private fun doNextQuestionsExist(): Boolean {
    return questionIndex + 1 < questions.size
  }
}