package com.example.healthcare

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_buy_medicine_details.*

class BuyMedicineDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine_details)

        var it=intent
        textViewBMD.text=it.getStringExtra("text1")
        editTextLBMD.text= Editable.Factory.getInstance().newEditable(it.getStringExtra("text2"))
        BMDCartTotal.text = "Total Cost"+it.getStringExtra("text3")+"/-"

        btnBMDBack.setOnClickListener {
            val int= Intent(this,BuyMedicineActivity::class.java)
            startActivity(int)
        }

        btnBMDAddCart.setOnClickListener {
            val shredPref=getSharedPreferences("shared_prefs", MODE_PRIVATE)
            val username=shredPref.getString("username","").toString()

            val product= textViewBMD.text.toString()
            val text3 = intent.getStringExtra("text3")
            val price = text3?.toFloatOrNull()

            //        created DataBase instance
            val db = DataBase(this,"healthcare",null,1)


            if(db.login(username,product)==1) {
                Toast.makeText(this, "Product Alredy Added", Toast.LENGTH_SHORT).show()
            }else{
                if (price != null) {
                    db.addCart(username,product,price,"medicine")
                }
                Toast.makeText(this,"Added to cart",Toast.LENGTH_SHORT).show()
                val it=Intent(this,BuyMedicineActivity::class.java)
                startActivity(it)
            }

        }
    }
}