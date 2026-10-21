package com.example.quizimages


import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// Structure représentant une question du quiz
data class Question(
    val texte: String,
    val image: Int,
    val choix: Array<String>,
    val bonneReponse: Int,
    val descriptionImage: String
)

class MainActivity : AppCompatActivity() {

    // Les cinq questions du quiz
    private val questions = arrayOf(
        Question(
            "Quelle est cette forme géométrique ?",
            R.drawable.triangle,
            arrayOf("Triangle", "Carré", "Cercle"),
            0,
            "Un triangle bleu"
        ),
        Question(
            "Quelle est cette forme géométrique ?",
            R.drawable.carre,
            arrayOf("Rectangle", "Carré", "Étoile"),
            1,
            "Un carré rouge"
        ),
        Question(
            "Quelle est cette forme géométrique ?",
            R.drawable.cercle,
            arrayOf("Triangle", "Rectangle", "Cercle"),
            2,
            "Un cercle vert"
        ),
        Question(
            "Quelle est cette forme géométrique ?",
            R.drawable.etoile,
            arrayOf("Étoile", "Carré", "Triangle"),
            0,
            "Une étoile jaune"
        ),
        Question(
            "Quelle est cette forme géométrique ?",
            R.drawable.rectangle,
            arrayOf("Cercle", "Rectangle", "Carré"),
            1,
            "Un rectangle orange"
        )
    )

    // Variables du jeu
    private var indiceQuestion = 0
    private var score = 0
    private var reponseDonnee = false

    // Widgets de l'interface
    private lateinit var txtScore: TextView
    private lateinit var txtProgress: TextView
    private lateinit var txtQuestionNumber: TextView
    private lateinit var imgQuestion: ImageView
    private lateinit var txtQuestion: TextView
    private lateinit var txtFeedback: TextView
    private lateinit var progressQuiz: ProgressBar

    private lateinit var btnChoix1: Button
    private lateinit var btnChoix2: Button
    private lateinit var btnChoix3: Button
    private lateinit var btnSuivant: Button
    private lateinit var btnRejouer: Button

    private lateinit var layoutResultat: View
    private lateinit var txtResultat: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Récupération des éléments XML
        txtScore = findViewById(R.id.txt_score)
        txtProgress = findViewById(R.id.txt_progress)
        txtQuestionNumber = findViewById(R.id.txt_question_number)
        imgQuestion = findViewById(R.id.img_question)
        txtQuestion = findViewById(R.id.txt_question)
        txtFeedback = findViewById(R.id.txt_feedback)
        progressQuiz = findViewById(R.id.progress_quiz)

        btnChoix1 = findViewById(R.id.btn_choix1)
        btnChoix2 = findViewById(R.id.btn_choix2)
        btnChoix3 = findViewById(R.id.btn_choix3)
        btnSuivant = findViewById(R.id.btn_suivant)
        btnRejouer = findViewById(R.id.btn_rejouer)

        layoutResultat = findViewById(R.id.layout_resultat)
        txtResultat = findViewById(R.id.txt_resultat)

        // Gestion des clics sur les trois choix
        btnChoix1.setOnClickListener { verifierReponse(0) }
        btnChoix2.setOnClickListener { verifierReponse(1) }
        btnChoix3.setOnClickListener { verifierReponse(2) }

        // Navigation vers la question suivante
        btnSuivant.setOnClickListener {
            if (indiceQuestion < questions.size - 1) {
                indiceQuestion++
                afficherQuestion()
            } else {
                afficherResultat()
            }
        }

        // Recommencer le quiz
        btnRejouer.setOnClickListener {
            recommencerQuiz()
        }

