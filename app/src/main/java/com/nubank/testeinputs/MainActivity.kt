package com.nubank.testeinputs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.nubank.testeinputs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        clickBotao()
    }

    fun clickBotao() {
        binding.btnConfirm.setOnClickListener {
            var nome = binding.editTextNome.text.toString()
            var senha = binding.editTextSenha.text.toString()

            when {
                nome.isEmpty() -> {
                    binding.editTextNome.error = "Preencha o nome!!"
                }

                senha.isEmpty() -> {
                    binding.editTextSenha.error = "Preencha a senha!!"
                }

                senha.length <= 3 -> {
                    val snackbar = Snackbar.make(it, "Senha deve ser maior que 3 caracteres!!", Snackbar.LENGTH_SHORT)
                    snackbar.show();
                }
                else -> {
                    login(it)
                }
            }
        }
    }

    fun login(view: View) {
        binding.loading.visibility = View.VISIBLE
        binding.btnConfirm.isEnabled = false

        Handler(Looper.getMainLooper()).postDelayed({
            navegarTela()
            val snackbar = Snackbar.make(view, "Login efetuado!!", Snackbar.LENGTH_SHORT)
            snackbar.show();
        }, 3000)
    }

    fun navegarTela() {
        val intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }
}