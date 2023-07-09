package com.example.healthcare

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SimpleAdapter
import kotlinx.android.synthetic.main.activity_buy_medicine.*

class BuyMedicineActivity : AppCompatActivity() {

    val packages= arrayOf (
        arrayOf("Upriss-03 100010 Capsule", "", "", "", "50"),
        arrayOf("HealthVit Chromium Picolinate200mcg Capsule", "", "", "", "350"),
        arrayOf("vitamin B Complex Capsules", "", "", "", "448"),
        arrayOf("Inlife Vitamin E Wheat Germ Oil Capsule", "", "", "", "539"),
        arrayOf("Dolo 650 Tablet", "", "", "", "30"),
        arrayOf("Crocin 650 Advance Tablet", "", "", "", "50"),
        arrayOf("Strepsils Hedicated Lozenges for Sore Throat","", "", "", "40"),
        arrayOf("Tata 1ing Calcium Vitamin D3", "", "", "", "30"),
        arrayOf("Fereonia- XT Tablet","","","","130")
    )

    val package_details= arrayOf(
        "Building and keeping the bones & teeth strong\n" +
                "Reducing Fatigue/stress and muscular pains\n" +
                "Boosting inmunity and increasing resistance against infection",

        "Chromium is an essential trace mineral that plays an important role in helping insulin regulation",

        "Provides relief from vitamin B deficiencies\n"+
            "Helps in formation of red blood cells\n"+
            "Maintains healthy nervous system",

        "It promotes health as well as skin benefit.\n" +
                "It helps reduce skin blemish and pigmentation.\n" +
                "It act as safeguard the skin from the harsh UVA and UVB sun rays.",

        "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical mess",
        "Helps relieve fever and bring down a high temperature\n" +
             "Suitable for people with a heart condition or high blood pressure",
        "Relieves the symptoms of a bacterial throat infection and soothes the recovery process\n" +
            "Provides a warm and comforting feeling during sore throat",
        "Reduces the risk of calcium deficiency, Rickets, and Osteoporosis\n"+
            "Promotes mobility and flexibility of joints",
        "Helps to reduce the iron deficiency due to chronic blood loss or low intake of iron"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy_medicine)


        buttonBMBack.setOnClickListener {
            val it = Intent(this,HomeActivity::class.java)
            startActivity(it)
        }

        buttonBMAddCart.setOnClickListener {
            val it = Intent(this,CartBuyMedicineActivity::class.java)
            startActivity(it)
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
        listViewBM.adapter=sa

        listViewBM.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, BuyMedicineDetailsActivity::class.java)
            intent.putExtra("text1", packages[i][0])
            intent.putExtra("text2",package_details[i])
            intent.putExtra("text3", packages[i][4])
            startActivity(intent)
        }


    }
}