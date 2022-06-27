package dev.viva.hcf.profiles.repository

import dev.viva.hcf.chat.ChatMode
import dev.viva.hcf.profiles.Profile
import dev.viva.hcf.profiles.repository.cache.ProfileCache
import ltd.matrixstudios.duplex.repository.DuplexMongoRepository
import java.util.UUID

object ProfileRepository : DuplexMongoRepository<UUID, Profile>("profiles", Profile::class.java) {
    fun create(uuid: UUID) {
        val profile = Profile(uuid, 0, 0.0, null, 0, 0, 0, 0, 0, false, ChatMode.PUBLIC, -1, -1, -1, -1, false)

        save(uuid, profile)

        ProfileCache.cache(uuid, profile)
    }
}

