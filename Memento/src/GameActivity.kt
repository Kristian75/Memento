package com.example.memento

import android.os.Bundle
import android.widget.GridLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.memento.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private val cards = mutableListOf<Card>()
    private var firstCard: Card? = null
    private var secondCard: Card? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupGame()
    }

    private fun setupGame() {
        val images = listOf(
            R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,
            R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4
        ).shuffled()

        for (image in images) {
            val card = Card(this, image)
            card.setOnClickListener { onCardClicked(card) }
            cards.add(card)
        }

        val gridLayout = binding.gridLayout
        gridLayout.rowCount = 4
        gridLayout.columnCount = 4

        for (card in cards) {
            gridLayout.addView(card)
        }
    }

    private fun onCardClicked(card: Card) {
        if (firstCard == null) {
            firstCard = card
            card.reveal()
        } else if (secondCard == null) {
            secondCard = card
            card.reveal()

            if (firstCard?.imageResId == secondCard?.imageResId) {
                firstCard = null
                secondCard = null
                Toast.makeText(this, "Pair found!", Toast.LENGTH_SHORT).show()
            } else {
                binding.gridLayout.postDelayed({
                    firstCard?.hide()
                    secondCard?.hide()
                    firstCard = null
                    secondCard = null
                }, 1000)
            }
        }
    }
}