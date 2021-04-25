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
    //TODO trocar de by lazy para lateinit var
    lateinit var nomeEditText: EditText
    lateinit var telefoneEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var botaoSalvar: Button
    private val dao = AlunoDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)
        title = resources.getString(R.string.TITULO_APPBAR)
        initializeComponents()
        setBotaoSalvarListener()

        val aluno = intent.getSerializableExtra("AlunoSelecionado") as Aluno
        nomeEditText.setText(aluno.nome)
        telefoneEditText.setText(aluno.telefone)
        emailEditText.setText(aluno.email)
    }

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

    private fun initializeComponents() {
        nomeEditText = findViewById(R.id.activity_formulario_aluno_nome)
        telefoneEditText = findViewById(R.id.activity_formulario_aluno_telefone)
        emailEditText = findViewById(R.id.activity_formulario_aluno_e_mail)
        botaoSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar)
    }

    private val notifyAlunoCriadoFunc: (alunoCriado: Aluno) -> Unit = {
        val msg = "Aluno(a) ${it.nome} criado."
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun salvarAluno(alunoCriado: Aluno) = dao.salvar(alunoCriado)
}