package me.riseremi.core.service

/**
 * @author Vitalii Dmitriev
 * @since 25.11.2018
 */
data class Resource(
        val name: String,
        val path: String,
        val type: ResourceManager.ResourceType
)