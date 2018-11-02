package org.wa.rceditor.application.view.fragments

import com.jfoenix.controls.JFXTextField
import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.ProjectItem
import org.wa.rceditor.application.model.ProjectItemModel
import tornadofx.*

class ProjectCell: ListCellFragment<ProjectItem>() {
    private val model = ProjectItemModel(itemProperty)

    override val root = hbox {
        addClass(Styles.itemRoot)
        label(model.title) {
            setId(Styles.contentLabel)
            hgrow = Priority.ALWAYS
            useMaxSize = true
            removeWhen { editingProperty }
        }
        form {
            removeWhen { editingProperty.not() }
            hgrow = Priority.ALWAYS
            fieldset {
                spacing = 10.0
                field {
                    this += JFXTextField().apply {
                        hgrow = Priority.ALWAYS
                        bind(model.title)
                        removeWhen { editingProperty.not() }
                        whenVisible { requestFocus() }
                        required()
                        action {
                            model.commit { commitEdit(item) }
                        }

                        promptText = "Title"
                        isLabelFloat = true
                    }
                }
                field {
                    this += JFXTextField().apply {
                        hgrow = Priority.ALWAYS
                        bind(model.versification)
                        required()
                        action {
                            model.commit { commitEdit(item) }
                        }

                        promptText = "Versification"
                        isLabelFloat = true
                    }
                }
                field {
                    this += JFXTextField().apply {
                        hgrow = Priority.ALWAYS
                        bind(model.identifier)
                        required()
                        action {
                            model.commit { commitEdit(item) }
                        }

                        promptText = "Identifier"
                        isLabelFloat = true
                    }
                }
                field {
                    this += JFXTextField().apply {
                        hgrow = Priority.ALWAYS
                        bind(model.sort)
                        filterInput { it.controlNewText.isInt() }
                        validator {
                            if ((it?.matches("^(6[0-6]|[1-5][0-9]|[1-9])$".toRegex()))!!.not()) {
                                error("This value should be from 1 to 66")
                            } else {
                                null
                            }
                        }
                        action {
                            model.commit { commitEdit(item) }
                        }

                        promptText = "Sort"
                        isLabelFloat = true
                    }
                }
                field {
                    this += JFXTextField().apply {
                        hgrow = Priority.ALWAYS
                        bind(model.path)
                        required()
                        action {
                            model.commit { commitEdit(item) }
                        }

                        promptText = "Path"
                        isLabelFloat = true
                    }
                }
                field {
                    this += JFXTextField().apply {
                        hgrow = Priority.ALWAYS
                        bind(model.category)
                        required()
                        action {
                            model.commit { commitEdit(item) }
                        }

                        promptText = "Category"
                        isLabelFloat = true
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