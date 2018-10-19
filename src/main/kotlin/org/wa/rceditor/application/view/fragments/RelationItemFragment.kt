package org.wa.rceditor.application.view.fragments

import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.RelationItem
import org.wa.rceditor.application.model.RelationItemModel
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class RelationItemFragment: ListCellFragment<RelationItem>() {
    private val viewModel by inject<MainViewModel>()
    private val relation = RelationItemModel(itemProperty)

    override val root = hbox {
        addClass(Styles.itemRoot)

        label(relation.text) {
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
        }
        textfield(relation.text) {
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