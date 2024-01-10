package br.com.heitor.pluggyheitor.repository

import br.com.heitor.pluggyheitor.models.EventEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventEntityRepository : JpaRepository<EventEntity, Long> {
}