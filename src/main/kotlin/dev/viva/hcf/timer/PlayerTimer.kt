package dev.viva.hcf.timer

import org.bukkit.entity.Player

abstract class PlayerTimer {

    abstract fun hasCooldown(player: Player) : Boolean
    abstract fun addCooldown(player: Player)
    abstract fun removeCooldown(player: Player)
    abstract fun getFormattedCooldown(player: Player) : String



}