
package com.example.rockpaperscissors

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var playerChoiceImage: ImageView
    private lateinit var computerChoiceImage: ImageView
    private lateinit var resultTextView: TextView
    private lateinit var playerScoreTextView: TextView
    private lateinit var computerScoreTextView: TextView
    private lateinit var playAgainButton: Button
    
    private var playerScore = 0
    private var computerScore = 0
    private val choices = listOf("rock", "paper", "scissors")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerChoiceImage = findViewById(R.id.player_choice)
        computerChoiceImage = findViewById(R.id.computer_choice)
        resultTextView = findViewById(R.id.result_text)
        playerScoreTextView = findViewById(R.id.player_score)
        computerScoreTextView = findViewById(R.id.computer_score)
        playAgainButton = findViewById(R.id.play_again)

        findViewById<Button>(R.id.rock_button).setOnClickListener { playGame("rock") }
        findViewById<Button>(R.id.paper_button).setOnClickListener { playGame("paper") }
        findViewById<Button>(R.id.scissors_button).setOnClickListener { playGame("scissors") }

        playAgainButton.setOnClickListener { resetGame() }
        playAgainButton.isEnabled = false
    }

    private fun playGame(playerChoice: String) {
        val computerChoice = choices[Random.nextInt(3)]
        updateImages(playerChoice, computerChoice)
        val winner = determineWinner(playerChoice, computerChoice)
        updateScore(winner)
        checkGameEnd()
    }

    private fun updateImages(playerChoice: String, computerChoice: String) {
        val imageMap = mapOf(
            "rock" to R.drawable.rock,
            "paper" to R.drawable.paper,
            "scissors" to R.drawable.scissors
        )
        playerChoiceImage.setImageResource(imageMap[playerChoice]!!)
        computerChoiceImage.setImageResource(imageMap[computerChoice]!!)
    }

    private fun determineWinner(player: String, computer: String): String {
        return if (player == computer) {
            "tie"
        } else if ((player == "rock" && computer == "scissors") ||
                   (player == "scissors" && computer == "paper") ||
                   (player == "paper" && computer == "rock")) {
            "player"
        } else {
            "computer"
        }
    }

    private fun updateScore(winner: String) {
        when (winner) {
            "player" -> {
                playerScore++
                playerScoreTextView.text = "Player: $playerScore"
                resultTextView.text = "You win!"
            }
            "computer" -> {
                computerScore++
                computerScoreTextView.text = "Computer: $computerScore"
                resultTextView.text = "Computer wins!"
            }
            else -> resultTextView.text = "It's a tie!"
        }
    }

    private fun checkGameEnd() {
        if (playerScore == 10 || computerScore == 10) {
            resultTextView.text = if (playerScore == 10) "You won the game!" else "Computer won the game!"
            disableGame()
        }
    }

    private fun disableGame() {
        findViewById<Button>(R.id.rock_button).isEnabled = false
        findViewById<Button>(R.id.paper_button).isEnabled = false
        findViewById<Button>(R.id.scissors_button).isEnabled = false
        playAgainButton.isEnabled = true
    }

    private fun resetGame() {
        playerScore = 0
        computerScore = 0
        playerScoreTextView.text = "Player: 0"
        computerScoreTextView.text = "Computer: 0"
        resultTextView.text = ""
        findViewById<Button>(R.id.rock_button).isEnabled = true
        findViewById<Button>(R.id.paper_button).isEnabled = true
        findViewById<Button>(R.id.scissors_button).isEnabled = true
        playAgainButton.isEnabled = false
    }
}
    
    <TextView android:id="@+id/result_text" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Choose an option!"/>

    <Button android:id="@+id/play_again" android:layout_width="wrap_content" android:layout_height="wrap_content" android:text="Play Again" android:visibility="gone"/>
</LinearLayout>
```
