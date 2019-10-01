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
        cam.setToOrtho(false, WIDTH / 2f, HEIGHT / 2f)
        background = Texture("bg.png")
        playBtn = Texture("playbtn.png")
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()) {
            gameStateManager.set(PlayState(gameStateManager))
        }
    }

    override fun update(deltaTime: Float) {
        handleInput()
    }

    override fun render(spriteBatch: SpriteBatch) {
        spriteBatch.projectionMatrix = cam.combined
        spriteBatch.begin()
        spriteBatch.draw(background, 0f, 0f)
        spriteBatch.draw(playBtn, cam.position.x - playBtn.width / 2, cam.position.y)
        spriteBatch.end()
    }

    override fun dispose() {
        background.dispose()
        playBtn.dispose()
        println("Menu State Disposed")
    }
}