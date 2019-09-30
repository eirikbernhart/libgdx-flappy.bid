package com.mygdx.fappybird.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.mygdx.fappybird.HEIGHT
import com.mygdx.fappybird.WIDTH

class MenuState(var gameStateManager: GameStateManager) : State(gameStateManager) {
    internal var background: Texture
    internal var playBtn: Texture

    init {
        background = Texture("bg.png")
        playBtn = Texture("playbtn.png")
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.set(PlayState(gameStateManager))
            dispose()
        }
    }

    override fun update(deltaTime: Float) {
        handleInput()
    }

    override fun render(spriteBatch: SpriteBatch) {
        spriteBatch.begin()
        spriteBatch.draw(background, 0f, 0f, WIDTH.toFloat(), HEIGHT.toFloat())
        spriteBatch.draw(playBtn, (WIDTH.toFloat() / 2) - (playBtn.width / 2), HEIGHT.toFloat() / 2)
        spriteBatch.end()
    }

    override fun dispose() {
        background.dispose()
        playBtn.dispose()
    }
}