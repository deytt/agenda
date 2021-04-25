package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.dao.AlunoDAO
import com.example.agenda.model.Aluno
import com.google.android.material.floatingactionbutton.FloatingActionButton

const val TAG = "ListaAlunosActivity"

class ListaAlunosActivity : AppCompatActivity() {
    private val TITLE_APPBAR = "Agenda de Alunos"
    //TODO trocar de lazy para lateinit var
    private val fab by lazy { findViewById<FloatingActionButton>(R.id.activity_main_fab_add) }
    private val alunosListView by lazy { findViewById<ListView>(R.id.activity_lista_alunos_listview) }
    private val dao = AlunoDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = TITLE_APPBAR
        setContentView(R.layout.activity_lista_alunos)
        setFabListener()

        dao.salvar(Aluno("David", "3256-7751", "david.cerqueira10@outlook.com"))
        dao.salvar(Aluno("Crístian", "3256-7751", "david.cerqueira10@outlook.com"))
        dao.salvar(Aluno("Alecrim", "3256-7751", "david.cerqueira10@outlook.com"))
        dao.salvar(Aluno("Cerqueira", "3256-7751", "david.cerqueira10@outlook.com"))
    }

    override fun onResume() {
        super.onResume()
        buildAlunosListView()
    }

    private fun buildAlunosListView() {
        val alunos = getTodosAlunos()
        alunosListView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            alunos
        )

        alunosListView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, posicao, id ->
            val alunoSelecionado = alunos[posicao]
            val intent = Intent(this, FormularioAlunoActivity::class.java).putExtra("AlunoSelecionado", alunoSelecionado)
            openFormularioAlunoActivity(intent)
        }
    }

    private fun getTodosAlunos() = dao.todos()
    private fun setFabListener() = fab.setOnClickListener { openFormularioAlunoActivity() }
    private fun openFormularioAlunoActivity(intent: Intent = Intent(this, FormularioAlunoActivity::class.java)) = startActivity(intent)
}