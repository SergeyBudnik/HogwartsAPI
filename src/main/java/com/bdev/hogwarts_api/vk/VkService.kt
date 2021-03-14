package com.bdev.hogwarts_api.vk

import com.bdev.hogwarts_api.properties.PropertiesConfig
import com.vk.api.sdk.client.VkApiClient
import com.vk.api.sdk.client.actors.GroupActor
import com.vk.api.sdk.httpclient.HttpTransportClient
import org.springframework.stereotype.Service

@Service
open class VkService {
    fun getUserId(screenName: String): Int? {
        return try {
            getVk().utils()
                    .resolveScreenName(getGroupActor(), screenName)
                    .execute()
                    .objectId
        } catch (e: Exception) {
            null
        }
    }

    fun sendMessage(userId: Int, message: String): Boolean {
        return try {
            getVk().messages()
                    .send(getGroupActor())
                    .userId(userId)
                    .message(message)
                    .execute()

            true
        } catch (e: Exception) {
            false
        }
    }

    private fun getVk(): VkApiClient {
        val transportClient = HttpTransportClient.getInstance()

        return VkApiClient(transportClient)
    }

    private fun getGroupActor(): GroupActor {
        val groupId = PropertiesConfig.config.vkGroupId.toInt()
        val groupSecretKey = PropertiesConfig.config.vkGroupSecretKey

        return GroupActor(groupId, groupSecretKey)
    }
}