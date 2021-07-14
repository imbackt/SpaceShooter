package com.github.imbackt.spaceshooter.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.github.imbackt.spaceshooter.SpaceShooter

fun main() {
    Lwjgl3Application(SpaceShooter(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("SpaceShooter")
        setWindowedMode(72 * 5, 128 * 5)
        setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png")
    })
}