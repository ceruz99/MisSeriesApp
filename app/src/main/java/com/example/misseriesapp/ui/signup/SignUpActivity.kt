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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.misseriesapp.R
import com.example.misseriesapp.databinding.ActivitySignUpBinding
import com.example.misseriesapp.ui.main.MainActivity
import com.example.misseriesapp.ui.main.MainViewModel
import com.google.android.material.snackbar.Snackbar

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBinding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel
    var date_edit_text: EditText? = null;
    var date_button: Button? = null;
    var dpFecha: DatePicker? = null;
    var selectedCity : String="Medellin";
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        signUpViewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        val view = signUpBinding.root
        setContentView(view)

        //Observers
        val datosObserver = Observer<String>{textF->
            signUpBinding.finalTextview.text=textF
        }
        signUpViewModel.textF.observe(this,datosObserver)

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
            signUpViewModel.datos(signUpBinding.nameEditText.text.toString(),signUpBinding.emailEditText.text.toString(), signUpBinding.passwordEditText.text.toString(),signUpBinding.reppasswordEditText.text.toString(),signUpBinding.dpFecha.dayOfMonth.toString()+"/"+(signUpBinding.dpFecha.month+1).toString()+"/"+signUpBinding.dpFecha.year)
            var genre:String;
            if(signUpBinding.maleRadioButton.isChecked){
                signUpViewModel.genres("male")
            }
            else{
                signUpViewModel.genres("female")
            }
            var snackbar=Snackbar.make(view, "Debe llenar todos los campos y la contraseña debe ser igual en ambos campos", Snackbar.LENGTH_LONG)
            if(signUpViewModel.completo.value==false){
                snackbar.show();
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