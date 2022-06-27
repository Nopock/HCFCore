package dev.viva.hcf.utils.menus


import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

abstract class Button {

    abstract fun getItemStack(player: Player) : ItemStack

    abstract fun onClick(player: Player, slot: Int, type: ClickType, event: InventoryClickEvent)

}