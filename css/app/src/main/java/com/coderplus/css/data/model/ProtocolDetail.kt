package com.coderplus.css.data.model
data class ProtocolDetail(
    val id: String,
    val title: String,
    val heroIconUrl: String,     // tus svg
    val resumen: String,
    val cuandoAplicar: List<String>,
    val pasos: List<String>,
    val haz: List<String>,
    val evita: List<String>,
    val notasUca: List<String> = emptyList(),
    val contactos: List<String> = emptyList()
)