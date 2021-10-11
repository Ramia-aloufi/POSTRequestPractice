package com.example.postrequests

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeapp.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var tv1: TextView
    lateinit var rv: RecyclerView
    lateinit var call: Call<List<Users.UserData>>
    lateinit var alL:ArrayList<Users.UserData>
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

        btn2.setOnClickListener {
            var intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
        }

        rv.adapter = MyAdapter(alL)
        rv.layoutManager = LinearLayoutManager(this)


    }

    fun init() {
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.button2)
        rv  = findViewById(R.id.rv)
        alL = arrayListOf()
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
                    var users = Users.UserData(user.pk,user.name,user.location)

                    alL.add(user)

                }
                rv.adapter?.notifyDataSetChanged()

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

