package com.mygdx.fappybird.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.mygdx.fappybird.FappyBird
import com.mygdx.fappybird.HEIGHT
import com.mygdx.fappybird.TITLE
import com.mygdx.fappybird.WIDTH

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = WIDTH
        config.height = HEIGHT
        config.title = TITLE
        LwjglApplication(FappyBird(), config)
    }
}
