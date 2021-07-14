package com.github.imbackt.spaceshooter

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

class PlayerShip(
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
                xPosition + width * 0.07f,
                yPosition + height * 0.45f,
                laserWidth,
                laserHeight,
                laserMovementSpeed,
                laserTextureRegion
            ),
            Laser(
                xPosition + width * 0.93f,
                yPosition + height * 0.45f,
                laserWidth,
                laserHeight,
                laserMovementSpeed,
                laserTextureRegion
            )
        )

        timeSinceLastShot = 0f

        return lasers
    }
}