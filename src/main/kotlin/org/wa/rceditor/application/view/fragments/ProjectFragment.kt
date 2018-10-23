package org.wa.rceditor.application.view.fragments

import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class ProjectFragment: Fragment("Project") {
    override val root = VBox()
    private val viewModel by inject<MainViewModel>()

    private var titleField by singleAssign<TextField>()
    private var identifierField by singleAssign<TextField>()
    private var sortField by singleAssign<TextField>()
    private var pathField by singleAssign<TextField>()
    private var versificationField by singleAssign<TextField>()
    private var categoryField by singleAssign<TextField>()

    init {
        with(root) {
            prefWidth = 400.0
            padding = insets(5.0)
            spacing = 5.0

            vbox {
                label("Title:") {
                    addClass(Styles.boldLabel)
                }
                titleField = textfield {
                    addClass(Styles.addItemRoot)
                }
            }
            vbox {
                label("Identifier:") {
                    addClass(Styles.boldLabel)
                }
                identifierField = textfield {
                    addClass(Styles.addItemRoot)
                }
            }
            vbox {
                label("Sort:") {
                    addClass(Styles.boldLabel)
                }
                sortField = textfield {
                    addClass(Styles.addItemRoot)
                    filterInput { it.controlNewText.isInt() }
                }
            }

            vbox {
                label("Versification:") {
                    addClass(Styles.boldLabel)
                }
                versificationField = textfield {
                    addClass(Styles.addItemRoot)
                }
            }
            vbox {
                label("Path:") {
                    addClass(Styles.boldLabel)
                }
                pathField = textfield {
                    addClass(Styles.addItemRoot)
                }
            }
            vbox {
                label("Category:") {
                    addClass(Styles.boldLabel)
                }
                categoryField = textfield {
                    addClass(Styles.addItemRoot)
                }
            }

            button("Add") {
                vboxConstraints {
                    padding = insets(15.0, 7.0)
                }
                action {
                    if (titleField.text.trim().isNotEmpty()
                            and identifierField.text.trim().isNotEmpty()
                            and sortField.text.trim().isNotEmpty()
                            and (sortField.text.trim().toInt() <= 66)
                            and versificationField.text.trim().isNotEmpty()
                            and pathField.text.trim().isNotEmpty()
                            and categoryField.text.trim().isNotEmpty()) {
                        viewModel.addProject(
                                titleField.text.trim(),
                                versificationField.text.trim(),
                                identifierField.text.trim(),
                                sortField.text.trim().toInt(),
                                pathField.text.trim(),
                                categoryField.text.trim()
                        )
                        titleField.clear()
                        versificationField.clear()
                        identifierField.clear()
                        sortField.clear()
                        pathField.clear()
                        categoryField.clear()

                        close()
                    }
                }
            }
        }
    }
}