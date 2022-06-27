package dev.viva.hcf.gems.menu.confirm

import dev.viva.hcf.utils.CC
import dev.viva.hcf.utils.ItemBuilder
import dev.viva.hcf.utils.menus.Button
import dev.viva.hcf.utils.menus.Menu
import dev.viva.hcf.utils.menus.filler.FillerButton
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.Collections
import java.util.function.Consumer

class ConfirmMenu(player: Player, val clickAction: Consumer<InventoryClickEvent>) : Menu(27, player) {

    override fun getButtons(player: Player): MutableMap<Int, Button> {
        val buttons = mutableMapOf<Int, Button>()

        buttons[0] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[1] = FillerButton(Material.LIME_STAINED_GLASS_PANE)
        buttons[2] = FillerButton(Material.LIME_STAINED_GLASS_PANE)
        buttons[3] = FillerButton(Material.LIME_STAINED_GLASS_PANE)
        buttons[4] = FillerButton(Material.WHITE_STAINED_GLASS_PANE)
        buttons[5] = FillerButton(Material.RED_STAINED_GLASS_PANE)
        buttons[6] = FillerButton(Material.RED_STAINED_GLASS_PANE)
        buttons[7] = FillerButton(Material.RED_STAINED_GLASS_PANE)
        buttons[8] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[9] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[10] = FillerButton(Material.LIME_STAINED_GLASS_PANE)
        buttons[11] = object : Button() {
            override fun getItemStack(player: Player): ItemStack {
                return ItemBuilder.of(Material.LIME_DYE).name(CC.translate("&aConfirm")).setLore(Collections.singletonList(CC.translate("&7Click to confirm your purchases."))).build()
            }

            override fun onClick(player: Player, slot: Int, type: ClickType, event: InventoryClickEvent) {
                event.isCancelled = true

                clickAction.accept(event)
            }

        }
        buttons[12] = FillerButton(Material.LIME_STAINED_GLASS_PANE)
        buttons[13] = FillerButton(Material.WHITE_STAINED_GLASS_PANE)
        buttons[14] = FillerButton(Material.RED_STAINED_GLASS_PANE)
        buttons[15] = object :  Button() {
            override fun getItemStack(player: Player): ItemStack {
                return ItemBuilder.of(Material.RED_DYE).name(CC.translate("&cCancel")).setLore(Collections.singletonList(CC.translate("&7Click to cancel your purchases."))).build()
            }

            override fun onClick(player: Player, slot: Int, type: ClickType, event: InventoryClickEvent) {
                event.isCancelled = true

                player.closeInventory()
            }

        }
        buttons[16] = FillerButton(Material.RED_STAINED_GLASS_PANE)
        buttons[17] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[18] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[19] = FillerButton(Material.LIME_STAINED_GLASS_PANE)
        buttons[20] = FillerButton(Material.LIME_STAINED_GLASS_PANE)
        buttons[21] = FillerButton(Material.LIME_STAINED_GLASS_PANE)
        buttons[22] = FillerButton(Material.WHITE_STAINED_GLASS_PANE)
        buttons[23] = FillerButton(Material.RED_STAINED_GLASS_PANE)
        buttons[24] = FillerButton(Material.RED_STAINED_GLASS_PANE)
        buttons[25] = FillerButton(Material.RED_STAINED_GLASS_PANE)
        buttons[26] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)

        return buttons
    }

    override fun getTitle(player: Player): String {
        return CC.translate("&a&lGem Shop << Confirm")
    }

}