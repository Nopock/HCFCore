package dev.viva.hcf.utils.enderpearl

import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class EnderpearlRefundEvent(val player: Player) : Event() {

    private val handlersList: HandlerList = HandlerList()

    override fun getHandlers(): HandlerList {
        return handlersList
    }
}