package br.com.heitor.pluggyheitor.requests

data class EventRequest(
    val itemId: String,
    val event: String,
    val id: String,
    val eventId: String,
    val triggeredBy: String
)