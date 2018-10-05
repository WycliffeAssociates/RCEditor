package org.wa.rceditor.application.view

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTextField
import javafx.geometry.Pos
import javafx.scene.control.ListCell
import javafx.scene.layout.HBox
import org.wa.rceditor.application.Styles
import tornadofx.*

open class StringCell: ListCell<String> {
    var hbox = HBox()
    val textField = JFXTextField()
    val button = JFXButton("Delete")

    constructor(): super() {
        hbox.apply {
            this += textField.apply {
                promptText = "Contributor name"
                addClass(Styles.prompt)
                prefWidth = 200.0
            }
            this += button

            alignment = Pos.CENTER_LEFT
        }
    }

    override fun updateItem(item: String?, empty: Boolean) {
        super.updateItem(item, empty)
        text = null
        graphic = null

        if (!empty || item != null) {
            textField.text = item
            graphic = hbox
        }
    }
}