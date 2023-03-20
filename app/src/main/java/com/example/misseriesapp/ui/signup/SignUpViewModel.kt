package com.example.misseriesapp.ui.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.misseriesapp.databinding.ActivitySignUpBinding

class SignUpViewModel : ViewModel(){
    val textF : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val completo : MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    fun datos(nombre: String, email: String, password: String, rePassword: String, fecha: String){
        if((nombre.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && rePassword.isNotEmpty()) && password==rePassword){
            textF.value+=nombre+"\n"+email+"\n"+password+"\n"+rePassword+"\n"+fecha+"\n";
            completo.value=true;
        }
        else{
            completo.value=false;
        }
    }
    fun genres(genre: String){
        if(completo.value==true) {
            textF.value += genre + "\n";
        }
    }
}