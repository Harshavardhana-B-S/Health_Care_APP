package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var  mailId: EditText
    private lateinit var password: EditText
    private lateinit var confirmPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username = findViewById(R.id.editTextRegUsername)
        mailId = findViewById(R.id.editTextRegEmailId)
        password = findViewById(R.id.editTextRegPassword)
        confirmPassword = findViewById(R.id.editTextRegConfirmPassword)
//            created instance
            val db = DataBase(this,"healthcare",null,1)

        buttonRegister.setOnClickListener {

            val username = username.text.toString()
            val mailId = mailId.text.toString()
            val password = password.text.toString()
            val confirmPassword = confirmPassword.text.toString()

            if (username.isEmpty() || mailId.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
                Toast.makeText(this, "Fill the required details", Toast.LENGTH_SHORT).show()
            else {
                if (password.compareTo(confirmPassword) == 0) {
                    if (isValid(password)) {
                  //database function
                        db.register(username,mailId,password)
                        Toast.makeText(this, "Record Inserted", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        Toast.makeText(this, "Password must contain at least 8 characters" +
                                "a letter, a number , a special character", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show()
                }
            }
        }
        textViewOldUser.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun isValid(password: String): Boolean {
        var f1 = 0
        var f2 = 0
        var f3 = 0

        if (password.length < 8) {
            return false
        } else {
            for (element in password) {
                if (element.isLetter()) {
                    f1 = 1
                }
            }

            for (element in password) {
                if (element.isDigit()) {
                    f2 = 1
                }
            }

            for (element in password) {
                if (element.code in 33..46 || element.code == 64) {
                    f3 = 1
                }
            }

            if(f1==1 && f2==1 && f3==1)
                return true

            return false
        }
    }

}
