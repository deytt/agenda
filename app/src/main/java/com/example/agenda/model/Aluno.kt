package com.example.agenda.model

import java.io.Serializable

class Aluno(
    val nome: String,
    val telefone: String,
    val email: String
) : Serializable {
    override fun toString() = nome
}