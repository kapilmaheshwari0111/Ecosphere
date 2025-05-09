package com.example.mobileappsdg

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ChatBot : AppCompatActivity() {

    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var chatLayout: LinearLayout
    private lateinit var thinkingIndicator: TextView // Thinking indicator

    // Predefined responses related to the app and SDG 12
    private val chatBotResponses = mapOf(
        "hi" to "Hello! I'm EcoBot, your assistant for sustainable living. How can I help you today?",
        "hello" to "Hi there! I'm EcoBot. What would you like to know?",
        "features" to "This app includes a store locator, resource calculator, sustainability tips, videos, achievements tracking, and weekly rewards!",
        "store" to "You can use the store locator feature to find stores that align with SDG 12 near you.",
        "resource calculator" to "The resource calculator helps you assess your resource usage and find ways to improve sustainability.",
        "tips" to "We provide tips on reducing waste, recycling, and sustainable living practices.",
        "videos" to "Yes! Check out our videos section for informative content on sustainability.",
        "achievements" to "You can track your achievements in the achievements section of the app, where you'll see your progress!",
        "weekly rewards" to "Weekly rewards are incentives for engaging with the app and implementing sustainable practices!",
        "thank you" to "Great! I'm glad to help you!"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_bot)

        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)
        chatLayout = findViewById(R.id.chatLayout)
        thinkingIndicator = TextView(this) // Initialize thinking indicator

        sendButton.setOnClickListener {
            val userMessage = messageInput.text.toString().trim()
            if (userMessage.isNotEmpty()) {
                displayMessage("You: $userMessage", true)
                messageInput.text.clear()

                // Display thinking indicator
                displayThinkingIndicator()

                // Simulate thinking time
                Handler().postDelayed({
                    // Get chatbot response
                    val botResponse = getChatBotResponse(userMessage.toLowerCase())
                    removeThinkingIndicator() // Remove thinking indicator
                    displayMessage("EcoBot: $botResponse", false)
                }, 1000) // 1 second delay
            }
        }
    }

    private fun displayMessage(message: String, isUserMessage: Boolean) {
        val textView = TextView(this)
        textView.text = message
        textView.textAlignment = if (isUserMessage) {
            TextView.TEXT_ALIGNMENT_VIEW_END
        } else {
            TextView.TEXT_ALIGNMENT_VIEW_START
        }
        chatLayout.addView(textView)
    }

    private fun displayThinkingIndicator() {
        thinkingIndicator.text = "EcoBot is thinking..."
        thinkingIndicator.textAlignment = TextView.TEXT_ALIGNMENT_VIEW_START
        chatLayout.addView(thinkingIndicator)
    }

    private fun removeThinkingIndicator() {
        chatLayout.removeView(thinkingIndicator)
    }

    private fun getChatBotResponse(userMessage: String): String {
        // Check for keywords in the user message
        for (keyword in chatBotResponses.keys) {
            if (userMessage.contains(keyword)) {
                return chatBotResponses[keyword]!!
            }
        }
        return when {
            userMessage.contains("thank you") -> "Great! I'm glad to help you!"
            else -> "Ahh, I don't know. You can contact support at 1234567890."
        }
    }
}
