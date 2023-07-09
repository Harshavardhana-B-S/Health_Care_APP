package com.example.healthcare

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_buy_medicine_book.*


class BuyMedicineBookActivity : AppCompatActivity() {

    private lateinit var fullName: EditText
    private lateinit var address: EditText
    private lateinit var pinCode: EditText
    private lateinit var contactNum: EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine_book)


        fullName=findViewById(R.id.editTextBMFullName)
        address=findViewById(R.id.editTextBMAddress)
        pinCode=findViewById(R.id.editTextBMPinCode)
        contactNum=findViewById(R.id.editTextBMContact)

        //Retrieve Data from  intent
        val it = intent
        val price: Array<String> = it.getStringExtra("price")?.split(":".toRegex())?.toTypedArray() ?: emptyArray()
        val date=it.getStringExtra("date")

        val floatValue = price[1].toString().toFloat()

        buttonBMBook.setOnClickListener {
            val shredPref=getSharedPreferences("shared_prefs", MODE_PRIVATE)
            val username=shredPref.getString("username","").toString()

            val db = DataBase(this,"healthcare",null,1)
            db.addOrder(username,fullName.text.toString(),address.text.toString(),contactNum.text.toString(),
                pinCode.text.toString(),date.toString(),"",floatValue,"medicine")
            db.removeCart(username,"medicine")
            Toast.makeText(this, "Booking Successful", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }





    }
}