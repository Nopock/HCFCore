package dev.viva.hcf.utils.menus.pagination

import dev.viva.hcf.utils.menus.Button
import dev.viva.hcf.utils.menus.MenuController
import dev.viva.hcf.utils.CC
import dev.viva.hcf.utils.ItemBuilder
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import java.util.*
import java.util.concurrent.CompletableFuture
import kotlin.math.ceil

abstract class PaginatedMenu(
    private val size: Int,
    private val player: Player
) {

    var page = 1;

    abstract fun getPagesButtons(player: Player): MutableMap<Int, Button>
    abstract fun getTitle(player: Player): String

    fun getButtonsInRange() : CompletableFuture<MutableMap<Int, Button>> {
        return CompletableFuture.supplyAsync {
            val buttons = hashMapOf<Int, Button>()

            val buttonSlots = getAllPagesButtonSlots()
            if (buttonSlots.isEmpty()) {
                val minIndex = ((page - 1) * 18)
                val maxIndex = (page * 18)

                for (entry in getPagesButtons(player).entries) {
                    var ind = entry.key
                    if (ind in minIndex until maxIndex) {
                        ind -= (18 * (page - 1)) - 9
                        buttons[0 + ind] = entry.value
                    }
                }
            } else {
                val maxPerPage = buttonSlots.size
                val minIndex = (page - 1) * maxPerPage
                val maxIndex = page * maxPerPage

                for ((index, entry) in getPagesButtons(player).entries.withIndex()) {
                    if (index in minIndex until Math.min(maxIndex, buttonSlots.size)) {
                        buttons[buttonSlots[index]] = entry.value
                    }
                }
            }
            return@supplyAsync buttons
        }

    }

    fun getPageNavigationButtons() : MutableMap<Int, Button> {
        val buttons = hashMapOf<Int, Button>()

        buttons[0] = object : Button() {
            override fun getItemStack(player: Player): ItemStack {
                return ItemBuilder.of(Material.FEATHER).name(CC.translate("&cCurrent Page: &f$page")).setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', "&cNavigate to previous page"))).build()
            }

            override fun onClick(player: Player, slot: Int, type: ClickType, event: InventoryClickEvent) {
                event.isCancelled = true
                if (page == 1) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou are already on the last page!"))
                    return
                }
                page -= 1
                updateMenu()
            }
        }

        buttons[8] = object : Button() {
            override fun getItemStack(player: Player): ItemStack {
                return ItemBuilder.of(Material.FEATHER).name(CC.translate("&cCurrent Page: &f$page")).setLore(Collections.singletonList(ChatColor.translateAlternateColorCodes('&', "&cNavigate to next page"))).build()
            }

            override fun onClick(player: Player, slot: Int, type: ClickType, event: InventoryClickEvent) {
                event.isCancelled = true
                page += 1
                updateMenu()
            }
        }

        return buttons
    }

    open fun getAllPagesButtonSlots(): List<Int> {
        return emptyList()
    }

    fun getMaximumPages(player: Player) : Int {
        val buttons = getPagesButtons(player)

        return if (buttons.isEmpty()) {
            1
        } else {
            ceil(buttons.size / (18).toDouble()).toInt()
        }
    }


    fun updateMenu() {
        val inventory = Bukkit.createInventory(null, (size + 9), getTitle(player))


        for (entry in getPageNavigationButtons()) {
            inventory.setItem(entry.key, entry.value.getItemStack(player))
        }

        CompletableFuture.runAsync {
            for (entry in getButtonsInRange().get()) {
                inventory.setItem(entry.key, entry.value.getItemStack(player))
            }
        }

        player.openInventory(inventory)
        player.updateInventory()
        MenuController.paginatedMenuMap[player.uniqueId] = this
    }

}