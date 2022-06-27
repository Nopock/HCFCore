package dev.viva.hcf.utils.menus

import dev.viva.hcf.utils.menus.pagination.PaginatedMenu
import org.bukkit.entity.Player
import java.util.*

object MenuController {
    var menuMap = hashMapOf<UUID, Menu>()
    var paginatedMenuMap = hashMapOf<UUID, PaginatedMenu>()


    fun addToMenuMap(player: Player, menu: Menu) {
        menuMap[player.uniqueId] = menu
    }

}