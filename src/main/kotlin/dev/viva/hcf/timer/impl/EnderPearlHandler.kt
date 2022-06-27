package dev.viva.hcf.timer.impl

import dev.viva.hcf.timer.PlayerTimer
import dev.viva.hcf.utils.MessageBuilder
import dev.viva.hcf.utils.TimeUtils
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

object EnderPearlHandler : PlayerTimer() {

    val cooldown = hashMapOf<UUID, Long>()

    override fun hasCooldown(player: Player): Boolean {
        return cooldown.containsKey(player.uniqueId) && System.currentTimeMillis() <= cooldown[player.uniqueId]!!
    }


    override fun addCooldown(player: Player) {
        val diff = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(16)
        cooldown[player.uniqueId] = diff
    }

    override fun removeCooldown(player: Player) {
        this.cooldown.remove(player.uniqueId)
    }

    override fun getFormattedCooldown(player: Player): String {
        val diff = cooldown[player.uniqueId]?.minus(System.currentTimeMillis()) ?: return "0:00"

        if (diff <= 0) return "0:00"

        return TimeUtils.formatIntoHHMMSS((diff / 1000L).toInt())
    }

    fun getFormattedCooldownSeconds(player: Player): String {
        val diff = cooldown[player.uniqueId]?.minus(System.currentTimeMillis())
        return ((10.00 * diff!!.toDouble() / 1000.00)).toString() + "s"
    }
}