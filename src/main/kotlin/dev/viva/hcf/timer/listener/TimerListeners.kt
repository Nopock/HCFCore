package dev.viva.hcf.timer.listener

import dev.viva.hcf.timer.impl.CrappleListener
import dev.viva.hcf.timer.impl.EnderPearlHandler
import dev.viva.hcf.timer.impl.SpawnTagHandler
import dev.viva.hcf.utils.CC
import org.bukkit.Material
import org.bukkit.entity.EnderPearl
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.ProjectileLaunchEvent
import org.bukkit.event.player.PlayerItemConsumeEvent

class TimerListeners : Listener {

    @EventHandler
    fun enderpearlThrow(event: ProjectileLaunchEvent){
        if (event.entity.shooter !is Player || event.entity !is EnderPearl) return

        val shooter: Player = event.entity.shooter as Player

        if (EnderPearlHandler.hasCooldown(shooter)){
            event.isCancelled = true
            shooter.sendMessage(CC.translate("&cYou cannot use for this another &l" + EnderPearlHandler.getFormattedCooldownSeconds(shooter) + "&c."))
            return
        }
        EnderPearlHandler.addCooldown(shooter)
    }

    @EventHandler
    fun consume(event: PlayerItemConsumeEvent){
        val player = event.player

        if (event.item.type == Material.GOLDEN_APPLE){
            if (!CrappleListener.hasCooldown(player)){
                CrappleListener.addCooldown(player)
                return
            }

            event.isCancelled = true
            player.sendMessage(CC.translate("&cYou cannot use for this another &l" + CrappleListener.getFormattedCooldownSeconds(player) + "&c."))
        }
    }

    @EventHandler
    fun combatTag(event: EntityDamageByEntityEvent){
        if (event.damager !is Player || event.entity !is Player) return

        val damager: Player = event.damager as Player
        val victim: Player = event.entity as Player

        if (!SpawnTagHandler.hasCooldown(damager)){
            damager.sendMessage(CC.translate("&cYou have been spawn tagged for &e60 &cseconds."))
        }
        if (!SpawnTagHandler.hasCooldown(victim)){
            victim.sendMessage(CC.translate("&cYou have been spawn tagged for &e60 &cseconds."))
        }

        SpawnTagHandler.addCooldown(damager)
        SpawnTagHandler.addCooldown(victim)
    }

}