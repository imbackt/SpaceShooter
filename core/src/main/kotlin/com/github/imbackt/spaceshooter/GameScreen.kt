package com.github.imbackt.spaceshooter

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.app.KtxScreen
import ktx.graphics.use

class GameScreen : KtxScreen {
    private val viewport = FitViewport(WORLD_WIDTH.toFloat(), WORLD_HEIGHT.toFloat())
    private val batch: Batch by lazy { SpriteBatch() }
    private val textureAtlas = TextureAtlas("images.atlas")
    private val backgrounds: Array<TextureRegion> = Array()
    private val playerShipTextureRegion = textureAtlas.findRegion("playerShip2_blue")
    private val playerShieldTextureRegion = textureAtlas.findRegion("shield2")
    private val enemyShipTextureRegion = textureAtlas.findRegion("enemyRed3")
    private val enemyShieldTextureRegion = textureAtlas.findRegion("shield1").apply { flip(false, true) }
    private val playerLaserTextureRegion = textureAtlas.findRegion("laserBlue03")
    private val enemyLaserTextureRegion = textureAtlas.findRegion("laserRed03")
    private val backgroundOffsets = floatArrayOf(0f, 0f, 0f, 0f)
    private val backgroundMaxScrollingSpeed = WORLD_HEIGHT / 4f

    private val playerShip = Ship(
        2f,
        3,
        10f,
        10f,
        WORLD_WIDTH / 2f,
        WORLD_HEIGHT / 4f,
        playerShipTextureRegion,
        playerShieldTextureRegion
    )
    private val enemyShip = Ship(
        2f,
        1,
        10f,
        10f,
        WORLD_WIDTH / 2f,
        WORLD_HEIGHT * 3f / 4f,
        enemyShipTextureRegion,
        enemyShieldTextureRegion
    )

    init {
        backgrounds.addAll(
            textureAtlas.findRegion("Starscape00"),
            textureAtlas.findRegion("Starscape01"),
            textureAtlas.findRegion("Starscape02"),
            textureAtlas.findRegion("Starscape03")
        )
    }

    override fun show() {

    }

    override fun render(delta: Float) {
        batch.use(viewport.camera.combined) {
            renderBackground(delta)

            enemyShip.draw(batch)

            playerShip.draw(batch)
        }
    }

    private fun renderBackground(delta: Float) {
        backgroundOffsets[0] += delta * backgroundMaxScrollingSpeed / 8
        backgroundOffsets[1] += delta * backgroundMaxScrollingSpeed / 4
        backgroundOffsets[2] += delta * backgroundMaxScrollingSpeed / 2
        backgroundOffsets[3] += delta * backgroundMaxScrollingSpeed / 1

        for (layer in backgroundOffsets.indices) {
            if (backgroundOffsets[layer] > WORLD_HEIGHT) {
                backgroundOffsets[layer] = 0f
            }
            batch.draw(
                backgrounds[layer],
                0f, -backgroundOffsets[layer],
                WORLD_WIDTH.toFloat(),
                WORLD_HEIGHT.toFloat() * 2
            )
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        batch.dispose()
        textureAtlas.dispose()
    }

    companion object {
        private const val WORLD_WIDTH = 72
        private const val WORLD_HEIGHT = 128
    }
}