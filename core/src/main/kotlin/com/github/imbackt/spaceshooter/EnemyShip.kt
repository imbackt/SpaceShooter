package com.github.imbackt.spaceshooter

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class EnemyShip(
    xCentre: Float,
    yCentre: Float,
    width: Float,
    height: Float,
    movementSpeed: Float,
    shield: Int,
    laserWidth: Float,
    laserHeight: Float,
    laserMovementSpeed: Float,
    timeBetweenShots: Float,
    shipTextureRegion: TextureRegion,
    shieldTextureRegion: TextureRegion,
    laserTextureRegion: TextureRegion
) : Ship(
    xCentre,
    yCentre,
    width,
    height,
    movementSpeed,
    shield,
    laserWidth,
    laserHeight,
    laserMovementSpeed,
    timeBetweenShots,
    shipTextureRegion,
    shieldTextureRegion,
    laserTextureRegion
) {

    override fun fireLasers(): Array<Laser> {
        val lasers = Array<Laser>(2)
        lasers.addAll(
            Laser(
                xPosition + width * 0.18f,
                yPosition - laserHeight,
                laserWidth,
                laserHeight,
                laserMovementSpeed,
                laserTextureRegion
            ),
            Laser(
                xPosition + width * 0.82f,
                yPosition - laserHeight,
                laserWidth,
                laserHeight,
                laserMovementSpeed,
                laserTextureRegion
            )
        )

        timeSinceLastShot = 0f

        return lasers
    }

    override fun draw(batch: Batch) {
        batch.draw(shipTextureRegion, xPosition, yPosition, width, height)
        if (shield > 0) {
            batch.draw(shieldTextureRegion, xPosition, yPosition - height * 0.2f, width, height)
        }
    }
}