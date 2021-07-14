package com.github.imbackt.spaceshooter

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Laser(
    private val xCentre: Float,
    private val yCentre: Float,
    private val width: Float,
    val height: Float,
    val movementSpeed: Float,
    private val textureRegion: TextureRegion
) {
    var xPosition = xCentre - width / 2
    var yPosition = yCentre - height / 2

    fun draw(batch: Batch) {
        batch.draw(textureRegion, xPosition, yPosition, width, height)
    }
}