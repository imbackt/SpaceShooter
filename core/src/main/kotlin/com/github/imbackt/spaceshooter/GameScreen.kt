package com.github.imbackt.spaceshooter

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.PooledLinkedList
import com.badlogic.gdx.utils.viewport.FitViewport
import ktx.app.KtxScreen
import ktx.graphics.use
import java.util.*

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

    private val playerShip = PlayerShip(
        WORLD_WIDTH / 2f,
        WORLD_HEIGHT / 4f,
        10f,
        10f,
        2f,
        3,
        0.4f,
        4f,
        45f,
        0.5f,
        playerShipTextureRegion,
        playerShieldTextureRegion,
        playerLaserTextureRegion
    )
    private val enemyShip = EnemyShip(
        WORLD_WIDTH / 2f,
        WORLD_HEIGHT * 3f / 4f,
        10f,
        10f,
        2f,
        1,
        0.3f,
        5f,
        50f,
        0.8f,
        enemyShipTextureRegion,
        enemyShieldTextureRegion,
        enemyLaserTextureRegion
    )

    private val playerLaserList = LinkedList<Laser>()
    private val enemyLaserList = LinkedList<Laser>()

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
            playerShip.update(delta)
            enemyShip.update(delta)

            renderBackground(delta)

            enemyShip.draw(batch)
            playerShip.draw(batch)

            if (playerShip.canFireLaser()) {
                val lasers = playerShip.fireLasers()
                lasers.forEach {
                    playerLaserList.add(it)
                }
            }

            if (enemyShip.canFireLaser()) {
                val lasers = enemyShip.fireLasers()
                lasers.forEach {
                    enemyLaserList.add(it)
                }
            }

            var iterator = playerLaserList.listIterator()
            while (iterator.hasNext()) {
                val laser = iterator.next()
                laser.draw(batch)
                laser.yPosition += laser.movementSpeed * delta
                if (laser.yPosition > WORLD_HEIGHT) {
                    iterator.remove()
                }
            }

            iterator = enemyLaserList.listIterator()
            while (iterator.hasNext()) {
                val laser = iterator.next()
                laser.draw(batch)
                laser.yPosition -= laser.movementSpeed * delta
                if ((laser.yPosition +laser.height) < 0) {
                    iterator.remove()
                }
            }
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