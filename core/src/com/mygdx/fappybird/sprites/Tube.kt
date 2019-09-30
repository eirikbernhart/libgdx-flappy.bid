

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.Texture
import java.util.*

class Tube(x: Float) {
    val topTube: Texture
    val bottomTube: Texture
    val posTopTube: Vector2
    val posBotTube: Vector2
    private val rand: Random


    init {
        topTube = Texture("toptube.png")
        bottomTube = Texture("bottomtube.png")
        rand = Random()


        posTopTube = Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP.toFloat() + LOWEST_OPENING)
        posBotTube = Vector2(x, posTopTube.y - TUBE_GAP.toFloat() - bottomTube.height.toFloat())
    }

    fun reposition(x: Float) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP.toFloat() + LOWEST_OPENING)
        posBotTube.set(x, posTopTube.y - TUBE_GAP.toFloat() - bottomTube.height.toFloat())
    }

    fun dispose() {
        topTube.dispose()
        bottomTube.dispose()
    }

    companion object {
        val TUBE_WIDTH = 52

        private val FLUCTUATION = 130
        private val TUBE_GAP = 100
        private val LOWEST_OPENING = 120
    }
}