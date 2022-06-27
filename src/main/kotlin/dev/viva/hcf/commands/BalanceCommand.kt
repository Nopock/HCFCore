package dev.viva.hcf.commands

import co.aikar.commands.BaseCommand
import co.aikar.commands.annotation.CommandAlias
import co.aikar.commands.annotation.Default
import co.aikar.commands.annotation.Optional
import dev.viva.hcf.profiles.repository.cache.ProfileCache
import dev.viva.hcf.utils.CC
import org.bukkit.entity.Player
import java.text.NumberFormat
import java.util.*

@CommandAlias("balance|bal")
object BalanceCommand : BaseCommand() {

    @Default
    fun balance(player: Player, @Optional target: Player?){
        if (target == null){
            val profile = ProfileCache.cache[player.uniqueId]

            player.sendMessage(CC.translate("&6Balance&7: &f" + NumberFormat.getInstance().format(profile?.balance)))
        } else {
            val profile = ProfileCache.cache[target.uniqueId]

            player.sendMessage(CC.translate("&6Balance of " + target.displayName + "&7: &f" + NumberFormat.getInstance(
                Locale.US).format(profile?.balance)))

        }
    }

}