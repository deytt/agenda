package com.example.agenda.model

class Aluno(
    val nome: String,
    val telefone: String,
    val email: String
) {
    override fun toString() = nome
}