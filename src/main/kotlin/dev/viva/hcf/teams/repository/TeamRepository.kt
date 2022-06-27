package dev.viva.hcf.teams.repository

import dev.viva.hcf.chat.ChatMode
import dev.viva.hcf.profiles.Profile
import dev.viva.hcf.teams.Team
import dev.viva.hcf.teams.repository.cache.TeamCache
import ltd.matrixstudios.duplex.repository.DuplexMongoRepository
import java.util.UUID

object TeamRepository : DuplexMongoRepository<String, Team>("teams", Team::class.java) {
    fun create(leader: UUID, name: String) {
        val team = Team(name.toLowerCase(), name, "", 0, 0.0, 1.01, ArrayList(), ArrayList(), ArrayList(), leader, "", -1)

        save(name.toLowerCase(), team)

        TeamCache.cache(name.toLowerCase(), team)

    }
}

