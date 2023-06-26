package com.example.healthcare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_order_details.buttonODBack
import kotlinx.android.synthetic.main.activity_order_details.listViewOD

class OrderDetailsActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)



        buttonODBack.setOnClickListener {
            val it= Intent(this,HomeActivity::class.java)
            startActivity(it)
        }


        val db = DataBase(this,"healthcare",null,1)  // created DataBase instance
        val shredPref= getSharedPreferences("shared_prefs", Context.MODE_PRIVATE)
        val username =shredPref.getString("username","").toString()

        val dbData =db.getorderData(username)
        var orderDetails: Array<Array<String>> = Array(dbData.size) { arrayOf<String>() }

        for(i in 0 until orderDetails.size){
            orderDetails[i] = Array(5) { "" }
            val arrData = dbData[i].toString()
            //val strData= arrayOf(arrData.split(java.util.regex.Pattern.quote("$")))
            val strData: Array<String> = arrData.split("\\$".toRegex()).toTypedArray()
            orderDetails[i][0]= strData[0].toString()
            orderDetails[i][1]= strData[1].toString()
            if(strData[6].compareTo("medicine")==0){
                orderDetails[i][3]="DEl: "+strData[4]
            }else {
                orderDetails[i][3]="Del: "+strData[4]+" "+strData[5]
            }
            orderDetails[i][2]="Rs. "+strData[6]
            orderDetails[i][4]=strData[6]
        }


        val list = mutableListOf<MutableMap<String, String>>()

        for(i in 0 until orderDetails.size){
            val item = mutableMapOf<String, String>()
            item["line1"] = orderDetails[i][0]
            item["line2"] = orderDetails[i][1]
            item["line3"] = orderDetails[i][2]
            item["line4"] = orderDetails[i][3]
            item["line5"] = orderDetails[i][4]
            list.add(item)
        }

        val sa = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )

        listViewOD.adapter = sa





    }
}