package com.example.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.agenda.R
import com.example.agenda.dao.AlunoDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
    }

    override fun onResume() {
        super.onResume()
        buildAlunosListView()
    }

    private fun buildAlunosListView() {
        alunosListView.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            getTodosAlunos()
        )
    }

    private fun getTodosAlunos() = dao.todos()
    private fun setFabListener() = fab.setOnClickListener { openFormularioAlunoActivity() }
    private fun openFormularioAlunoActivity() = startActivity(Intent(this, FormularioAlunoActivity::class.java))
}