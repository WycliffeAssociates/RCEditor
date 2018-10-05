package org.wa.rceditor.application.view

import com.jfoenix.controls.JFXButton
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.ListCell
import javafx.scene.layout.HBox
import org.wycliffeassociates.resourcecontainer.entity.Project
import tornadofx.*

open class ProjectCell: ListCell<Project> {
    var hbox = HBox()
    val label = Label()
    val button = JFXButton("Edit")

    constructor(): super() {
        hbox.apply {
            this += label
            this += button

            alignment = Pos.CENTER_LEFT
        }
    }

    override fun updateItem(item: Project?, empty: Boolean) {
        super.updateItem(item, empty)
        text = null
        graphic = null

        if (!empty || item != null) {
            label.text = "${item!!.sort}. ${item.title} (${item.identifier})"
            graphic = hbox
        }
    }
}