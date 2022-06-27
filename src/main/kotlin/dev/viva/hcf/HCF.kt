package dev.viva.hcf

import dev.viva.hcf.profiles.Profile
import dev.viva.hcf.profiles.repository.cache.ProfileCache
import dev.viva.hcf.timer.impl.EnderPearlHandler
import dev.viva.hcf.timer.impl.SpawnTagHandler
import ltd.matrixstudios.duplex.DuplexMongoManager
import dev.viva.hcf.utils.CC
import dev.viva.hcf.utils.TimeUtils
import io.github.devrawr.scoreboards.ScoreboardContext
import io.github.devrawr.scoreboards.Scoreboards
import org.bukkit.plugin.java.JavaPlugin

class HCF : JavaPlugin() {

    companion object {
        lateinit var instance: HCF
    }

    //NOTE: Cache all teams on load.
    //Also, when the plugin disables, flush to mongo

    override fun onEnable() {
        saveDefaultConfig()

        instance = this

        DuplexMongoManager.start("mongodb://localhost:27017", "hcf")

        setupScoreboard()
    }

    private fun setupScoreboard(){
        Scoreboards
            .registerOnJoin { player ->
                val context = ScoreboardContext(player)

                val profile: Profile = ProfileCache.cache[player.uniqueId] ?: return@registerOnJoin context

                context.title(CC.translate("&6&lElevern &c[Map 1]"))

                context.add(CC.translate("&7&m-------------------------------------"))
                context.add(CC.translate("testing"))
                if (SpawnTagHandler.hasCooldown(player)){
                    context.repeating()
                        .cooldown(20L)
                        .handle {
                            CC.translate("&c&lSpawn Tag&7: &c" + SpawnTagHandler.getFormattedCooldown(player))
                        }.create()
                }

                if (EnderPearlHandler.hasCooldown(player)){
                    context.repeating()
                        .cooldown(20L)
                        .handle {
                            CC.translate("&9&lEnderpearl&7: &c" + EnderPearlHandler.getFormattedCooldown(player))
                        }.create()
                }

                if (profile.startingTimer > 0){
                    context.repeating()
                        .cooldown(20L)
                        .handle {
                            CC.translate("&a&lStarting Timer&7: &c" + TimeUtils.formatLongIntoHHMMSS(profile.startingTimer))
                        }.create()
                } else if (profile.pvpTimer > 0){
                    context.repeating()
                        .cooldown(20L)
                        .handle {
                            CC.translate("&a&lPvP Timer&7: &c" + TimeUtils.formatLongIntoHHMMSS(profile.pvpTimer))
                        }.create()
                }


                return@registerOnJoin context
            }
    }

}