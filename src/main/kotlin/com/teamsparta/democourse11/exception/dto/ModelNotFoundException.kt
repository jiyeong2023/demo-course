package com.teamsparta.democourse11.exception.dto

data class ModelNotFoundException(val modelName: String, val id: Long) :RuntimeException(
    "Model $modelName not found with given id: $id"
)
