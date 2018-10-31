package org.wa.rceditor.application.view.fragments

import com.jfoenix.controls.JFXListView
import com.jfoenix.controls.JFXTextField
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.ProjectItem
import org.wa.rceditor.application.model.ProjectItemModel
import tornadofx.*

class ProjectFragment: Fragment("Project") {
    override val root = VBox()
    val listView: JFXListView<ProjectItem> by param()

    private val model = ProjectItemModel(SimpleObjectProperty(ProjectItem()))

    init {
        with(root) {
            minWidth = 400.0
            padding = insets(15.0, 5.0, 10.0, 5.0)
            spacing = 15.0

            this += JFXTextField().apply {
                bind(model.title)
                addClass(Styles.addItemRoot)
                required()

                promptText = "Title"
                isLabelFloat = true

                action { commitAll() }
            }

            this += JFXTextField().apply {
                bind(model.identifier)
                addClass(Styles.addItemRoot)
                required()

                promptText = "Identifier"
                isLabelFloat = true

                action { commitAll() }
            }

            this += JFXTextField().apply {
                bind(model.sort)
                addClass(Styles.addItemRoot)
                filterInput { it.controlNewText.isInt() }
                validator {
                    if ((it?.matches("^(6[0-6]|[1-5][0-9]|[1-9])$".toRegex()))!!.not()) {
                        error("This value should be from 1 to 66")
                    } else {
                        null
                    }
                }

                promptText = "Sort"
                isLabelFloat = true

                action { commitAll() }
            }

            this += JFXTextField().apply {
                bind(model.versification)
                addClass(Styles.addItemRoot)
                required()

                promptText = "Versification"
                isLabelFloat = true

                action { commitAll() }
            }

            this += JFXTextField().apply {
                bind(model.path)
                addClass(Styles.addItemRoot)
                required()

                promptText = "Path"
                isLabelFloat = true

                action { commitAll() }
            }

            this += JFXTextField().apply {
                bind(model.category)
                addClass(Styles.addItemRoot)
                required()

                promptText = "Category"
                isLabelFloat = true

                action { commitAll() }
            }
        }
    }

    fun commitAll() {
        if(model.isValid) {
            model.commit()
            listView.items.add(model.item)
            close()
        } else {
            model.validate()
        }
    }
}