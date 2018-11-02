package org.wa.rceditor.application.view.fragments

import javafx.beans.property.StringProperty
import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class RelationCell: ListCellFragment<StringProperty>() {
    private val viewModel by inject<MainViewModel>()
    private val relation = ItemViewModel(itemProperty = itemProperty).bind { item }

    override val root = hbox {
        addClass(Styles.itemRoot)

        label(relation) {
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
        }
        textfield(relation) {
            hgrow = Priority.ALWAYS
            removeWhen { editingProperty.not() }
            whenVisible { requestFocus() }
            action { if (text.trim().isNotEmpty()) commitEdit(item) }
            promptText = "Relation"
            required()
        }
        button(graphic = Styles.closeIcon()) {
            removeWhen { parent.hoverProperty().not().or(editingProperty) }
            action { viewModel.removeRelation(item) }
        }
    }
}