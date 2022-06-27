package dev.viva.hcf.utils

import org.bukkit.ChatColor

object CC {

    fun translate(msg: String) : String {
        return ChatColor.translateAlternateColorCodes('&', msg)
    }

}