package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var username:EditText
    private lateinit var password:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        username=findViewById(R.id.editTextLoginUsername)
        password=findViewById(R.id.editTextLoginPassword)
        val db = DataBase(this,"healthcare",null,1)


        buttonLogin.setOnClickListener {
            val username=username.text.toString()
            val password=password.text.toString()
            if(username.isEmpty() || password.isEmpty())
                Toast.makeText(this,"Fill Credentials",Toast.LENGTH_SHORT).show()
            else{
                if(db.login(username,password)==1) {
                    Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show()

                }else
                    Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT).show()
            }
        }


        textViewNewUser.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }
}