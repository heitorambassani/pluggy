package br.com.heitor.pluggyheitor.controller

import br.com.heitor.pluggyheitor.models.EventEntity
import br.com.heitor.pluggyheitor.requests.EventRequest
import br.com.heitor.pluggyheitor.service.WidgetService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/widget")
class WidgetController(private val widgetService: WidgetService) {
    @GetMapping
    fun getWidgetURL(): String {
        return widgetService.getWidgetURL()
    }

    @PostMapping
    fun storeWebhookInfo(@RequestBody eventRequest: EventRequest): ResponseEntity<String> {
        println("Received event: $eventRequest")

        widgetService.saveEventRequest(eventRequest)

        return ResponseEntity.status(HttpStatus.OK)
            .body("Received and processed the event successfully")
    }
}