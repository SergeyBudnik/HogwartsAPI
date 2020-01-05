package com.bdev.hogwarts_api.vk

import com.bdev.hogwarts_api.properties.PropertiesConfig
import com.vk.api.sdk.client.TransportClient
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.client.actors.UserActor
import com.vk.api.sdk.httpclient.HttpTransportClient

fun main(args: Array<String>) {
    val transportClient = HttpTransportClient.getInstance()

    val vk = VkApiClient(transportClient)

    val groupId = 162180239
    val groupSecretKey = "28ff9843e5817fff0919fc5f6b1ecfc883d568a7e39a3d0eb5500f69ecf9e0ed04ee253cb4776cccf30d7"

    val groupActor = GroupActor(groupId, groupSecretKey)

    val objectId = vk.utils()
            .resolveScreenName(groupActor, "serge_budnik")
            .execute()
            .objectId

    println("$objectId")
}

class VkMessageSender {
    @Deprecated("VkService")
    fun sendMessage(userId: Int, message: String): Boolean {
        try {
            val transportClient = HttpTransportClient.getInstance()

            val vk = VkApiClient(transportClient)

            val groupId = PropertiesConfig.config.vkGroupId.toInt()
            val groupSecretKey = PropertiesConfig.config.vkGroupSecretKey

            val groupActor = GroupActor(groupId, groupSecretKey)

            vk.users()
                    .get(groupActor)
                    .userIds("serge_budnik")
                    .build()

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
