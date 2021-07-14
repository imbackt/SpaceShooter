package com.github.imbackt.spaceshooter

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

abstract class Ship(
    private val xCentre: Float,
    private val yCentre: Float,
    protected val width: Float,
    protected val height: Float,
    protected val movementSpeed: Float,
    protected val shield: Int,
    protected val laserWidth: Float,
    protected val laserHeight: Float,
    protected val laserMovementSpeed: Float,
    private val timeBetweenShots: Float,
    protected val shipTextureRegion: TextureRegion,
    protected val shieldTextureRegion: TextureRegion,
    protected val laserTextureRegion: TextureRegion
) {

    protected val xPosition: Float
        get() = xCentre - width / 2
    protected val yPosition: Float
        get() = yCentre - height / 2

    protected var timeSinceLastShot = 0f

    fun update(delta: Float) {
        timeSinceLastShot += delta
    }

    fun canFireLaser(): Boolean {
        return (timeSinceLastShot - timeBetweenShots) >= 0
    }

    abstract fun fireLasers() : Array<Laser>

    open fun draw(batch: Batch) {
        batch.draw(shipTextureRegion, xPosition, yPosition, width, height)
        if (shield > 0) {
            batch.draw(shieldTextureRegion, xPosition, yPosition, width, height)
        }
    }
}