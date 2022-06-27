package dev.viva.hcf.utils

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent

class MessageBuilder(text: String) {

    private var builder: TextComponent? = null

    init {
        builder = TextComponent(CC.translate(text))
    }

    fun hover(text: String){
        builder?.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder(CC.translate(text)).create())
    }

    fun click(command: String){
        builder?.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, command)
    }

    fun create() : TextComponent? {
        return builder
    }

}