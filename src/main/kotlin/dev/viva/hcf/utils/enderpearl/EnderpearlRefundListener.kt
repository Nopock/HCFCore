package dev.viva.hcf.utils.enderpearl

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.EnderPearl
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.player.PlayerInteractEvent

object EnderpearlRefundListener : Listener {

    @EventHandler
    fun refund(event: PlayerInteractEvent){
        val player: Player = event.player

        if (event.item == null) return

        if (event.item?.type == Material.ENDER_PEARL){
            if (event.isCancelled){
                Bukkit.getPluginManager().callEvent(EnderpearlRefundEvent(player))
            }
        }
    }

    @EventHandler
    fun projectile(event: ProjectileHitEvent){
        if (event.entity.shooter is Player && event.entity is EnderPearl){
            val player: Player = event.entity.shooter as Player

            if (event.isCancelled){
                Bukkit.getPluginManager().callEvent(EnderpearlRefundEvent(player));
            }
        }
    }

}