package com.github.imbackt.spaceshooter

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.StretchViewport
import ktx.app.KtxScreen
import ktx.graphics.use

class GameScreen : KtxScreen {
    private val viewport = StretchViewport(WORLD_WIDTH.toFloat(), WORLD_HEIGHT.toFloat())
    private val batch: Batch by lazy { SpriteBatch() }
    private var backgrounds = arrayOf<Texture>()
    private val backgroundOffsets = floatArrayOf(0f, 0f, 0f, 0f)
    private val backgroundMaxScrollingSpeed = WORLD_HEIGHT / 4f

    override fun show() {
        backgrounds += Texture("Starscape00.png")
        backgrounds += Texture("Starscape01.png")
        backgrounds += Texture("Starscape02.png")
        backgrounds += Texture("Starscape03.png")
    }

    override fun render(delta: Float) {
        batch.use(viewport.camera.combined) {
            renderBackground(delta)
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
            batch.draw(backgrounds[layer], 0f, -backgroundOffsets[layer], WORLD_WIDTH.toFloat(), WORLD_HEIGHT.toFloat())
            batch.draw(backgrounds[layer], 0f, -backgroundOffsets[layer] + WORLD_HEIGHT, WORLD_WIDTH.toFloat(), WORLD_HEIGHT.toFloat())
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        batch.dispose()
        backgrounds.forEach {
            it.dispose()
        }
    }

    companion object {
        private const val WORLD_WIDTH = 72
        private const val WORLD_HEIGHT = 128
    }
}