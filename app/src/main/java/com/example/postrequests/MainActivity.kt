package com.example.postrequests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.recipeapp.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var tvid:EditText
    lateinit var tvname:EditText
    lateinit var tvloc:EditText
    lateinit var btn:Button
    lateinit var vi:Button
    lateinit var call: Call<Users.UserData>
    var name = "ram"
    var loc = "KSA"
    var id = 0
    lateinit var nn:Users.UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        btn.setOnClickListener {
             id =  tvid.text.toString().toInt()
             name =  tvname.text.toString()
             loc = tvloc.text.toString()
            nn = Users.UserData(id,name,loc)
            callfun()
        }
        vi.setOnClickListener {
            var intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }


    }

    fun init(){
        tvid = findViewById(R.id.tvid)
        tvname = findViewById(R.id.tvname)
        tvloc = findViewById(R.id.tvloc)
        btn = findViewById(R.id.btn)
        vi = findViewById(R.id.view)


    }

    fun callfun(){


        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface!!.added(nn).enqueue(object : Callback<Users.UserData?> {
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