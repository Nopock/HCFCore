package dev.viva.hcf.utils.menus


import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.concurrent.CompletableFuture

abstract class Menu(
    private val size: Int,
    private val player: Player
) {

    abstract fun getButtons(player: Player) : MutableMap<Int, Button>
    abstract fun getTitle(player: Player) : String

    fun getAllButtons() : MutableMap<Int, Button> {
        return getButtons(player)
    }


    //dont need to use update menu because it is just placing items in menu. If it gets to it ill do it
    fun openMenu() {
        val inventory = Bukkit.createInventory(null, size, getTitle(player))

        MenuController.addToMenuMap(player, this)

        CompletableFuture.runAsync {
            for (entry in getAllButtons())
            {
                inventory.setItem(entry.key, entry.value.getItemStack(player))
            }
        }


        player.openInventory(inventory)

    }





}