package com.bdev.hogwarts_api.vk;

import com.bdev.hogwarts_api.properties.PropertiesConfig;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;

public class VkMessageSender {
    public boolean sendMessage(int userId, String message) {
        try {
            TransportClient transportClient = HttpTransportClient.getInstance();

            VkApiClient vk = new VkApiClient(transportClient);

            int groupId = (int) PropertiesConfig.config.getVkGroupId();
            String groupSecretKey = PropertiesConfig.config.getVkGroupSecretKey();

            GroupActor groupActor = new GroupActor(groupId, groupSecretKey);

            vk.messages()
                    .send(groupActor)
                    .userId(userId)
                    .message(message)
                    .execute();

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
