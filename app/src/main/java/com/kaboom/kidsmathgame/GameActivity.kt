package com.kaboom.kidsmathgame

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Random

class GameActivity : AppCompatActivity() {
    lateinit var actionTitle: String

    var startBtn: Button? = null
    var timeTextView: TextView? = null
    var scoreTextView: TextView? = null
    var alertTextView: TextView? = null
    var questionTextView: TextView? = null
    var finalScoreTextView: TextView? = null
    var button0: Button? = null
    var button1: Button? = null
    var button2: Button? = null
    var button3: Button? = null
    var countDownTimer: CountDownTimer? = null
    var constraintLayout: ConstraintLayout? = null
    var lastLayout: ConstraintLayout? = null
    var buttonPlayAgain: Button? = null
    var random = Random()
    var a = 0
    var b = 0
    var indexOfCorrectAnswer = 0
    var answers = ArrayList<Int>()
    var points = 0
    var totalQuestions = 0

    private var gameInProgress = false

    private var gameCompleted = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        actionTitle = intent.getStringExtra("actionTitle").toString()

        when (actionTitle) {
            "addition" -> supportActionBar!!.title = "Addition"
            "subtraction" -> supportActionBar!!.title = "Subtraction"
            "multiplication" -> supportActionBar!!.title = "Multiplication"
            "division" -> supportActionBar!!.title = "Division"
        }


        @SuppressLint("SetTextI18n")
        startBtn = findViewById(R.id.btnStart)
        timeTextView = findViewById(R.id.TimeTextView)
        scoreTextView = findViewById(R.id.ScoreTextView)
        alertTextView = findViewById(R.id.AlertTextView)
        questionTextView = findViewById(R.id.QuestionTextView)
        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

        constraintLayout = findViewById(R.id.quizUi)
        lastLayout = findViewById(R.id.lastUi)
        lastLayout?.visibility = View.INVISIBLE
        constraintLayout?.visibility = View.INVISIBLE

    }

    @SuppressLint("SetTextI18n")
    fun nextQuestion() {
        a = random.nextInt(10)
        b = random.nextInt(10)

        if (actionTitle == "addition") {

            questionTextView!!.text = "$a+$b"
            indexOfCorrectAnswer = random.nextInt(4)
            answers.clear()
            for (i in 0..3) {
                if (indexOfCorrectAnswer == i) {
                    answers.add(a + b)
                } else {
                    var wrongAnswer = random.nextInt(20)
                    while (wrongAnswer == a + b) {
                        wrongAnswer = random.nextInt(20)
                    }
                    answers.add(wrongAnswer)
                }
            }
            button0!!.text = Integer.toString(answers[0])
            button1!!.text = Integer.toString(answers[1])
            button2!!.text = Integer.toString(answers[2])
            button3!!.text = Integer.toString(answers[3])
        } else if (actionTitle == "multiplication") {


            questionTextView!!.text = "$a x $b"
            indexOfCorrectAnswer = random.nextInt(4)
            answers.clear()
            for (i in 0..3) {
                if (indexOfCorrectAnswer == i) {
                    answers.add(a * b)
                } else {
                    var wrongAnswer = random.nextInt(20)
                    while (wrongAnswer == a * b) {
                        wrongAnswer = random.nextInt(20)
                    }
                    answers.add(wrongAnswer)
                }
            }
            button0!!.text = Integer.toString(answers[0])
            button1!!.text = Integer.toString(answers[1])
            button2!!.text = Integer.toString(answers[2])
            button3!!.text = Integer.toString(answers[3])
        }
       else if (actionTitle == "division") {
            // Division-specific code for values up to 100
            do {
                // Generate random values for a and b
                a = random.nextInt(101) // Up to 100 (inclusive)
                b = random.nextInt(10) + 1 // Non-zero divisor, up to 10


            } while (a % b != 0 || a / b < 0 || a / b > 100)

            questionTextView!!.text = "$a / $b"

            indexOfCorrectAnswer = random.nextInt(4)
            answers.clear()
            for (i in 0..3) {
                if (indexOfCorrectAnswer == i) {
                    answers.add(a / b)
                } else {
                    var wrongAnswer: Int
                    do {

                        wrongAnswer = random.nextInt(101)
                    } while (wrongAnswer == a / b)
                    answers.add(wrongAnswer)
                }
            }
            button0!!.text = Integer.toString(answers[0])
            button1!!.text = Integer.toString(answers[1])
            button2!!.text = Integer.toString(answers[2])
            button3!!.text = Integer.toString(answers[3])
        }
        else {
            questionTextView!!.text = "$a - $b"
            indexOfCorrectAnswer = random.nextInt(4)
            answers.clear()
            for (i in 0..3) {
                if (indexOfCorrectAnswer == i) {
                    answers.add(a - b)
                } else {
                    // Generate a random number that is not equal to a - b
                    var wrongAnswer = random.nextInt(40) - 20
                    while (wrongAnswer == a - b) {
                        wrongAnswer = random.nextInt(40) - 20
                    }
                    answers.add(wrongAnswer)
                }
            }
            button0!!.text = Integer.toString(answers[0])
            button1!!.text = Integer.toString(answers[1])
            button2!!.text = Integer.toString(answers[2])
            button3!!.text = Integer.toString(answers[3])
        }


    }


    fun optionSelect(view: View) {
        totalQuestions++
        if (Integer.toString(indexOfCorrectAnswer) == view.tag.toString()) {
            points += 1
            alertTextView!!.text = "Correct"
        } else {
            alertTextView!!.text = "Wrong"
        }
        scoreTextView!!.text = "$points/$totalQuestions"
        nextQuestion()
    }


    fun start(view: View?) {

        gameInProgress = true
        startBtn!!.visibility = View.INVISIBLE
        constraintLayout!!.visibility = View.VISIBLE
        nextQuestion()
        countDownTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeTextView!!.text = (millisUntilFinished / 1000).toString() + "s"
            }

            override fun onFinish() {

                if (!isFinishing) {
                    gameInProgress = false
                    gameCompleted = false
                    var score = ""
                    score =
                        Integer.toString(points) + "/" + Integer.toString(totalQuestions)

                    val intent = Intent(this@GameActivity, ResultsActivity::class.java)
                    intent.putExtra("Score", score)
                    startActivity(intent)
                    finish()


                }
            }
        }.start()
    }

    override fun onBackPressed() {
        if (!gameInProgress || gameCompleted) {
            super.onBackPressed()
        } else {

            AlertDialog.Builder(this)
                .setTitle("Game in Progress")
                .setMessage("Are you sure you want to exit the game?")
                .setPositiveButton("Yes") { _, _ -> super.onBackPressed() }
                .setNegativeButton("No", null)
                .show()
        }

    }
}


