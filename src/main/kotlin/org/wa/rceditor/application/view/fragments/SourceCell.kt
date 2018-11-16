package org.wa.rceditor.application.view.fragments

import com.jfoenix.controls.JFXTextField
import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.SourceItem
import org.wa.rceditor.application.model.SourceItemModel
import tornadofx.*

class SourceCell: ListCellFragment<SourceItem>() {
    private val model = SourceItemModel(itemProperty)

    override val root = hbox {
        addClass(Styles.itemRoot)
        spacing = 5.0

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

        label(model.identifier) {
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
        }
        this += JFXTextField().apply {
            hgrow = Priority.ALWAYS
            removeWhen { editingProperty.not() }
            whenVisible { requestFocus() }
            action {
                model.commit { commitEdit(item) }
            }
            promptText = "Identifier"
            isLabelFloat = true
            bind(model.identifier)
            required()
        }
        this += JFXTextField().apply {
            hgrow = Priority.ALWAYS
            removeWhen { editingProperty.not() }
            action {
                model.commit { commitEdit(item) }
            }
            promptText = "Language"
            isLabelFloat = true
            bind(model.language)
            required()
        }
        this += JFXTextField().apply {
            hgrow = Priority.ALWAYS
            removeWhen { editingProperty.not() }
            action {
                model.commit { commitEdit(item) }
            }
            promptText = "Version"
            isLabelFloat = true
            bind(model.version)
            required()
        }
    }
}