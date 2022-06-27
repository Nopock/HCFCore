package dev.viva.hcf.gems.menu

import dev.viva.hcf.profiles.Profile
import dev.viva.hcf.profiles.repository.ProfileRepository
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

class GemMenu(player: Player) : Menu(27, player) {
    override fun getButtons(player: Player): MutableMap<Int, Button> {
        val profile = ProfileCache.cache[player.uniqueId]
        val buttons = mutableMapOf<Int, Button>()
        for (i in 0 until 2) {
            buttons[i] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        }

        buttons[4] = object: Button() {
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

        for (i in 6 until 9) {
            buttons[i] = FillerButton(Material.BLACK_STAINED_GLASS_PANE)
        }

        buttons[10] = object: Button() {
            override fun getItemStack(player: Player): ItemStack {
                return ItemBuilder.of(Material.ENDER_CHEST)
                    .name(CC.translate("&b&lPartner Packages"))
                    .addToLore(CC.translate("&a"))
                    .addToLore(CC.translate("&aClick to view all items")).build()
            }

            override fun onClick(player: Player, slot: Int, type: ClickType, event: InventoryClickEvent) {
                event.isCancelled = true

                // OPEN THE pp MENU
            }

        }

        buttons[11] = object: Button() {
            override fun getItemStack(player: Player): ItemStack {
                return ItemBuilder.of(Material.APPLE).name(CC.translate("&a&lLives")).addToLore(CC.translate("&e")).addToLore(CC.translate("&aClick to view all items")).build()
            }

            override fun onClick(player: Player, slot: Int, type: ClickType, event: InventoryClickEvent) {
                event.isCancelled = true

                // OPEN THE lives menu
            }

        }

        return buttons
    }

    override fun getTitle(player: Player): String {
        TODO("Not yet implemented")
    }
}