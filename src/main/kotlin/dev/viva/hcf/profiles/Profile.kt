package dev.viva.hcf.profiles

import dev.viva.hcf.chat.ChatMode
import dev.viva.hcf.teams.Team
import java.util.UUID

data class Profile(
    val uuid: UUID,
    var lives: Int,
    var balance: Double,
    var faction: String?,
    var kills: Int,
    var deaths: Int,
    var killstreak: Int,
    var bestKillstreak: Int,
    var gems: Int,
    var cobblestonePickup: Boolean,
    var chatMode: ChatMode,
    var goppleCooldown: Long,
    var startingTimer: Long,
    var pvpTimer: Long,
    var deathbanTimer: Long,
    var reclaimed: Boolean,
)
