package com.mygdx.fappybird

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.fappybird.states.GameStateManager
import com.mygdx.fappybird.states.MenuState

const val WIDTH = 480
const val HEIGHT = 800

const val TITLE = "FappyBird"

class FappyBird : ApplicationAdapter() {
    private lateinit var batch: SpriteBatch

    private lateinit var gameStateManager: GameStateManager

    override fun create() {
        batch = SpriteBatch()
        gameStateManager = GameStateManager()
        gameStateManager.push(MenuState(gameStateManager))
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gameStateManager.update(Gdx.graphics.deltaTime)
        gameStateManager.render(batch)
    }
}