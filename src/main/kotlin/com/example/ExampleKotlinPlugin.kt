package com.example

import com.google.inject.Provides
import net.runelite.api.ChatMessageType
import net.runelite.api.Client
import net.runelite.api.GameState
import net.runelite.api.events.GameStateChanged
import net.runelite.client.config.ConfigManager
import net.runelite.client.eventbus.Subscribe
import net.runelite.client.plugins.Plugin
import net.runelite.client.plugins.PluginDescriptor
import org.slf4j.LoggerFactory
import javax.inject.Inject


/**
 *  Example Kotlin Plugin
 */
@PluginDescriptor(name = "Example.kt")
open class ExampleKotlinPlugin : Plugin() {

    @Inject
    private lateinit var client: Client
    
    @Inject
    private lateinit var config: ExampleKotlinConfig
    
    @Throws(Exception::class)
    override fun startUp() {
        log.info("Example.kt started!")
    }

    @Throws(Exception::class)
    override fun shutDown() {
        log.info("Example.kt stopped!")
    }
    
    @Subscribe
    fun onGameStateChanged(gameStateChanged: GameStateChanged) {
        if (gameStateChanged.gameState == GameState.LOGGED_IN) {
            client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example.kt: ${config.greeting()}", null)
        }
    }
    
    @Provides
    protected fun provideConfig(configManager: ConfigManager): ExampleKotlinConfig = configManager.getConfig(ExampleKotlinConfig::class.java)
    
    companion object {
        private val log = LoggerFactory.getLogger(ExampleKotlinPlugin::class.java)
        
    }
    
}