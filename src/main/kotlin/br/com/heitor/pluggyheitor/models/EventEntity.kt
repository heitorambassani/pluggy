package br.com.heitor.pluggyheitor.models

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "evententity")
data class EventEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var itemid: String,
    var event: String,
    var eventid: String,
    var triggeredby: String,
    var json_item: String,
)