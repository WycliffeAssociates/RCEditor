package org.wa.rceditor.application.view.fragments

import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import tornadofx.*

class StringCell: ListCellFragment<String>() {
    override val root = hbox {
        addClass(Styles.itemRoot)

        label(itemProperty) {
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
        }
        textfield(itemProperty) {
            hgrow = Priority.ALWAYS
            removeWhen { editingProperty.not() }
            whenVisible { requestFocus() }
            action {
                if (text.trim().isNotEmpty()) {
                    commitEdit(item)
                }
            }
        }
        button(graphic = Styles.closeIcon()) {
            removeWhen { parent.hoverProperty().not().or(editingProperty) }
            action { cell?.listView?.items?.remove(item) }
        }
    }
}