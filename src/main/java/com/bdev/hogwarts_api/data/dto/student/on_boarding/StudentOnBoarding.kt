package com.bdev.hogwarts_api.data.dto.student.on_boarding

import com.bdev.hogwarts_api.data.dto.education.EducationInfo
import com.bdev.hogwarts_api.data.dto.person.Person
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class NewStudentOnBoarding @JsonCreator constructor(
        @JsonProperty("info") val info: StudentOnBoardingInfo,
        @JsonProperty("action") val action: NewStudentOnBoardingAction
)

data class ExistingStudentOnBoarding @JsonCreator constructor(
        @JsonProperty("info") val info: StudentOnBoardingInfo,
        @JsonProperty("actions") val actions: List<ExistingStudentOnBoardingAction>,
        @JsonProperty("result") val result: StudentOnBoardingResult
)

data class StudentOnBoardingInfo @JsonCreator constructor(
        @JsonProperty("login") val login: String,
        @JsonProperty("person") val person: Person,
        @JsonProperty("educationInfo") val educationInfo: EducationInfo,
        @JsonProperty("managerLogin") val managerLogin: String
)

data class StudentOnBoardingResult @JsonCreator constructor(
        @JsonProperty("type") val type: StudentOnBoardingResultType,
        @JsonProperty("comment") val comment: String
)

enum class StudentOnBoardingResultType(val id: String) {
        PROGRESS("PROGRESS"),
        ON_BOARDED("ON_BOARDED"),
        CANCELED("CANCELED");

        companion object {
                fun fromId(id: String): StudentOnBoardingResultType? {
                        return values().find { it.id == id }
                }
        }
}

data class NewStudentOnBoardingAction @JsonCreator constructor(
        @JsonProperty("info") val info: StudentOnBoardingActionInfo
)

data class ExistingStudentOnBoardingAction @JsonCreator constructor(
        @JsonProperty("info") val info: StudentOnBoardingActionInfo,
        @JsonProperty("creationTime") val creationTime: Long,
        @JsonProperty("completionTime") val completionTime: Long?
)

data class StudentOnBoardingActionInfo @JsonCreator constructor(
        @JsonProperty("assigneeLogin") val assigneeLogin: String,
        @JsonProperty("actionTime") val actionTime: Long,
        @JsonProperty("description") val description: String
)
