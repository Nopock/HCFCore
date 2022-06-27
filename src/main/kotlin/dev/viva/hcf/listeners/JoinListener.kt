package dev.viva.hcf.listeners

import dev.viva.hcf.profiles.Profile
import dev.viva.hcf.profiles.repository.cache.ProfileCache
import dev.viva.hcf.server.ServerHandler
import dev.viva.hcf.utils.ItemBuilder
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import java.util.concurrent.TimeUnit

object JoinListener : Listener {

    @EventHandler
    fun join(event: PlayerJoinEvent){
        val player = event.player

        if (!player.hasPlayedBefore()){
            val profile: Profile = ProfileCache.cache[player.uniqueId] ?: return

            profile.balance = 250.0
            if (ServerHandler.sotw <= 0){
                profile.startingTimer = TimeUnit.HOURS.toMillis(1)
            }
            giveStarterItems(player)
        }
    }

    private fun giveStarterItems(player: Player){
        player.inventory.addItem(ItemBuilder.of(Material.FISHING_ROD).enchant(Enchantment.DURABILITY, 3).build())
        player.inventory.addItem(ItemBuilder.of(Material.COOKED_BEEF).amount(16).build())
    }

}