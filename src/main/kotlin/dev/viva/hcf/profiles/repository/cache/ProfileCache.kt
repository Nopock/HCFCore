package dev.viva.hcf.profiles.repository.cache

import dev.viva.hcf.profiles.Profile
import ltd.matrixstudios.duplex.caches.DuplexMongoCache
import java.util.UUID

object ProfileCache : DuplexMongoCache<UUID, Profile>()