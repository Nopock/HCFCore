package dev.viva.hcf.utils.menus.filler

import dev.viva.hcf.utils.CC
import dev.viva.hcf.utils.ItemBuilder
import dev.viva.hcf.utils.menus.Button
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack


class FillerButton(private val material: Material) : Button() {

    override fun getItemStack(player: Player): ItemStack {
        return ItemBuilder.of(material).name(CC.translate("&e")).build()
    }

    override fun onClick(player: Player, slot: Int, type: ClickType, event: InventoryClickEvent) {
        TODO("Not yet implemented")
    }
}