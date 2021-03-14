package com.bdev.hogwarts_api.rest.admin

import com.bdev.hogwarts_api.rest.CommonRest
import com.bdev.hogwarts_api.rest.common.CommonCRUDRest
import com.bdev.hogwarts_api.rest_service.common.CommonCRUDRestService

abstract class AdminCommonCRUDRest <Id, Type> constructor(
        private val commonCRUDRestService: CommonCRUDRestService<Id, Type>
): CommonCRUDRest<Id, Type>, CommonRest() {
    override fun getAll(authToken: String) = commonCRUDRestService.getAll(userInfo = getUserInfo(authToken))
    override fun getById(authToken: String, id: Id) = commonCRUDRestService.getById(userInfo = getUserInfo(authToken), id = id)
    override fun create(authToken: String, o: Type) = commonCRUDRestService.create(userInfo = getUserInfo(authToken), o = o)
    override fun update(authToken: String, o: Type) = commonCRUDRestService.update(userInfo = getUserInfo(authToken), o = o)
    override fun delete(authToken: String, id: Id) = commonCRUDRestService.delete(userInfo = getUserInfo(authToken), id = id)
}
