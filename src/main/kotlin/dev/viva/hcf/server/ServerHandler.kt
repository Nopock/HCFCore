package dev.viva.hcf.server

import dev.viva.hcf.HCF
import dev.viva.hcf.chat.ChatListener
import dev.viva.hcf.profiles.Profile
import dev.viva.hcf.profiles.repository.ProfileRepository
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player
import java.util.stream.Collectors
import kotlin.math.abs

object ServerHandler {

    var WARZONE_BORDER: Int = HCF.instance.config.getInt("server.warzone.warzone-border")
    var WARZONE_RADIUS: Int = HCF.instance.config.getInt("server.warzone.warzone-radius")

    var NETHER_BUFFER: Int = HCF.instance.config.getInt("server.nether-buffer")

    var sotw: Long = 0
    var eotw: Boolean = false
    var maxFactionSize: Int = HCF.instance.config.getInt("server.maxFactionSize")
    var maxAllies: Int = HCF.instance.config.getInt("server.maxAllies")


    fun isWarzone(loc: Location): Boolean {
        if (loc.world?.environment !== World.Environment.NORMAL) {
            return false
        }

        return abs(loc.blockX) <= WARZONE_RADIUS && abs(loc.blockZ) <= WARZONE_RADIUS || abs(
            loc.blockX
        ) > WARZONE_BORDER || abs(loc.getBlockZ()) > WARZONE_RADIUS
    }

    fun isNetherBufferZone(loc: Location): Boolean {
        if (loc.world!!.environment != World.Environment.NETHER) {
            return false
        }

        val radius: Int = NETHER_BUFFER
        val x = loc.blockX
        val z = loc.blockZ

        return x < radius && x > -radius && z < radius && z > -radius
    }


    private fun calculateKillsTop(): List<Player> {
        val list = ProfileRepository.findAll()
            .stream()
            .collect(Collectors.toList())

        list.sortBy { it.kills }

        val playersList = arrayListOf<Player>()

        val first = list[0]
        val second = list[1]
        val third = list[2]

        if (first != null){
            getPlayer(first)?.let { playersList.add(it) }
        }
        if (second != null){
            getPlayer(second)?.let { playersList.add(it) }
        }
        if (third != null){
            getPlayer(third)?.let { playersList.add(it) }
        }

        return playersList
    }


    private fun getPlayer(profile: Profile): Player? {
        return Bukkit.getPlayer(profile.uuid)
    }

}