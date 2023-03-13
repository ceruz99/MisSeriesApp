package com.example.misseriesapp.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.misseriesapp.R
import com.example.misseriesapp.databinding.ActivitySignUpBinding
import com.example.misseriesapp.ui.main.MainActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBinding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding=ActivitySignUpBinding.inflate(layoutInflater)
        val view= signUpBinding.root
        setContentView(view)
        signUpBinding.registerButton.setOnClickListener{
            Log.d("saludito","hola")
            var nombre:String=signUpBinding.nameEditText.text.toString()
            var email:String=signUpBinding.emailEditText.text.toString()
            var password:String=signUpBinding.passwordEditText.text.toString()
            var repPassword:String=signUpBinding.reppasswordEditText.text.toString()
            val info="Nombre: $nombre\nEmail: $email\nPassword: $password"


        }
    }
}