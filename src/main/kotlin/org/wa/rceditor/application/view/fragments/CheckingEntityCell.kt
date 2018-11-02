package org.wa.rceditor.application.view.fragments

import javafx.beans.property.StringProperty
import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class CheckingEntityCell: ListCellFragment<StringProperty>() {
    private val viewModel by inject<MainViewModel>()
    private val entity = ItemViewModel(itemProperty = itemProperty).bind { item }

    override val root = hbox {
        addClass(Styles.itemRoot)

        label(entity) {
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
        }
        textfield(entity) {
            hgrow = Priority.ALWAYS
            removeWhen { editingProperty.not() }
            whenVisible { requestFocus() }
            action { if (text.trim().isNotEmpty()) commitEdit(item) }
            promptText = "Checking Entity"
            required()
        }
        button(graphic = Styles.closeIcon()) {
            removeWhen { parent.hoverProperty().not().or(editingProperty) }
            action { viewModel.removeCheckingEntity(item) }
        }
    }
}