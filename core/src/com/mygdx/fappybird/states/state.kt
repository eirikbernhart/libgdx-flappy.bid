package com.mygdx.fappybird.states

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3

abstract class State protected constructor(protected var gsm: GameStateManager) {
    protected var cam: OrthographicCamera = OrthographicCamera()
    protected var mouse: Vector3? = null

    init {
        cam.setToOrtho(false, 240f, 400f)
    }

    abstract fun handleInput()
    abstract fun update(deltaTime: Float)
    abstract fun render(spriteBatch: SpriteBatch)
    abstract fun dispose()
}