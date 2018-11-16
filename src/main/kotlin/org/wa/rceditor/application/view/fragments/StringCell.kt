package org.wa.rceditor.application.view.fragments

import com.jfoenix.controls.JFXTextField
import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import tornadofx.*

class StringCell: ListCellFragment<String>() {
    override val root = hbox {
        addClass(Styles.itemRoot)

        button(graphic = Styles.closeIcon()) {
            hboxConstraints {
                marginLeft = -10.0
                marginRight = 5.0
            }

            removeWhen { parent.hoverProperty().not().or(editingProperty) }
            action { cell?.listView?.items?.remove(item) }
        }

        hbox {
            prefWidth = 13.0
            removeWhen { parent.hoverProperty().or(editingProperty) }
        }

        label(itemProperty) {
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
        }
        this += JFXTextField().apply {
            hgrow = Priority.ALWAYS
            bind(itemProperty)
            removeWhen { editingProperty.not() }
            whenVisible { requestFocus() }

            action {
                if (text.trim().isNotEmpty()) {
                    commitEdit(item)
                }
            }
        }
    }
}