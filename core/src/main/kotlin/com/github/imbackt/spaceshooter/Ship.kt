package com.github.imbackt.spaceshooter

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion

class Ship(
    private val movementSpeed: Float,
    private val shield: Int,
    private val width: Float,
    private val height: Float,
    private val xCentre: Float,
    private val yCentre: Float,
    private val shipTexture: TextureRegion,
    private val shieldTexture: TextureRegion
) {
    private val xPosition: Float
        get() = xCentre - width / 2;
    private val yPosition: Float
        get() = yCentre - height / 2;

    fun draw(batch: Batch) {
        batch.draw(shipTexture, xPosition, yPosition, width, height)
        if (shield > 0) {
            batch.draw(shieldTexture, xPosition, yPosition, width, height)
        }
    }
}