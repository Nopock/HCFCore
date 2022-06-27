package dev.viva.hcf.chat

import dev.viva.hcf.profiles.Profile
import dev.viva.hcf.profiles.repository.ProfileRepository
import dev.viva.hcf.profiles.repository.cache.ProfileCache
import dev.viva.hcf.teams.Team
import dev.viva.hcf.teams.repository.TeamRepository
import dev.viva.hcf.teams.repository.cache.TeamCache
import dev.viva.hcf.utils.CC
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.util.stream.Collectors

object ChatListener : Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    fun chat(event: AsyncPlayerChatEvent){
        val player: Player = event.player
        val profile = ProfileCache.cache[player.uniqueId]!!

        val team: Team? = TeamCache.cache[ProfileCache.cache[player.uniqueId]?.faction]
        val chatMode: ChatMode = profile.chatMode

        if (event.isCancelled && chatMode == ChatMode.PUBLIC) return

        event.isCancelled = true

        if (chatMode != ChatMode.PUBLIC && team == null) {
            player.sendMessage(CC.translate("&cYou cannot speak in a non-public channel if you're not on a team."))
            return
        }

        if (chatMode != ChatMode.PUBLIC && team != null){
            when (chatMode) {
                ChatMode.CAPTAIN -> {
                    if (!team.isCaptain(player.uniqueId) && !team.isCoLeader(player.uniqueId) && !team.isLeader(player.uniqueId)){
                        player.sendMessage(CC.translate("&cYou must be at least Captain to talk in this chat."))
                        return
                    }
                }
                ChatMode.LEADER -> {
                    if (!team.isCoLeader(player.uniqueId) && !team.isLeader(player.uniqueId)){
                        player.sendMessage(CC.translate("&cYou must be at least Co-Leader to talk in this chat."))
                        return
                    }
                }
                else -> {

                }
            }
        }

        // Now we actually put our message together!
        when (chatMode){
            ChatMode.PUBLIC -> {
                val publicChat = publicChatFormat(team, player)
                val chatMessage = String.format(publicChat, event.message)

                for (member in Bukkit.getOnlinePlayers()){
                    val memberTeam = TeamCache.cache[ProfileCache.cache[member.uniqueId]?.faction]

                    if (team != null && memberTeam != null){
                        if (memberTeam == team){
                            member.sendMessage(CC.translate(chatMessage.replace(ChatColor.YELLOW.toString(), ChatColor.DARK_GREEN.toString(), false)))
                        } else {
                            member.sendMessage(CC.translate(chatMessage))
                        }
                    }
                }
            }

            ChatMode.FACTION -> {
                team?.sendTeamMessage("&a(Team) " + player.name + ": " + event.message)
            }

            ChatMode.CAPTAIN -> {
                team?.sendTeamMessage("&b(Officer) " + player.name + ": " + event.message)
            }

            ChatMode.LEADER -> {
                team?.sendTeamMessage("&e(Leader) " + player.name + ": " + event.message)
            }

            ChatMode.ALLY -> {
                team?.sendTeamMessage("&d(Ally) " + player.name + ": " + event.message)
                if (team?.ally != null){
                    val allyTeam = TeamRepository.byKey(team.ally)

                    allyTeam?.sendTeamMessage("&d(Ally) " + player.name + ": " + event.message)

                }
            }
        }
    }

    private fun publicChatFormat(team: Team?, player: Player): String {
        var starting = ""

        if (team != null){
            starting = "&6[" + ChatColor.YELLOW + team.name + "&6] "
        }

        return starting + player.displayName + "&7: &r%s"
    }



}