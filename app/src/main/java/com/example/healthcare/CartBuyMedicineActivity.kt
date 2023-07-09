package com.example.healthcare

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SimpleAdapter
import android.widget.TextView
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_cart_buy_medicine.*

import java.util.Calendar

class CartBuyMedicineActivity : AppCompatActivity() {

    private lateinit var dateBtn: Button
    private lateinit var total: TextView



    // Create Date and time picker variables/instance
    private lateinit var datePickerDialog: DatePickerDialog



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_buy_medicine)

        dateBtn=findViewById(R.id.buttonCartDate)

        total=findViewById(R.id.BMDCartTotal)


        val sharedpref = getSharedPreferences ("shared_prefs", Context.MODE_PRIVATE)
        val username=sharedpref.getString("username","").toString()

        //  DataBase created instance
        val db = DataBase(this,"healthcare",null,1)

        //get item in Cart
        val dbData: ArrayList<String> = db.getCartData(username, "medicine")
//        Toast.makeText(applicationContext,""+dbData,Toast.LENGTH_LONG).show()

        var totalAmt: Float = 0.0f
        var packages = Array(dbData.size) { arrayOfNulls<String>(0) }
        for (i in packages.indices) {
            packages[i] = Array(5) { "" }
        }

        for (i in 0 until dbData.size) {
            val arrData = dbData[i].toString()
            val strData = arrData.split("\\$".toRegex()).toTypedArray()
            packages[i][0] = strData[0]
            packages[i][4] = "Cost: ${strData[1]}/-"
            totalAmt += strData[1].toFloat()
        }

        total.text = "Total Cost : $totalAmt"

        val list = mutableListOf<MutableMap<String, String>>()
        for(i in 0 until  packages.size){
            val item = mutableMapOf<String, String>()
            item["line1"] = packages[i]?.get(0) ?: ""
            item["line2"] = packages[i]?.get(1) ?: ""
            item["line3"] = packages[i]?.get(2) ?: ""
            item["line4"] = packages[i]?.get(3) ?: ""
            item["line5"] = "Coins Fees:"+ packages[i][4]+"/-"
            list.add(item)
        }

        val sa = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )

        //instance of ListView and add adapter
        listViewBM.adapter = sa



        buttonCartBack.setOnClickListener {
            val intent= Intent(this,BuyMedicineActivity::class.java)
            startActivity(intent)
        }

        buttonCheckOut.setOnClickListener {
            val intent = Intent(this,BuyMedicineBookActivity::class.java)
            intent.putExtra("price", total.text)
            intent.putExtra("date", dateBtn.text)
            startActivity(intent)
        }

        //date Picker
        dateBtn.setOnClickListener {
            showDatePickerDialog()
        }

        //time picker

    }

    private fun showDatePickerDialog() {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        // Create a DatePickerDialog and set the initial date to the current date
        datePickerDialog = DatePickerDialog(this,
            { _, year, monthOfYear, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(Calendar.YEAR, year)
                selectedDate.set(Calendar.MONTH, monthOfYear)
                selectedDate.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                // Compare the selected date with the current date
                if (selectedDate.after(currentDate)) {
                    // Update the selectedDate TextView with the selected date
                    val selectedDateText = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year)
                    this. buttonCartDate.text = selectedDateText
                } else {
                    Toast.makeText(this, "Please select a future date", Toast.LENGTH_SHORT).show()
                }
            }, year, month, day)

        // Set the minimum date to the current date
        datePickerDialog.datePicker.minDate = currentDate.timeInMillis

        // Show the DatePickerDialog
        datePickerDialog.show()
    }
}