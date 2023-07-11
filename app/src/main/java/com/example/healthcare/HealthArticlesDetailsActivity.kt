package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class HealthArticlesDetailsActivity : AppCompatActivity() {

    private lateinit var tv1: TextView
    private lateinit var img: ImageView
    private lateinit var backBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_articles_details)

        backBtn = findViewById(R.id.buttonBackHAD)
        img = findViewById(R.id.imageViewHAD)
        tv1 = findViewById(R.id.textViewHAD)

        val it = intent
        tv1.text = it.getStringExtra("text1")

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val resId = bundle.getInt("text2");
            img.setImageResource(resId);
        }

        backBtn.setOnClickListener {
            val it= Intent(this,HealthArticlesActivity::class.java)
            startActivity(it)
        }


    }
}