package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity333 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main333)

        val NEXT = findViewById<Button>(R.id.profile)       //image view uses as a button for navigation
        NEXT.setOnClickListener {
            val intent = Intent(this, MainActivity111::class.java)
            startActivity(intent)

        }


        val NEXT1 = findViewById<Button>(R.id.con)         //button for navigation
        NEXT1.setOnClickListener {
            val intent = Intent(this, MainActivity444::class.java)
            startActivity(intent)

        }

        val NEXT2 = findViewById<Button>(R.id.helpcen)       //image view uses as a button for navigation
        NEXT2.setOnClickListener {
            val intent = Intent(this, MainActivity222::class.java)
            startActivity(intent)

        }



    }

}



