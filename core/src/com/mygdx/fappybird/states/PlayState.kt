package com.mygdx.fappybird.states

import Tube
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.fappybird.HEIGHT
import com.mygdx.fappybird.WIDTH
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.math.Vector2
import com.mygdx.fappybird.sprites.Bird


class PlayState(gsm: GameStateManager) : State(gsm) {

    private val bird: Bird
    private val bg: Texture
    private val ground: Texture
    private val groundPos1: Vector2
    private val groundPos2: Vector2
    private val tubes: Array<Tube>

    init {
        bird = Bird(50, 300)
        cam.setToOrtho(false, WIDTH / 2f, HEIGHT / 2f)
        bg = Texture("bg.png")
        ground = Texture("ground.png")
        groundPos1 = Vector2(cam.position.x - cam.viewportWidth / 2, GROUND_Y_OFFSET.toFloat())
        groundPos2 = Vector2(cam.position.x - cam.viewportWidth / 2 + ground.width, GROUND_Y_OFFSET.toFloat())

        tubes = Array()

        for (i in 1..TUBE_COUNT) {
            tubes.add(Tube(i * (TUBE_SPACING.toFloat() + Tube.TUBE_WIDTH)))
        }


    }

    override fun handleInput() {
        if (Gdx.input.justTouched())
            bird.jump()
    }

    override fun update(dt: Float) {
        handleInput()
        updateGround()
        bird.update(dt)
        cam.position.x = bird.position.x + 80

        for (i in 0 until tubes.size) {
            val tube = tubes.get(i)
            if (cam.position.x - cam.viewportWidth / 2 > tube.posTopTube.x + tube.topTube.width) {
                tube.reposition(tube.posTopTube.x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT)
            }

            if (tube.collides(bird.getBounds())) {
                gsm.set(MenuState(gsm))
            }
        }

        if (bird.position.y <= ground.height + GROUND_Y_OFFSET) {
            gsm.set(MenuState(gsm))
        }
        cam.update()
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        sb.draw(bg, cam.position.x - cam.viewportWidth / 2, 0f)
        sb.draw(bird.getTexture(), bird.position.x, bird.position.y)
        for (tube in tubes) {
            sb.draw(tube.topTube, tube.posTopTube.x, tube.posTopTube.y)
            sb.draw(tube.bottomTube, tube.posBotTube.x, tube.posBotTube.y)
        }

        sb.draw(ground, groundPos1.x, groundPos1.y)
        sb.draw(ground, groundPos2.x, groundPos2.y)
        sb.end()
    }

    override fun dispose() {
        bg.dispose()
        ground.dispose()
        for (tube in tubes)
            tube.dispose()
        println("Play State Disposed")
    }

    private fun updateGround() {
        if (cam.position.x - cam.viewportWidth / 2 > groundPos1.x + ground.width)
            groundPos1.add((ground.width * 2).toFloat(), 0f)
        if (cam.position.x - cam.viewportWidth / 2 > groundPos2.x + ground.width)
            groundPos2.add((ground.width * 2).toFloat(), 0f)
    }

    companion object {
        private val TUBE_SPACING = 125
        private val TUBE_COUNT = 4
        private val GROUND_Y_OFFSET = -50
    }
}