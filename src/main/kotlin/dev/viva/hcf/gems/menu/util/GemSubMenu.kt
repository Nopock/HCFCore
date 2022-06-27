package dev.viva.hcf.gems.menu.util

import dev.viva.hcf.profiles.repository.cache.ProfileCache
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

class GemSubMenu(private val title: String, player: Player, private val items: MutableList<Button>) : Menu(27, player) {
    override fun getButtons(player: Player): MutableMap<Int, Button> {
        val profile = ProfileCache.cache[player.uniqueId]

        val buttons = mutableMapOf<Int, Button>()

        buttons[0] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[1] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[2] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)

        buttons[4] = object : Button() {
            override fun getItemStack(player: Player): ItemStack {
                if (profile != null) {
                    return ItemBuilder.of(Material.EMERALD)
                        .name(CC.translate("&a&l&nGem Shop"))
                        .addToLore(CC.translate("&eYou have &a${profile.gems} &egems."))
                        .addToLore(CC.translate("&e"))
                        .addToLore(CC.translate("&6&l| &e+1 &agems &eper kill."))
                        .addToLore(CC.translate("&6&l| &e+5 &agems &eper faction you make raidable!"))
                        .build()
                }

                return ItemStack(Material.AIR)
            }

            override fun onClick(player: Player, slot: Int, type: ClickType, event: InventoryClickEvent) {
                event.isCancelled = true
            }

        }

        buttons[6] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[7] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[8] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)

        buttons[9] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)

        buttons[17] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[18] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[19] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[20] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)

        buttons[24] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[25] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        buttons[26] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)

        buttons[22] = object :  Button() {
            override fun getItemStack(player: Player): ItemStack {
                return ItemBuilder.of(Material.PLAYER_HEAD)
                    .name(CC.translate("&a&l&nGem Shop"))
                    .addToLore("")
                    .addToLore("&6&l| &eOP goodies!!!").build()
            }

            override fun onClick(player: Player, slot: Int, type: ClickType, event: InventoryClickEvent) {
                event.isCancelled = true
            }

        }

        when (items.size) {
           7 -> {
               for((int, i) in (10 until 17).withIndex()) {
                   buttons[i] = items[int]
               }
           }

            5 -> {
                buttons[10] = items[0]
                buttons[11] = items[1]
                buttons[13] = items[2]
                buttons[15] = items[3]
                buttons[16] = items[4]
            }

            3 -> {
                buttons[11] = items[0]
                buttons[13] = items[1]
                buttons[15] = items[2]
            }

            1 -> {
                buttons[13] = items[0]
            }
        }



        return buttons

    }

    override fun getTitle(player: Player): String {
        return CC.translate(title)
    }

}