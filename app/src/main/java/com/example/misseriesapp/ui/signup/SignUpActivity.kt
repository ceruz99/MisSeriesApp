package com.example.misseriesapp.ui.signup

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.misseriesapp.R
import com.example.misseriesapp.databinding.ActivitySignUpBinding
import com.example.misseriesapp.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBinding: ActivitySignUpBinding
    var date_edit_text: EditText? = null;
    var date_button: Button? = null;
    var dpFecha: DatePicker? = null;
    var selectedCity : String="Medellin";
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = signUpBinding.root
        setContentView(view)

        date_edit_text = findViewById(R.id.date_edit_text);
        date_button = findViewById(R.id.date_button);
        dpFecha = findViewById(R.id.dpFecha);

        date_edit_text?.setText(getFechaDatePicker())

        dpFecha?.setOnDateChangedListener{
            dpFecha,anio,mes,dia->
            date_edit_text?.setText(getFechaDatePicker())
            dpFecha?.visibility=View.GONE
        }

        //Configurando el spinner
        val cities=resources.getStringArray(R.array.ciudades);
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,cities)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        signUpBinding.originSpinner.adapter=adapter;
        signUpBinding.originSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCity=cities[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        signUpBinding.registerButton.setOnClickListener {
            var nombre: String = signUpBinding.nameEditText.text.toString()
            var email: String = signUpBinding.emailEditText.text.toString()
            var password: String = signUpBinding.passwordEditText.text.toString()
            var repPassword: String = signUpBinding.reppasswordEditText.text.toString()
            var genre:String;
            if(signUpBinding.maleRadioButton.isSelected){
                genre = "male";
            }
            else{
                genre = "female";
            }
            val hobbies: Array<String> = arrayOf();
            if(signUpBinding.crimeCheckbox.isChecked){
                hobbies.plus("crime");
            }
            if(signUpBinding.thrillerCheckbox.isChecked){
                hobbies.plus("thriller");
            }
            if(signUpBinding.dramaCheckbox.isChecked){
                hobbies.plus("drama");
            }
            if(signUpBinding.otherCheckbox.isChecked){
                hobbies.plus("others");
            }
            var fecha: String = signUpBinding.dpFecha.dayOfMonth.toString()+"/"+(signUpBinding.dpFecha.month+1).toString()+"/"+signUpBinding.dpFecha.year;
            //val info = "Nombre: $nombre\nEmail: $email\nPassword: $password"
            var completo: Boolean=validarCampos(nombre,email,password,repPassword);
            var snackbar=Snackbar.make(view, "Debe llenar todos los campos", Snackbar.LENGTH_LONG)
            var snackbar2=Snackbar.make(view, "La contraseña no es igual en los dos campos", Snackbar.LENGTH_LONG)
            if(!completo){
                snackbar.show();
            }
            if(password!=repPassword){
                snackbar2.show();
            }
            else{
                var textF: String = nombre+"\n"+email+"\n"+password+"\n"+genre+"\n";
                for(i in hobbies){
                    textF+=i+" ";
                }
                textF+="\n"+fecha+"\n"+selectedCity;
                signUpBinding.finalEditText.setText(textF);
            }

        }
    }

    //Función para obtener la fecha del date picker
    fun getFechaDatePicker():String{
        var dia=dpFecha?.dayOfMonth.toString().padStart(length = 2, padChar = '0');
        var mes=(dpFecha!!.month+1).toString().padStart(length = 2, padChar = '0');
        var anio=dpFecha?.year.toString().padStart(length = 4, padChar = '0')
        return dia+"/"+mes+"/"+anio
    }

    //Función para mostrar calendario al dar click en el botón
    fun showCalendar(view: View){
        dpFecha?.visibility=View.VISIBLE
    }
    fun validarCampos(nombre_:String,email_:String,password_:String,rePassword_:String):Boolean{
        if(nombre_.isEmpty()){
            return false
        }
        else if(email_.isEmpty()){
            return false
        }
        else if(password_.isEmpty()){
            return false
        }
        else if(rePassword_.isEmpty()){
            return false
        }
        else{
            return true
        }
    }
}