package com.example.misseriesapp.data

import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

class UserRepository {
    private var auth: FirebaseAuth= Firebase.auth
    fun signUpUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
            println("funcionó papá")
        }
    }

}