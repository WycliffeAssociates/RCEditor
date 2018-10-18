package org.wa.rceditor.application.view.fragments

import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.CheckingEntityItem
import org.wa.rceditor.application.model.CheckingEntityItemModel
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class CheckingEntityItemFragment: ListCellFragment<CheckingEntityItem>() {
    private val viewModel by inject<MainViewModel>()
    private val checkingEntity = CheckingEntityItemModel(itemProperty)

    override val root = hbox {
        addClass(Styles.itemRoot)

        label(checkingEntity.text) {
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
        }
        textfield(checkingEntity.text) {
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