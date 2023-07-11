package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_health_articles.*

class HealthArticlesActivity : AppCompatActivity() {

    val healthDeatils= arrayOf(
        arrayOf("Walking Daily", "", "","", "Click More Details"),
        arrayOf("Home care of COVID-19","","","", "Click More Details"),
        arrayOf("Stop Smoking", "","","","Click More Details"),
        arrayOf("Menstrual Cramps","","","","Click More Details"),
        arrayOf("Healthy Gut","","","","Click More Details")
    )

    val images= arrayOf(R.drawable.health1,R.drawable.health2,R.drawable.health3,
            R.drawable.health4,R.drawable.health5)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_articles)

        backButton.setOnClickListener {
            val it= Intent(this,HomeActivity::class.java)
            startActivity(it)
        }

        val list = mutableListOf<MutableMap<String, String>>()
        // val item = mutableMapOf<String, String>()

        for(i in 0 until healthDeatils.size){
            val item = mutableMapOf<String, String>()
            item["line1"] = healthDeatils[i][0]
            item["line2"] = healthDeatils[i][1]
            item["line3"] = healthDeatils[i][2]
            item["line4"] = healthDeatils[i][3]
            item["line5"] = healthDeatils[i][4]
            list.add(item)
        }

        val sa = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )
        
        listView.adapter = sa

//        listView.setOnItemClickListener { adapterView, view, i, l ->
//            val intent = Intent(this, HealthArticlesDetailsActivity::class.java)
//            intent.putExtra("text1", healthDeatils[i][0])
//            intent.putExtra("text2", images[i])
//            startActivity(intent)
//        }



    }
}