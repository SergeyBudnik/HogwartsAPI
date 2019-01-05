package com.bdev.hogwarts_api.vk

import com.bdev.hogwarts_api.properties.PropertiesConfig
import com.vk.api.sdk.client.TransportClient
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.httpclient.HttpTransportClient

class VkMessageSender {
    fun sendMessage(userId: Int, message: String): Boolean {
        try {
            val transportClient = HttpTransportClient.getInstance()

            val vk = VkApiClient(transportClient)

            val groupId = PropertiesConfig.config.vkGroupId.toInt()
            val groupSecretKey = PropertiesConfig.config.vkGroupSecretKey

            val groupActor = GroupActor(groupId, groupSecretKey)

            vk.messages()
                    .send(groupActor)
                    .userId(userId)
                    .message(message)
                    .execute()

            return true
        } catch (e: Exception) {
            return false
        }
    }
}
