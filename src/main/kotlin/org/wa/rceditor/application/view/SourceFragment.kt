package org.wa.rceditor.application.view

import javafx.geometry.Pos
import javafx.scene.control.ListView
import javafx.scene.layout.VBox
import org.wa.rceditor.application.viewmodel.MainViewModel
import org.wycliffeassociates.resourcecontainer.entity.Source
import tornadofx.*

class SourceFragment: Fragment("Source") {
    override val root = VBox()
    private val viewModel by inject<MainViewModel>()
    private lateinit var listView: ListView<Source>

    init {
        with(root) {
            prefWidth = 620.0
            prefHeight = 400.0

            form {
                fieldset {
                    field {
                        listView = listview(viewModel.sourceProperty.value) {
                            minHeight = 100.0
                            prefHeight = 300.0
                            cellFormat {
                                graphic = hbox {
                                    spacing = 5.0
                                    textfield(it.identifier) {
                                        promptText = "Identifier"
                                        focusedProperty().onChange {
                                            if (it) {
                                                listView.selectionModel.select(index)
                                            } else {
                                                viewModel
                                                        .sourceProperty
                                                        .value[listView.selectionModel.selectedIndex].identifier = text
                                            }
                                        }
                                    }
                                    textfield(it.language) {
                                        promptText = "Language"
                                        focusedProperty().onChange {
                                            if (it) {
                                                listView.selectionModel.select(index)
                                            } else {
                                                viewModel
                                                        .sourceProperty
                                                        .value[listView.selectionModel.selectedIndex].language = text
                                            }
                                        }
                                    }
                                    textfield(it.version) {
                                        promptText = "Version"
                                        focusedProperty().onChange {
                                            if (it) {
                                                listView.selectionModel.select(index)
                                            } else {
                                                viewModel
                                                        .sourceProperty
                                                        .value[listView.selectionModel.selectedIndex].version = text
                                            }
                                        }
                                    }

                                    button("X") {
                                        action {
                                            viewModel.sourceProperty.value.remove(it)
                                        }
                                    }

                                    alignment = Pos.CENTER_LEFT
                                }
                            }
                        }
                    }
                }

                fieldset {
                    field {
                        vboxConstraints {
                            marginTop = 10.0
                        }
                        button("Add") {
                            action {
                                viewModel.sourceProperty.value.add(Source())
                            }
                        }
                        button("Save") {
                            action {
                                close()
                            }
                        }
                    }
                }
            }
        }
    }
}