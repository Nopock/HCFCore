package dev.viva.hcf.gems.menu

import dev.viva.hcf.utils.CC
import dev.viva.hcf.utils.ItemBuilder
import dev.viva.hcf.utils.menus.Button
import dev.viva.hcf.utils.menus.Menu
import dev.viva.hcf.utils.menus.filler.FillerButton
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class GemMenu(player: Player) : Menu(27, player) {
    override fun getButtons(player: Player): MutableMap<Int, Button> {
        val buttons = mutableMapOf<Int, Button>()
        for (i in 0 until 2) {
            buttons[i] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        }

        buttons[4] = object: Button() {
            override fun getItemStack(player: Player): ItemStack {
                return ItemBuilder.of(Material.EMERALD).name(CC.translate("&a&l&nGem Shop")).build()
            }

            override fun onClick(player: Player, slot: Int, type: ClickType, event: InventoryClickEvent) {
                TODO("Not yet implemented")
            }

        }

        return buttons
    }

    override fun getTitle(player: Player): String {
        TODO("Not yet implemented")
    }
}