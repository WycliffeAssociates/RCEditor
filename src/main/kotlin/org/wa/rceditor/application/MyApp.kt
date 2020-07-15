package org.wa.rceditor.application

import javafx.scene.image.Image
import javafx.stage.Stage
import org.wa.rceditor.application.app.MainView
import tornadofx.*

class MyApp: App(MainView::class, Styles::class) {
    override fun start(stage: Stage) {
        stage.icons.add(
            Image(javaClass.getResource("/launcher.png").openStream())
        )
        super.start(stage)
    }
}

fun main(args: Array<String>) {
    launch<MyApp>(args)
}
