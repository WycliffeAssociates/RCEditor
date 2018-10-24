package org.wa.rceditor.application.view.fragments

import javafx.scene.layout.Priority
import javafx.util.converter.NumberStringConverter
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.ProjectItem
import org.wa.rceditor.application.model.ProjectItemModel
import tornadofx.*

class ProjectCell: ListCellFragment<ProjectItem>() {
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
                field("Title") {
                    textfield(project.title) {
                        hgrow = Priority.ALWAYS
                        removeWhen { editingProperty.not() }
                        whenVisible { requestFocus() }
                        action {
                            project.commit()
                            commitEdit(item)
                        }
                    }
                }
                field("Versification") {
                    textfield(project.versification) {
                        hgrow = Priority.ALWAYS
                        action {
                            project.commit()
                            commitEdit(item)
                        }
                    }
                }
                field("Identifier") {
                    textfield(project.identifier) {
                        hgrow = Priority.ALWAYS
                        action {
                            project.commit()
                            commitEdit(item)
                        }
                    }
                }
                field("Sort") {
                    textfield(project.sort, NumberStringConverter()) {
                        filterInput { it.controlNewText.isInt() }
                        hgrow = Priority.ALWAYS
                        action {
                            project.commit()
                            commitEdit(item)
                        }
                    }
                }
                field("Path") {
                    textfield(project.path) {
                        hgrow = Priority.ALWAYS
                        action {
                            project.commit()
                            commitEdit(item)
                        }
                    }
                }
                field("Category") {
                    textfield(project.category) {
                        hgrow = Priority.ALWAYS
                        action {
                            project.commit()
                            commitEdit(item)
                        }
                    }
                }
            }
        }

        button(graphic = Styles.closeIcon()) {
            removeWhen { parent.hoverProperty().not().or(editingProperty) }
            action { cell?.listView?.items?.remove(item) }
        }
    }
}