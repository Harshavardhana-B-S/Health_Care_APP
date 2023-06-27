package com.example.healthcare

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lab_test_details.*

class LabTestDetailsActivity : AppCompatActivity() {

    //create variables/instance
    private lateinit var pkgName: TextView
    private lateinit var totalCost: TextView
    private lateinit var lbDetails: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test_details)

        totalCost=findViewById(R.id.textViewCartTotal)
        pkgName=findViewById(R.id.textViewBM)
        lbDetails=findViewById(R.id.editTextLBD)

//        lbDetails.keyListener=null

        //Retrieve Data from  intent
        val it = intent
        val pkgNameInit = it.getStringExtra("text1")
        val lbDetailsInit = it.getStringExtra("text2")
        val totalCostInit = it.getStringExtra("text3")

        //assign intent data
        // Editable obj created from String, since string cannot be assigned to Editable variables
        pkgName.text =pkgNameInit
        lbDetails.text = Editable.Factory.getInstance().newEditable(lbDetailsInit)
        totalCost.text = Editable.Factory.getInstance().newEditable("Fess:$totalCostInit/-")

        buttonODBack.setOnClickListener {
            val it = Intent(this,LabTestActivity::class.java)
            startActivity(it)
        }

        buttonLBAddCart.setOnClickListener {
            val sharedpref = getSharedPreferences ("shared_prefs", MODE_PRIVATE)
            val username =sharedpref.getString("username","").toString()

            val product=pkgName.text.toString()
//            val price= Float.parseFloat(intent.getStringExtra("text3").toString())
            val price = intent.getStringExtra("text3")?.toFloatOrNull() ?: 0.0f

            //        created DataBase instance
            val db = DataBase(this,"healthcare",null,1)

            if(db.checkCart(username,product)==1){
                Toast.makeText(this,"Product Already Added",Toast.LENGTH_SHORT).show()
            }else {
                db.addCart(username,product,price,"lab")
                Toast.makeText(this,"Record Inserted to Cart",Toast.LENGTH_SHORT).show()
                val it = Intent(this,LabTestActivity::class.java)
                startActivity(it)
            }

        }



    }
}