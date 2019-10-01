package com.mygdx.fappybird.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Bird(x: Int, y: Int) {

    val position: Vector3
    val velocity: Vector3
    val texture: Texture
    private val bounds: Rectangle
    private val birdAnimation: Animation
    private val flapSound: Sound

    var colliding: Boolean = false

    val x: Float
        get() = position.x

    val y: Float
        get() = position.y

    init {
        position = Vector3(x.toFloat(), y.toFloat(), 0f)
        velocity = Vector3(0f, 0f, 0f)
        texture = Texture("birdanimation.png")
        birdAnimation = Animation(TextureRegion(texture), 3, 0.5f)
        bounds = Rectangle(x.toFloat(), y.toFloat(), texture.width.toFloat() / 3, texture.height.toFloat())
        flapSound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"))
    }

    fun update(deltaTime: Float) {
        birdAnimation.update(deltaTime)
        if (position.y > 0) {
            velocity.add(0f, GRAVITY.toFloat(), 0f)
        }
        velocity.scl(deltaTime)
        position.add(MOVEMENT.toFloat() * deltaTime, velocity.y, 0f)
        if (position.y < 0) {
            position.y = 0f
        }
        velocity.scl(1 / deltaTime)
        bounds.setPosition(position.x, position.y)
    }

    fun getTexture(): TextureRegion {
        return birdAnimation.getFrame()
    }

    fun jump() {
        velocity.y = 250f
        flapSound.play(0.5f)
    }

    fun getBounds(): Rectangle {
        return bounds
    }

    fun dispose() {
        texture.dispose()
        flapSound.dispose()
    }

    companion object {
        private val GRAVITY = -15
        private val MOVEMENT = 100
    }
}