        // Afficher la première question
        afficherQuestion()
    }

    // Afficher la question courante
    private fun afficherQuestion() {
        val question = questions[indiceQuestion]

        txtQuestionNumber.text =
            "Question ${indiceQuestion + 1} sur ${questions.size}"

        txtQuestion.text = question.texte
        imgQuestion.setImageResource(question.image)
        imgQuestion.contentDescription = question.descriptionImage

        btnChoix1.text = question.choix[0]
        btnChoix2.text = question.choix[1]
        btnChoix3.text = question.choix[2]

        // Réactiver les boutons
        btnChoix1.isEnabled = true
        btnChoix2.isEnabled = true
        btnChoix3.isEnabled = true

        // Réinitialiser les couleurs
        btnChoix1.setBackgroundColor(Color.LTGRAY)
        btnChoix2.setBackgroundColor(Color.LTGRAY)
        btnChoix3.setBackgroundColor(Color.LTGRAY)

        // Masquer les éléments de correction précédents
        txtFeedback.visibility = View.GONE
        btnSuivant.visibility = View.GONE
        btnSuivant.isEnabled = false

        reponseDonnee = false

        // Actualiser le score et la progression
        actualiserBandeau()
    }

    // Vérifier la réponse sélectionnée
    private fun verifierReponse(indiceChoix: Int) {

        // Empêcher plusieurs réponses à la même question
        if (reponseDonnee) return

        reponseDonnee = true

        val question = questions[indiceQuestion]

        val boutons = arrayOf(
            btnChoix1,
            btnChoix2,
            btnChoix3
        )

        // Désactiver les trois boutons après la réponse
        boutons.forEach { it.isEnabled = false }

        // Vérifier si la réponse est correcte
        if (indiceChoix == question.bonneReponse) {
            score++

            txtFeedback.text = "Bonne réponse ! Bravo."
            txtFeedback.setTextColor(Color.rgb(46, 125, 50))

            boutons[indiceChoix].setBackgroundColor(
                Color.rgb(165, 214, 167)
            )
        } else {
            txtFeedback.text = "Mauvaise réponse !"
            txtFeedback.setTextColor(Color.rgb(198, 40, 40))

            boutons[indiceChoix].setBackgroundColor(
                Color.rgb(239, 154, 154)
            )

            // Montrer également la bonne réponse
            boutons[question.bonneReponse].setBackgroundColor(
                Color.rgb(165, 214, 167)
            )
        }

        txtFeedback.visibility = View.VISIBLE

        // Afficher le bon bouton de navigation
        btnSuivant.text =
            if (indiceQuestion == questions.size - 1) {
                "Voir le résultat"
            } else {
                "Question suivante"
            }

        btnSuivant.isEnabled = true
        btnSuivant.visibility = View.VISIBLE

        // Une réponse donnée fait avancer la progression,
        // même si elle est incorrecte.
        actualiserBandeau()
    }

    // Mettre à jour le score et la progression
    private fun actualiserBandeau() {
        txtScore.text = "Score : $score/${questions.size}"

        val progression =
            indiceQuestion + if (reponseDonnee) 1 else 0

        progressQuiz.progress = progression
        txtProgress.text =
            "$progression réponse(s) sur ${questions.size}"
    }

    // Afficher le résultat final
    private fun afficherResultat() {
        // Masquer les éléments du quiz
        imgQuestion.visibility = View.GONE
        txtQuestion.visibility = View.GONE
        txtQuestionNumber.visibility = View.GONE

        btnChoix1.visibility = View.GONE
        btnChoix2.visibility = View.GONE
        btnChoix3.visibility = View.GONE

        txtFeedback.visibility = View.GONE
        btnSuivant.visibility = View.GONE

        // Afficher le résultat
        txtResultat.text = "Résultat final : $score/${questions.size}"
        layoutResultat.visibility = View.VISIBLE

        actualiserBandeau()
    }

    // Réinitialiser le jeu
    private fun recommencerQuiz() {
        indiceQuestion = 0
        score = 0
        reponseDonnee = false

        // Réafficher les éléments du quiz
        imgQuestion.visibility = View.VISIBLE
        txtQuestion.visibility = View.VISIBLE
        txtQuestionNumber.visibility = View.VISIBLE

        btnChoix1.visibility = View.VISIBLE
        btnChoix2.visibility = View.VISIBLE
        btnChoix3.visibility = View.VISIBLE

        layoutResultat.visibility = View.GONE

        // Réinitialiser la progression
        progressQuiz.progress = 0

        afficherQuestion()
    }
}

