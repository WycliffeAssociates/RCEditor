package org.wa.rceditor.application.view.fragments

import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.text.TextAlignment
import tornadofx.*

class DialogFragment: Fragment() {
    val message: String by param()
    val type: TYPE by param()

    override val root = vbox {
        prefWidth = 400.0
        minHeight = 100.0

        title = if (type == TYPE.SUCCESS) "Success" else "An error occurred"

        vboxConstraints {
            padding = insets(10.0)
            alignment = Pos.CENTER
        }

        label(message) {
            vgrow = Priority.ALWAYS
            isWrapText = true
            textAlignment = TextAlignment.CENTER
            maxHeight = Double.MAX_VALUE
            style {
                fontSize = 16.px
                textFill = if (type == TYPE.SUCCESS) c("#05CC0E") else c("#f00")
            }
            vboxConstraints {
                marginBottom = 10.0
            }
        }

        separator()

        this += JFXButton("OK").apply {
            vboxConstraints {
                padding = insets(20.0, 5.0)
                marginTop = 10.0
            }
            action { close() }
        }
    }

    enum class TYPE { SUCCESS, ERROR }
}