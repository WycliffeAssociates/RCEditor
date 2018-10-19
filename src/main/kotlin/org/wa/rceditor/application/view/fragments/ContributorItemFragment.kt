package org.wa.rceditor.application.view.fragments

import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.ContributorItem
import org.wa.rceditor.application.model.ContributorItemModel
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class ContributorItemFragment: ListCellFragment<ContributorItem>() {
    private val viewModel by inject<MainViewModel>()
    private val contributor = ContributorItemModel(itemProperty)

    override val root = hbox {
        addClass(Styles.itemRoot)

        label(contributor.text) {
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
        }
        textfield(contributor.text) {
            hgrow = Priority.ALWAYS
            removeWhen { editingProperty.not() }
            whenVisible { requestFocus() }
            action { if (text.trim().isNotEmpty()) commitEdit(item) }
            promptText = "Contributor name"
            required()
        }
        button(graphic = Styles.closeIcon()) {
            removeWhen { parent.hoverProperty().not().or(editingProperty) }
            action { viewModel.removeContributor(item) }
        }
    }
}