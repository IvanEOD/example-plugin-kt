package com.example

import net.runelite.client.RuneLite
import net.runelite.client.externalplugins.ExternalPluginManager


/**
 * Example Kotlin Plugin Test
 */
class ExampleKotlinPluginTest {

    companion object {
        //Note: Make sure run configuration has "-ea" in the VM Options.
        @JvmStatic
        fun main(vararg args: String) {
            ExternalPluginManager.loadBuiltin(ExampleKotlinPlugin::class.java)
            RuneLite.main(args)
        }
    }
    
}