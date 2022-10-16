package com.example.physicsquiz

class PhysicsQuestions {
  private val questions: List<Question> = listOf(
    Question(
      "Czy działanie lasera polega na emisji promieniowania elektromagnetycznego?",
      true
    ),
    Question(
      "Czy temperatura wrzenia wody wynosi zawsze 100 stopni Celsjusza?",
      false
    ),
    Question("Czy masa obiektu pozostaje taka sama w każdy warunkach?", true),
    Question("Czy niuton [N] jest jednostką miary siły w układzie SI?", true),
    Question("Czy resublimacja to przejście ze stanu gazowego w stały?", true),
    Question("Czy stojący rower posiada energię kinetyczną?", false),
    Question("Czy ładunki jednoimienne odpychają się?", true),
    Question("Czy rezystancja to inaczej opór elektryczny?", true),
    Question("Czy proton posiada ładunek ujemny?", false),
    Question("Czy drewno jest lepszym przewodnikiem ciepła niż metal?", false)
  )

  fun getAll(): List<Question> = questions
}