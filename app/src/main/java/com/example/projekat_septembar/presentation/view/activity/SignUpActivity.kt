package com.example.projekat_septembar.presentation.view.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.projekat_septembar.databinding.ActivitySighUpBinding


class SignUpActivity : AppCompatActivity() {

//    private val signInViewModel: SignInContract.LogInViewModel by viewModel<SignInViewModel>()
    private lateinit var binding: ActivitySighUpBinding

    var username: String = ""
    private var password: String = ""
    private lateinit var save: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySighUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        init()
    }

}