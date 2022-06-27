package dev.viva.hcf.teams.repository.cache

import dev.viva.hcf.teams.Team
import ltd.matrixstudios.duplex.caches.DuplexMongoCache
import java.util.UUID

object TeamCache : DuplexMongoCache<String, Team>()