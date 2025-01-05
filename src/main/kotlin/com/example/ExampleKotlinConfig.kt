package com.example

import net.runelite.client.config.Config
import net.runelite.client.config.ConfigGroup
import net.runelite.client.config.ConfigItem


/**
 *  Example Kotlin Config
 */
@ConfigGroup("example")
interface ExampleKotlinConfig : Config {
    
    @ConfigItem(
        keyName = "greeting",
        name = "Welcome greeting",
        description = "The message to show to the user when they login"
    )
    fun greeting(): String = "Hello from Kotlin"
    
}