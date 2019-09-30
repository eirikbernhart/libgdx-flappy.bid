package com.mygdx.fappybird.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3

class Bird(x: Int, y: Int) {

    val position: Vector3
    val velocity: Vector3
    private val texture: Texture

    var colliding: Boolean = false

    val x: Float
        get() = position.x

    val y: Float
        get() = position.y

    val width: Float
        get() = texture.width.toFloat()

    val height: Float
        get() = texture.height.toFloat()

    init {
        position = Vector3(x.toFloat(), y.toFloat(), 0f)
        velocity = Vector3(0f, 0f, 0f)
        texture = Texture("bird.png")
    }

    fun update(deltaTime: Float) {
        if (position.y > 0) {
            velocity.add(0f, GRAVITY.toFloat(), 0f)
        }
        velocity.scl(deltaTime)
        position.add(MOVEMENT.toFloat() * deltaTime, velocity.y, 0f)
        if (position.y < 0) {
            position.y = 0f
        }
        velocity.scl(1 / deltaTime)
    }

    fun getTexture(): Texture {
        return texture
    }

    fun jump() {
        velocity.y = 250f
    }

    companion object {
        private val GRAVITY = -15
        private val MOVEMENT = 100
    }
}