package dev.viva.hcf.profiles.listener

import dev.viva.hcf.profiles.repository.ProfileRepository
import dev.viva.hcf.profiles.repository.cache.ProfileCache
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerPreLoginEvent

class ProfileListener : Listener {

    @EventHandler
    fun onJoin(player: AsyncPlayerPreLoginEvent) {
        if (ProfileCache.isCached(player.uniqueId)) return

        if (ProfileRepository.byKey(player.uniqueId) == null) {
            ProfileRepository.create(player.uniqueId)
            return;
       }

        ProfileRepository.byKey(player.uniqueId)?.let { ProfileCache.cache(player.uniqueId, it) }
    }
}