package dev.viva.hcf.utils

import com.google.common.base.Preconditions
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta


class ItemBuilder {
    private val item: ItemStack

    private constructor(material: Material, amount: Int) {
        Preconditions.checkArgument(amount > 0, "Amount cannot be lower than 0.")
        item = ItemStack(material, amount)
    }

    private constructor(item: ItemStack) {
        this.item = item
    }

    fun amount(amount: Int): ItemBuilder {
        item.amount = amount
        return this
    }

    fun data(data: Short): ItemBuilder {
        item.durability = data
        return this
    }

    fun owner(owner: String?): ItemBuilder {
        val playerheadmeta = item.itemMeta as SkullMeta?
        playerheadmeta!!.owner = owner
        playerheadmeta.setDisplayName(owner)
        item.itemMeta = playerheadmeta
        return this
    }

    fun owner(owner: String?, displayName: String?): ItemBuilder {
        val playerheadmeta = item.itemMeta as SkullMeta?
        playerheadmeta!!.owner = owner
        playerheadmeta.setDisplayName(displayName)
        item.itemMeta = playerheadmeta
        return this
    }

    fun flag(flag: ItemFlag?): ItemBuilder {
        val meta = item.itemMeta
        meta!!.addItemFlags(flag)
        item.itemMeta = meta
        return this
    }

    fun enchant(enchantment: Enchantment?, level: Int): ItemBuilder {
        item.addUnsafeEnchantment(enchantment!!, level)
        return this
    }

    fun unenchant(enchantment: Enchantment?): ItemBuilder {
        item.removeEnchantment(enchantment!!)
        return this
    }

    fun name(displayName: String?): ItemBuilder {
        val meta = item.itemMeta
        meta!!.setDisplayName(
            if (displayName == null) null else ChatColor.translateAlternateColorCodes(
                '&',
                displayName
            )
        )
        item.itemMeta = meta
        return this
    }

    fun owningPlayer(name: String?): ItemBuilder {
        val meta = item.itemMeta as SkullMeta?
        meta!!.owningPlayer = Bukkit.getOfflinePlayer(name!!)
        item.itemMeta = meta
        return this
    }

    fun setLore(l: List<String?>): ItemBuilder {
        val lore: ArrayList<*> = ArrayList<Any?>()
        val meta = item.itemMeta

        meta?.lore = l

        item.itemMeta = meta
        return this
    }

    fun addToLore(l: String): ItemBuilder {
        val meta = item.itemMeta
        meta?.lore?.add(CC.translate(l))
        item.itemMeta = meta

        return this
    }

    fun color(color: Color?): ItemBuilder {
        val meta = item.itemMeta as? LeatherArmorMeta
            ?: throw UnsupportedOperationException("Cannot set color of a non-leather armor item.")
        meta.setColor(color)
        item.itemMeta = meta
        return this
    }

    fun setUnbreakable(unbreakable: Boolean): ItemBuilder {
        val meta = item.itemMeta
        meta!!.isUnbreakable = unbreakable
        item.itemMeta = meta
        return this
    }

    fun build(): ItemStack {
        return item.clone()
    }

    companion object {
        fun of(material: Material): ItemBuilder {
            return ItemBuilder(material, 1)
        }

        fun of(material: Material, amount: Int): ItemBuilder {
            return ItemBuilder(material, amount)
        }

        fun copyOf(builder: ItemBuilder): ItemBuilder {
            return ItemBuilder(builder.build())
        }

        fun copyOf(item: ItemStack): ItemBuilder {
            return ItemBuilder(item)
        }
    }
}