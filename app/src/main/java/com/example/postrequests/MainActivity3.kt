package com.example.postrequests

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.recipeapp.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity3 : AppCompatActivity() {
    lateinit var call: Call<List<Users.UserData>>
    lateinit var etID: EditText
    lateinit var etLoc: EditText
    lateinit var etNam: EditText
    lateinit var Upbtn: Button
    lateinit var Delbtn: Button

    private val apiInterface by lazy { APIClient().getClient()?.create(APIInterface::class.java) }

    var nam = ""
    var loc = ""
    var userID = 241

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        init()

        Upbtn.setOnClickListener {
            gettext()
            update()
        }
        Delbtn.setOnClickListener {
            gettext()
            delete()
        }


    }

    fun init(){
        etID = findViewById(R.id.etID)
        etNam = findViewById(R.id.etNam)
        etLoc = findViewById(R.id.etLoc)
        Upbtn = findViewById(R.id.Upbtn)
        Delbtn = findViewById(R.id.Delbtn)

    }
    fun gettext(){
        userID = etID.text.toString().toInt()
        nam = etNam.text.toString()
        loc = etLoc.text.toString()

    }
    fun update(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface!!.update(userID, Users.UserData(userID,nam,loc)).enqueue(object :
            Callback<Users.UserData> {
            override fun onResponse(call: Call<Users.UserData>, response: Response<Users.UserData>) {
                Toast.makeText(this@MainActivity3,"updated", Toast.LENGTH_SHORT).show()
            }
            override fun onFailure(call: Call<Users.UserData>, t: Throwable) {
                Toast.makeText(this@MainActivity3,"failure",Toast.LENGTH_SHORT).show()
            }
        })
    }
    private fun delete(){
        apiInterface?.delete(userID)?.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@MainActivity3,"Deleted",Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MainActivity3,"failure",Toast.LENGTH_SHORT).show()
            }
        })
    }

}