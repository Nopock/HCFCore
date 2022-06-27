package dev.viva.hcf.teams

import dev.viva.hcf.utils.CC
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.UUID

data class Team(val id: String,
                val name: String,
                val announcement: String,
                val points: Int,
                val balance: Double,
                val dtr: Double,
                val members: List<UUID>,
                val captains: List<UUID>,
                val `co-leaders`: List<UUID>,
                val leader: UUID,
                val ally: String?,
                val lastDiedAt: Long) {

    fun isRaidable(): Boolean {
        return dtr < 0.0
    }


    fun getRawMembers(): List<UUID> {
        val rawMembers: MutableList<UUID> = mutableListOf()

        rawMembers.addAll(members)
        rawMembers.removeIf {
            captains.contains(it) || `co-leaders`.contains(it) || it == leader
        }
        
        return members
    }

    fun sendTeamMessage(message: String) {
        val membersList = arrayListOf<Player>()

        for (member in members) {
            val player = Bukkit.getPlayer(member)

            if (player == null || !player.isOnline) continue

            membersList.add(player)
        }

        membersList.forEach { it.sendMessage(CC.translate(message)) }
    }

    fun isMember(uuid: UUID): Boolean {
        return members.contains(uuid)
    }

    fun isCaptain(uuid: UUID): Boolean {
        return captains.contains(uuid)
    }

    fun isCoLeader(uuid: UUID): Boolean {
        return `co-leaders`.contains(uuid)
    }

    fun isLeader(uuid: UUID): Boolean {
        return uuid == leader
    }
}
