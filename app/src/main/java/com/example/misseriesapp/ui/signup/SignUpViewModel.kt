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
    val mensaje : MutableLiveData<String> by lazy{
        MutableLiveData<String>()
    }
    fun datos(nombre: String, email: String, password: String, rePassword: String, fecha: String){
        //var mensaje : String;
        var completoCorreo:Boolean=false;
        if(email.contains("@")==true){
            completoCorreo=true;
        }
        if((nombre.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && rePassword.isNotEmpty()) && password==rePassword && completoCorreo){
            textF.value+=nombre+"\n"+email+"\n"+password+"\n"+rePassword+"\n"+fecha+"\n";
            completo.value=true;
        }
        else if(completoCorreo==false){
            mensaje.value="Correo mal escrito"
            completo.value=false;
        }
        else{
            completo.value=false;
            mensaje.value="Debe llenar todos los espacios y confirmar que la contraseña sea igual en ambos espacios";
        }
    }
    fun genres(genre: String){
        if(completo.value==true) {
            textF.value += genre + "\n";
        }
    }
}