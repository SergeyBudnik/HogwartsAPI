package com.bdev.hogwarts_api.service.student_onboarding

import com.bdev.hogwarts_api.data.dto.staff.StaffMember
import com.bdev.hogwarts_api.data.dto.student.on_boarding.NewStudentOnBoardingAction
import com.bdev.hogwarts_api.data.dto.student.on_boarding.StudentOnBoardingInfo
import com.bdev.hogwarts_api.service.staff.StaffMemberStorageService
import com.bdev.hogwarts_api.vk.VkService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

interface StudentOnBoardingNotificationsService {
    fun notifyAction(info: StudentOnBoardingInfo, action: NewStudentOnBoardingAction)
}

@Service
open class StudentOnBoardingNotificationsServiceImpl @Autowired constructor(
        private val staffMemberStorageService: StaffMemberStorageService,
        private val vkService: VkService
) : StudentOnBoardingNotificationsService {
    override fun notifyAction(info: StudentOnBoardingInfo, action: NewStudentOnBoardingAction) {
        val manager = staffMemberStorageService.getStaffMember(info.managerLogin)
        val assignee = staffMemberStorageService.getStaffMember(action.info.assigneeLogin)

        val message = getMessage(
                info = info,
                action = action,
                assignee = assignee,
                manager = manager
        )

        manager?.person?.contacts?.vkLinks?.forEach { vkLink ->
            sendMessageToVkLink(message = message, vkLinkValue = vkLink.value)
        }

        assignee?.person?.contacts?.vkLinks?.forEach { vkLink ->
            sendMessageToVkLink(message = message, vkLinkValue = vkLink.value)
        }
    }

    private fun sendMessageToVkLink(message: String, vkLinkValue: String) {
        vkService.getUserId(vkLinkValue)?.let { userId ->
            vkService.sendMessage(userId = userId, message = message)
        }
    }

    private fun getMessage(
            info: StudentOnBoardingInfo,
            action: NewStudentOnBoardingAction,
            assignee: StaffMember?,
            manager: StaffMember?
    ): String {
        val sdf = SimpleDateFormat("dd.MM.yyyy HH:mm")

        sdf.timeZone = TimeZone.getTimeZone("Europe/Moscow")

        var message = "Назначена задача:\n"

        message += "1. Описание: ${action.info.description}\n"
        message += "2. Когда: ${sdf.format(Date(action.info.actionTime))}\n"

        message += "3. Ответственные:\n" +
                ">>> Исполнитель: ${assignee?.person?.name ?: action.info.assigneeLogin}\n" +
                ">>> Менеджер задачи: ${manager?.person?.name ?: info.managerLogin}\n"

        message += "4. Информация о студенте:\n" +
                ">>> Имя: ${info.person.name}\n" +
                ">>> Уровень: ${info.educationInfo.level}\n" +
                ">>> Возраст: ${info.educationInfo.age}\n"

        message += ">>> Телефоны:\n"

        info.person.contacts.phones.forEach { phone -> message += ">>>>>> ${phone.name} : ${phone.value}\n" }

        message += ">>> Ссылки VK:\n"

        info.person.contacts.vkLinks.forEach { vkLink -> message += ">>>>>> ${vkLink.name} : https://vk.com/${vkLink.value}\n" }

        return message
    }
}
