package br.com.heitor.pluggyheitor.service

import ai.pluggy.client.PluggyClient
import ai.pluggy.client.request.CreateConnectTokenRequest
import ai.pluggy.client.response.Connector
import br.com.heitor.pluggyheitor.models.EventEntity
import br.com.heitor.pluggyheitor.repository.EventEntityRepository
import br.com.heitor.pluggyheitor.requests.EventRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.IOException

@Service
class WidgetService(
    @Value("\${clientId}") private val clientId: String,
    @Value("\${clientSecret}") private val clientSecret: String,
    @Value("\${webhookUrl}") private val webhookUrl: String,
    private val eventEntityRepository: EventEntityRepository,
) {
    fun getWidgetURL(): String {
        val accessToken: String = getAccessToken()
        val widgetURL: String = getWidgetURL(accessToken)

        return widgetURL
    }

    @Throws(IOException::class)
    private fun getAccessToken(): String {
        val pluggyClient = PluggyClient.builder()
            .clientIdAndSecret(clientId, clientSecret)
            .build()

        // Fetch access token
        val createConnectTokenRequest = CreateConnectTokenRequest(webhookUrl, clientId)
        return pluggyClient.service()
            .createConnectToken(createConnectTokenRequest)
            .execute()
            .body()
            ?.getAccessToken() ?: ""
    }

    @Throws(IOException::class)
    private fun getWidgetURL(accessToken: String): String {
        val institutionToConnect = "Banco do Brasil"

        val pluggyClient = PluggyClient.builder()
            .clientIdAndSecret(clientId, clientSecret)
            .build()

        // Fetch connectors
        val connectors = pluggyClient.service()
            .connectors
            .execute()
            .body()
            ?.getResults()

        val institution = connectors!!.stream()
            .filter { connector: Connector? -> institutionToConnect == connector!!.name }
            .findFirst().orElse(null)

        val timestamp = System.currentTimeMillis().toString()

        val str = "https://connect.pluggy.ai/?connect_token=" +
                accessToken +
                "&with_sandbox=true" +
                "&itemId=" +
                institution!!.id +
                "&events=OPEN%2CSUBMITTED_CONSENT%2CSELECTED_INSTITUTION" +
                "&timestamp=" + timestamp +
                "&connectorId=" +
                institution!!.id +
                "&connector_name=" +
                institution!!.name.replace(' ', '+')

        return str
    }

    fun saveEventRequest(eventRequest: EventRequest) {
        val eventEntity = EventEntity(
            itemid = eventRequest.itemId,
            event = eventRequest.event,
            triggeredby = eventRequest.triggeredBy,
            eventid = eventRequest.eventId,
        )
        val savedEvent = eventEntityRepository.save(eventEntity)
        println("Saved event with id: ${savedEvent.id}")
    }
}