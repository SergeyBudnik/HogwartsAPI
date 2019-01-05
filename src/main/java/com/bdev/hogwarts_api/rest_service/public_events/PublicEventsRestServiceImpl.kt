package com.bdev.hogwarts_api.rest_service.public_events

import com.bdev.hogwarts_api.data.dto.events.Event
import com.bdev.hogwarts_api.data.dto.events.EventParticipant
import com.bdev.hogwarts_api.data.dto.events.EventType
import com.bdev.hogwarts_api.exceptions.http.HttpEntityNotFoundException
import com.bdev.hogwarts_api.service.event_participant.EventParticipantService
import com.bdev.hogwarts_api.service.events.EventService
import com.bdev.hogwarts_api.vk.VkMessageSender
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.Arrays

@Service
open class PublicEventsRestServiceImpl : PublicEventsRestService {
    @Autowired
    private lateinit var eventService: EventService
    @Autowired
    private lateinit var eventParticipantService: EventParticipantService

    @Transactional(readOnly = true)
    override fun getCurrentEvent(eventType: EventType): Event {
        return eventService
                .getLatestEvent(eventType)
                ?: throw HttpEntityNotFoundException("")
    }

    @Transactional
    override fun addEventParticipant(eventParticipant: EventParticipant) {
        if (!eventService.exists(eventParticipant.eventId)) {
            throw HttpEntityNotFoundException("")
        }

        eventParticipantService.createParticipant(eventParticipant)

        val event = eventService
                .getById(eventParticipant.eventId)
                ?: throw HttpEntityNotFoundException("")

        val message = "Новая заявка на " + event.name + ".\n" +
                "Студент: " + eventParticipant.name + "\n" +
                "Телефон: " + eventParticipant.phone + "\n" +
                "VK: ?\n" +
                "Подробнее: http://hogwarts-engschool.ru/admin/#/events/" + event.id + "/participants"

        val adminIds = Arrays.asList(23236615, 30026296)

        adminIds.forEach { adminId -> VkMessageSender().sendMessage(adminId!!, message) }
    }
}
