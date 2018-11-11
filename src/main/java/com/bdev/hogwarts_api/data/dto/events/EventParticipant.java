package com.bdev.hogwarts_api.data.dto.events;

import com.bdev.hogwarts_api.data.dto.student.StudentReferralSource;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Builder
@Getter
@Setter
public class EventParticipant {
    private Long id;
    @NonNull private Long eventId;
    @NonNull private String name;
    @NonNull private EventParticipantStatus status;
    @NonNull private String phone;
    @NonNull private StudentReferralSource referralSource;
    @NonNull private Boolean enlisted;

    @JsonCreator
    public static EventParticipant create(
            @JsonProperty("id") Long id,
            @JsonProperty("eventId") Long eventId,
            @JsonProperty("name") String name,
            @JsonProperty("status") EventParticipantStatus status,
            @JsonProperty("phone") String phone,
            @JsonProperty("referralSource") StudentReferralSource referralSource,
            @JsonProperty("enlisted") Boolean enlisted
    ) {
        return EventParticipant
                .builder()
                .id(id)
                .eventId(eventId)
                .name(name)
                .status(status)
                .phone(phone)
                .referralSource(referralSource)
                .enlisted(enlisted)
                .build();
    }
}
