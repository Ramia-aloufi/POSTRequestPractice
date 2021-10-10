package com.example.postrequests

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var btn1: Button
    lateinit var tv1: TextView
    lateinit var call: Call<List<Users.UserData>>
     var txt = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init()
        getUser()

        btn1.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun init() {
        btn1 = findViewById(R.id.btn1)
        tv1 = findViewById(R.id.tv1)
    }

    fun getUser() {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        call = apiInterface!!.getit()
        val progressDialog = ProgressDialog(this@MainActivity2)
        progressDialog.setMessage("Please wait")
        progressDialog.show()

        call?.enqueue(object :Callback<List<Users.UserData>> {
            override fun onResponse(call: Call<List<Users.UserData>>,response: Response<List<Users.UserData>>) {
                progressDialog.dismiss()
                val resource = response.body()
                for(user in resource!!){
                    val nameList = user.name
                    val locList = user.location
                    txt += "$nameList \n $locList\n\n"
                    tv1.text= txt

                }
       }

            override fun onFailure(call: Call<List<Users.UserData>>, t: Throwable) {
                progressDialog.dismiss()
                call.cancel()
            }
        })
    }
}


//        call?.enqueue(object : Callback<Users.UserData>{
//            override fun onResponse(call: Call<Users.UserData?>?, response: Response<Users.UserData?>?) {
//                Log.d("TAG", response.code().toString() + "")
//                progressDialog.dismiss()
//                Toast.makeText(this@MainActivity2,"detdata", Toast.LENGTH_SHORT).show()
//                val resource = response
//                val detdata = resource?.name.toString()
//                println(detdata)
//                tv1.text = detdata
//            }
//
//            override fun onFailure(call: Call<Users.UserData?>, t: Throwable?) {
//                Toast.makeText(this@MainActivity2,"failure", Toast.LENGTH_SHORT).show()
//                call.cancel()
//                progressDialog.dismiss()
//
//            }
//        })

