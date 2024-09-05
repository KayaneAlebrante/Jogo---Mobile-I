package com.example.jogo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.jogo.databinding.ActivityPlayBinding
import kotlin.random.Random

class PlayActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityPlayBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPlayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonEnviar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val escolhaUsuario = binding.editEscolha.text.toString()

        if (escolhaUsuario.isNotEmpty()) {
            val escolhaUsuarioInt = escolhaUsuario.toIntOrNull()

            if (escolhaUsuarioInt != null && escolhaUsuarioInt in 1..3) {
                jogar(escolhaUsuarioInt)
            } else {
                Toast.makeText(this, "Valor Invalido, insira 1, 2 ou 3.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Por favor, insira sua escolha.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun jogar(escolhaUsuario: Int) {
        val escolhaApp = Random.nextInt(1, 4)

        atualizarIcones(escolhaUsuario, escolhaApp)

        val resultado = when {
            escolhaUsuario == escolhaApp -> "Empate!"
            (escolhaUsuario == 1 && escolhaApp == 3) ||
                    (escolhaUsuario == 2 && escolhaApp == 1) ||
                    (escolhaUsuario == 3 && escolhaApp == 2) -> "Você venceu!"
            else -> "Você perdeu!"
        }
        binding.textResultado.text = resultado
    }

    private fun atualizarIcones(escolhaUsuario: Int, escolhaApp: Int) {
        val iconUsuario = when (escolhaUsuario) {
            1 -> R.drawable.ic_pedra
            2 -> R.drawable.ic_papel
            3 -> R.drawable.ic_tesoura
            else -> R.drawable.ic_default
        }
        binding.iconEscolhaUsuario.setImageResource(iconUsuario)

        val iconApp = when (escolhaApp) {
            1 -> R.drawable.ic_pedra
            2 -> R.drawable.ic_papel
            3 -> R.drawable.ic_tesoura
            else -> R.drawable.ic_default
        }
        binding.iconEscolhaApp.setImageResource(iconApp)
    }
}