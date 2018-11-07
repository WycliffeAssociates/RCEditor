package org.wa.rceditor.application

import org.wa.rceditor.application.app.MainView
import tornadofx.*

class MyApp: App(MainView::class, Styles::class)

fun main(args: Array<String>) {
    launch<MyApp>(args)
}