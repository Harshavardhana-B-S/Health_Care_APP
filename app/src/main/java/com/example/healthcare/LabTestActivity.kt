package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_lab_test.*

class LabTestActivity : AppCompatActivity() {

    private val packages= arrayOf(
        arrayOf("Package 1: Full Body Checkup","", "", "","999"),
        arrayOf("Package 2: Blood Glucose Fasting","", "", "","299"),
        arrayOf("Package 3: COVID-19 Antibody - Ig6","", "", "","899"),
        arrayOf("Package 4: Thyroid Check","", "", "","499"),
        arrayOf("Package 5: Immunity Check","", "", "","699")
    )

    private val packagesDetails= arrayOf(
        "Blood Glucose Fasting\n"+
                "Complete Hemogram\n"+
                "HbA1c\n"+"Iron Studies\n"+
                "Kidney Function test\n"+
                "LDH Lactate Dehydrogenase, Serum\n"+
                "Lipid Profile\n"+
                "Liver Function Test",
        "Blood Glucose Fasting",
        "COVID-19 Antibody - Ig6",
        "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
        "Complete Hemogram\n"+
                "CRP (C Reactive Protein) Quantitative, Serum\n"+
                " Iron studies\n "+
                "Kidney Function test\n"+
                "Vitamin D Total -25 Hydroxy\n"+
                "Liver Function test\n"+
                "Lipid profile"
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lab_test)

        buttonODBack.setOnClickListener {
            val intent= Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }


       val list = mutableListOf<MutableMap<String, String>>()
        for(i in 0 until packages.size){
            val item = mutableMapOf<String, String>()
            item["line1"] = packages[i][0]
            item["line2"] = packages[i][1]
            item["line3"] = packages[i][2]
            item["line4"] = packages[i][3]
            item["line5"] = "Total Fees:"+packages[i][4]+"/-"
            list.add(item)
        }

        val sa= SimpleAdapter(
            this,
            list,
            R.layout.multi_lines,
            arrayOf("line1", "line2", "line3", "line4", "line5"),
            intArrayOf(R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e)
        )


       // set adapter on the list
        listViewBM.adapter = sa

        listViewBM.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this,LabTestDetailsActivity::class.java)
            intent.putExtra("text1", packages[i][0])
            intent.putExtra("text2", packagesDetails[i])
            intent.putExtra("text3", packages[i][4])
            startActivity(intent)
        }

        buttonLBAddCart.setOnClickListener {
            val intent = Intent(this,CartLabActivity::class.java)
            startActivity(intent)
        }





    }
}

