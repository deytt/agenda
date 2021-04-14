package com.example.agenda.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.agenda.R
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.Aluno

class FormularioAlunoActivity : AppCompatActivity() {
    private val TITULO_APPBAR = "Cadastro Aluno(a)"
    //TODO trocar de by lazy para lateinit var
    private val nomeEditText by lazy { findViewById<EditText>(R.id.activity_formulario_aluno_nome) }
    private val telefoneEditText  by lazy { findViewById<EditText>(R.id.activity_formulario_aluno_telefone) }
    private val emailEditText by lazy { findViewById<EditText>(R.id.activity_formulario_aluno_e_mail) }
    private val botaoSalvar by lazy { findViewById<Button>(R.id.activity_formulario_aluno_botao_salvar) }
    private val dao = AlunoDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)
        title = TITULO_APPBAR
        setBotaoSalvarListener()
    }

    private fun salvarAluno(alunoCriado: Aluno) = dao.salvar(alunoCriado)

    private fun createAluno(): Aluno {
        val nome = nomeEditText.text.toString()
        val telefone = telefoneEditText.text.toString()
        val email = emailEditText.text.toString()

        return Aluno(nome, telefone, email)
    }

    private fun setBotaoSalvarListener() {
        botaoSalvar.setOnClickListener {
            val alunoCriado = createAluno()
            salvarAluno(alunoCriado)
            notifyAlunoCriadoFunc(alunoCriado)
            finish()
        }
    }

    private val notifyAlunoCriadoFunc: (alunoCriado: Aluno) -> Unit = {
        val msg = "Aluno(a) ${it.nome} criado."
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}