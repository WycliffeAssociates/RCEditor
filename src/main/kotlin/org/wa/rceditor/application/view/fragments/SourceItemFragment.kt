package org.wa.rceditor.application.view.fragments

import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.SourceItem
import org.wa.rceditor.application.model.SourceItemModel
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class SourceItemFragment: ListCellFragment<SourceItem>() {
    private val viewModel by inject<MainViewModel>()
    private val source = SourceItemModel(itemProperty)

    override val root = hbox {
        addClass(Styles.itemRoot)
        label(source.identifier) {
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
        }
        textfield(source.identifier) {
            hgrow = Priority.ALWAYS
            removeWhen { editingProperty.not() }
            whenVisible { requestFocus() }
            action { commitEdit(item) }
            promptText = "Identifier"
            required()
        }
        textfield(source.language) {
            hgrow = Priority.ALWAYS
            removeWhen { editingProperty.not() }
            action { commitEdit(item) }
            promptText = "Language"
            required()
        }
        textfield(source.version) {
            hgrow = Priority.ALWAYS
            removeWhen { editingProperty.not() }
            action { commitEdit(item) }
            promptText = "Version"
            required()
        }
        button(graphic = Styles.closeIcon()) {
            removeWhen { parent.hoverProperty().not().or(editingProperty) }
            action { viewModel.removeSource(item) }
        }
    }
}