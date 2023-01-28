package com.example.appejemplo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.VolleyLog
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.appejemplo1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //variable binding, que dará acceso a lo que está en la interfaz
    private lateinit var binding: ActivityMainBinding
    private var url:String="https://jsonplaceholder.typicode.com/posts"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.btnObtener.setOnClickListener{
            var parametro=binding.txtId.getText().toString();
            if(parametro.isNotEmpty()){
                //llamar funcion
                getWS(parametro)
            }else{
                Toast.makeText(applicationContext,"Especifique el id",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getWS(parametro: String) {
        //crear una instancia de RequestQueue
        val queue= Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest( //presentar los datos que vienen del get
            Request.Method.GET,url.plus("/").plus(parametro),null,
            {   response->
                binding.txtId.setText(response.getString("id"))//extrae de la respuesta el objeto
                binding.txtUserId.setText(response.getString("userId"))
                binding.txtTitle.setText(response.getString("title"))
                binding.txtBody.setText(response.getString("body"))
                binding.txtMensaje.setText(response.toString())

            }) { error->
            VolleyLog.e("Post","Error".plus(error.message)
            )
        }

    }
}