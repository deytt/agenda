package com.example.agenda.dao

import com.example.agenda.model.Aluno

class AlunoDAO {
    companion object { val lista = ArrayList<Aluno>() }
    fun salvar(aluno: Aluno) = lista.add(aluno)
    //retornando uma cópia da lista para que ninguém consiga manipular a lista de alunos
    fun todos(): List<Aluno> = ArrayList(lista)
}