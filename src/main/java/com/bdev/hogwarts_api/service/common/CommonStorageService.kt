package com.bdev.hogwarts_api.service.common

interface CommonStorageService <Id, NewType, ExistingType> {
    fun getAll(): List<ExistingType>
    fun getById(id: Id): ExistingType?
    fun create(o: NewType): Id
    fun update(o: ExistingType)
    fun delete(id: Id)
}
