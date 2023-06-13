package com.example.healthcare


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView

class DoctorDetailsActivity : AppCompatActivity() {

    private val doctorDetails1 = arrayOf(
        arrayOf("Doctor Name: Ajit Saste", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No: 9898989898", "600"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No: 7828989898", "90"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No: 8878989898", "300"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Bengalrur", "Exp: 6yrs", "Mobile No: 9891239898", "500"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Delhi", "Exp: 7yrs", "Mobile No: 98454989898", "800")
    )

    private val doctorDetails2 = arrayOf(
        arrayOf("Doctor Name: Arjun Boi", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No: 9898989898", "600"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No: 7828989898", "90"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No: 8878989898", "300"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Bengalrur", "Exp: 6yrs", "Mobile No: 9891239898", "500"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Delhi", "Exp: 7yrs", "Mobile No: 98454989898", "800")
    )

    private val doctorDetails3 = arrayOf(
        arrayOf("Doctor Name: Aryan BOi", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No: 9898989898", "600"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No: 7828989898", "90"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No: 8878989898", "300"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Bengalrur", "Exp: 6yrs", "Mobile No: 9891239898", "500"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Delhi", "Exp: 7yrs", "Mobile No: 98454989898", "800")
    )

    private val doctorDetails4 = arrayOf(
        arrayOf("Doctor Name: Naveen Boi", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No: 9898989898", "600"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No: 7828989898", "90"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No: 8878989898", "300"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Bengalrur", "Exp: 6yrs", "Mobile No: 9891239898", "500"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Delhi", "Exp: 7yrs", "Mobile No: 98454989898", "800")
    )

    private val doctorDetails5 = arrayOf(
        arrayOf("Doctor Name: Rocky Boi", "Hospital Address: Pimpri", "Exp: 5yrs", "Mobile No: 9898989898", "600"),
        arrayOf("Doctor Name: Prasad Pawar", "Hospital Address: Nigdi", "Exp: 15yrs", "Mobile No: 7828989898", "90"),
        arrayOf("Doctor Name: Swapnil Kale", "Hospital Address: Pune", "Exp: 8yrs", "Mobile No: 8878989898", "300"),
        arrayOf("Doctor Name: Deepak Deshmukh", "Hospital Address: Bengalrur", "Exp: 6yrs", "Mobile No: 9891239898", "500"),
        arrayOf("Doctor Name: Ashok Panda", "Hospital Address: Delhi", "Exp: 7yrs", "Mobile No: 98454989898", "800")
    )


    private lateinit var titleDD: TextView
    private lateinit var backBtn:Button
    private lateinit var lst:ListView

    var doctorDetails: Array<Array<String>> = emptyArray()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_details)

        titleDD=findViewById(R.id.textViewDDTitle)
        backBtn=findViewById(R.id.buttonDDBack)

        val intent= intent
        val title=intent.getStringExtra("title")
        titleDD.text=title

        if(titleDD.text.toString().compareTo("Family Physicians")==0)
            doctorDetails=doctorDetails1
        else
            if(titleDD.text.toString().compareTo("Dietician")==0)
                doctorDetails=doctorDetails2
        else
            if(titleDD.text.toString().compareTo("Dentist")==0)
                    doctorDetails=doctorDetails3
        else if(titleDD.text.toString().compareTo("Surgeon")==0)
            doctorDetails=doctorDetails4
        else
            doctorDetails=doctorDetails5

        backBtn.setOnClickListener {
            val intent= Intent(this,FindDoctorActivity::class.java)
            startActivity(intent)
        }

        val list = mutableListOf<MutableMap<String, String>>()
        val item = mutableMapOf<String, String>()

        for(i in 0 until doctorDetails.size){
            val item = mutableMapOf<String, String>()
            item["line1"] = doctorDetails[i][0]
            item["line2"] = doctorDetails[i][1]
            item["line3"] = doctorDetails[i][2]
            item["line4"] = doctorDetails[i][3]
            item["line5"] = "Coins Fees:"+doctorDetails[i][4]+"/-"
            list.add(item)
        }

//        val sa = SimpleAdapter(this,list,
//            R.layout.multi_lines,
//            arrayOf("line1", "line2", "line3", "line4", "line5"),
//            arrayOf(R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e)
//        )

        val sa = SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )

       lst=findViewById(R.id.listViewDD)
        lst.adapter = sa





    }

}



