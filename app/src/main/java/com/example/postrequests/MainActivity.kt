package com.example.postrequests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tvname:TextView
    lateinit var tvloc:TextView
    lateinit var btn:Button
    lateinit var vi:Button
    lateinit var call: Call<Users.UserData>
    var name = "ram"
    var loc = "KSA"
    lateinit var nn:Users.UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        btn.setOnClickListener {
             name =  tvname.text.toString()
             loc = tvloc.text.toString()
            nn = Users.UserData(name,loc)
            callfun()
        }
        vi.setOnClickListener {
            var intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


    }

    fun init(){
        tvname = findViewById(R.id.tvname)
        tvloc = findViewById(R.id.tvloc)
        btn = findViewById(R.id.btn)
        vi = findViewById(R.id.view)


    }

    fun callfun(){


        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        call= apiInterface!!.added(nn)

        call?.enqueue(object : Callback<Users.UserData?> {
            override fun onResponse( call: Call<Users.UserData?>,response: Response<Users.UserData?>) {
                Log.d("TAG", response.code().toString() + "")
                Toast.makeText(this@MainActivity,"added",Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<Users.UserData?>, t: Throwable?) {
                Toast.makeText(this@MainActivity,"failure",Toast.LENGTH_SHORT).show()
                call.cancel()
            }


        })
    }
}