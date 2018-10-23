package org.wa.rceditor.application.view.fragments

import javafx.scene.control.TextField
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class SourceFragment: Fragment("Source") {
    override val root = VBox()
    private val viewModel by inject<MainViewModel>()

    private var identifierField by singleAssign<TextField>()
    private var languageField by singleAssign<TextField>()
    private var versionField by singleAssign<TextField>()

    init {
        with(root) {
            prefWidth = 620.0
            padding = insets(5.0)
            spacing = 5.0

            hbox {
                spacing = 5.0
                vbox {
                    hgrow = Priority.ALWAYS
                    label("Identifier:") {
                        addClass(Styles.boldLabel)
                    }
                    identifierField = textfield {
                        addClass(Styles.addItemRoot)
                    }
                }

                vbox {
                    hgrow = Priority.ALWAYS
                    label("Language:") {
                        addClass(Styles.boldLabel)
                    }
                    languageField = textfield {
                        addClass(Styles.addItemRoot)
                    }
                }

                vbox {
                    hgrow = Priority.ALWAYS
                    label("Version:") {
                        addClass(Styles.boldLabel)
                    }
                    versionField = textfield {
                        addClass(Styles.addItemRoot)
                    }
                }

                button("Add") {
                    hgrow = Priority.ALWAYS
                    hboxConstraints {
                        marginTop = 15.0
                        padding = insets(15.0, 7.0)
                    }
                    action {
                        if (identifierField.text.trim().isNotEmpty()
                                and languageField.text.trim().isNotEmpty()
                                and versionField.text.trim().isNotEmpty()) {
                            viewModel.addSource(
                                    identifierField.text.trim(),
                                    languageField.text.trim(),
                                    versionField.text.trim()
                            )
                            identifierField.clear()
                            languageField.clear()
                            versionField.clear()
                        }
                    }
                }
            }

            listview(viewModel.sources()) {
                isEditable = true
                vgrow = Priority.ALWAYS
                cellFragment(SourceCell::class)
            }
        }
    }
}