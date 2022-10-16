package com.example.physicsquiz

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(val question: String, val answer: Boolean): Parcelable
