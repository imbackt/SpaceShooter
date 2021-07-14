package com.github.imbackt.spaceshooter

import ktx.app.KtxGame
import ktx.app.KtxScreen

class SpaceShooter : KtxGame<KtxScreen>() {
    override fun create() {
        addScreen(GameScreen())
        setScreen<GameScreen>()
    }
}