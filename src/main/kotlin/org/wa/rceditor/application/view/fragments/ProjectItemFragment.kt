package org.wa.rceditor.application.view.fragments

import javafx.scene.layout.Priority
import javafx.util.converter.NumberStringConverter
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.ProjectItem
import org.wa.rceditor.application.model.ProjectItemModel
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class ProjectItemFragment: ListCellFragment<ProjectItem>() {
    private val viewModel by inject<MainViewModel>()
    private val project = ProjectItemModel(itemProperty)

    override val root = hbox {
        addClass(Styles.itemRoot)
        label(project.title) {
            textProperty().bind(
                    stringBinding(project.title) {
                        "${if(project.title.value != "") project.title.value else "-----"}"
                    }
            )
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
        }
        form {
            removeWhen { editingProperty.not() }
            fieldset {
                field {
                    textfield(project.title) {
                        hgrow = Priority.ALWAYS
                        removeWhen { editingProperty.not() }
                        whenVisible { requestFocus() }
                        action { commitEdit(item) }
                        promptText = "Title"
                    }
                }
                field {
                    textfield(project.versification) {
                        hgrow = Priority.ALWAYS
                        action { commitEdit(item) }
                        promptText = "Versification"
                    }
                }
                field {
                    textfield(project.identifier) {
                        hgrow = Priority.ALWAYS
                        action { commitEdit(item) }
                        promptText = "Identifier"
                    }
                }
                field {
                    textfield(project.sort, NumberStringConverter()) {
                        filterInput { it.controlNewText.isInt() }
                        hgrow = Priority.ALWAYS
                        action { commitEdit(item) }
                        promptText = "Sort"
                    }
                }
                field {
                    textfield(project.path) {
                        hgrow = Priority.ALWAYS
                        action { commitEdit(item) }
                        promptText = "Path"
                    }
                }
                field {
                    textfield(project.category) {
                        hgrow = Priority.ALWAYS
                        action { commitEdit(item) }
                        promptText = "Category"
                    }
                }
            }
        }

        button(graphic = Styles.closeIcon()) {
            removeWhen { parent.hoverProperty().not().or(editingProperty) }
            action { viewModel.removeProject(item) }
        }
    }
}