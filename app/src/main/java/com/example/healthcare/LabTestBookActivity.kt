package com.example.healthcare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_lab_test_book.*

class LabTestBookActivity : AppCompatActivity() {

    private lateinit var fullName: EditText
    private lateinit var address: EditText
    private lateinit var pinCode:EditText
    private lateinit var contactNum:EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test_book)

        fullName=findViewById(R.id.editTextLTBFullName)
        address=findViewById(R.id.editTextLTBAddress)
        pinCode=findViewById(R.id.editTextLTBPinCode)
        contactNum=findViewById(R.id.editTextLTBContact)

        //Retrieve Data from  intent
        val it = intent
        val price: Array<String> = it.getStringExtra("price")?.split(":".toRegex())?.toTypedArray() ?: emptyArray()
        val date=it.getStringExtra("date")
        val time=it.getStringExtra("time")

        val floatValue = price[1].toString().toFloat()

        buttonLTBook.setOnClickListener {
              val shredPref=getSharedPreferences("shared_prefs", MODE_PRIVATE)
               val username=shredPref.getString("username","").toString()

            val db = DataBase(this,"healthcare",null,1)
            db.addOrder(username,fullName.text.toString(),address.text.toString(),contactNum.text.toString(),
                pinCode.text.toString(),date.toString(),time.toString(),floatValue,"lab")
             db.removeCart(username,"lab")
            Toast.makeText(this, "Booking Successful", Toast.LENGTH_SHORT).show()
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }
}