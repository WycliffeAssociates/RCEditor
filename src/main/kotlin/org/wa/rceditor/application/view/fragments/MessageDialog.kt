package org.wa.rceditor.application.view.fragments

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDialog
import com.jfoenix.controls.JFXDialogLayout
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.text.TextAlignment
import tornadofx.*

class MessageDialog(type: MessageDialog.TYPE, message: String): JFXDialog() {
    init {
        val title = if (type == MessageDialog.TYPE.SUCCESS) "Success" else "An error occurred"

        content = JFXDialogLayout().apply {
            setHeading(label(title))
            setBody(vbox {
                prefWidth = 400.0
                minHeight = 100.0

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
                        textFill = if (type == MessageDialog.TYPE.SUCCESS) c("#05CC0E") else c("#f00")
                    }
                    vboxConstraints {
                        marginBottom = 10.0
                    }
                }
            })
            setActions(JFXButton("Okay").apply {
                action { close() }
            })
        }
        transitionType = JFXDialog.DialogTransition.CENTER
        isOverlayClose = true
    }

    enum class TYPE { SUCCESS, ERROR }
}