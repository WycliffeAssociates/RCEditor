package org.wa.rceditor.application.view.fragments

import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.ListView
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.ProjectItem
import org.wa.rceditor.application.model.ProjectItemModel
import tornadofx.*

class ProjectFragment: Fragment("Project") {
    override val root = VBox()
    val listView: ListView<ProjectItem> by param()

    private val model = ProjectItemModel(SimpleObjectProperty(ProjectItem()))

    init {
        with(root) {
            prefWidth = 400.0
            padding = insets(5.0)
            spacing = 5.0

            vbox {
                label("Title:") {
                    addClass(Styles.boldLabel)
                }
                textfield(model.title) {
                    addClass(Styles.addItemRoot)
                }.required()
            }
            vbox {
                label("Identifier:") {
                    addClass(Styles.boldLabel)
                }
                textfield(model.identifier) {
                    addClass(Styles.addItemRoot)
                }.required()
            }
            vbox {
                label("Sort:") {
                    addClass(Styles.boldLabel)
                }
                textfield(model.sort) {
                    addClass(Styles.addItemRoot)
                    filterInput { it.controlNewText.isInt() }
                    validator {
                        if ((it?.matches("^(6[0-6]|[1-5][0-9]|[1-9])$".toRegex()))!!.not()) {
                            error("This value should be from 1 to 66")
                        } else {
                            null
                        }
                    }
                }
            }

            vbox {
                label("Versification:") {
                    addClass(Styles.boldLabel)
                }
                textfield(model.versification) {
                    addClass(Styles.addItemRoot)
                }.required()
            }
            vbox {
                label("Path:") {
                    addClass(Styles.boldLabel)
                }
                textfield(model.path) {
                    addClass(Styles.addItemRoot)
                }.required()
            }
            vbox {
                label("Category:") {
                    addClass(Styles.boldLabel)
                }
                textfield(model.category) {
                    addClass(Styles.addItemRoot)
                }.required()
            }

            button("Add") {
                vboxConstraints {
                    padding = insets(15.0, 7.0)
                }
                enableWhen(model.valid)
                action {
                    model.commit()
                    listView.items.add(model.item)
                    close()
                }
            }
        }
    }
}