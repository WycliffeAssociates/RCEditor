package org.wa.rceditor.application.view

import javafx.geometry.Pos
import javafx.scene.control.ListCell
import javafx.scene.control.ListView
import javafx.scene.layout.VBox
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class ContributorFragment: Fragment("Contributor") {
    override val root = VBox()
    private val viewModel by inject<MainViewModel>()
    private lateinit var listView: ListView<String>

    init {
        with(root) {
            prefWidth = 400.0
            prefHeight = 400.0

            form {
                fieldset {
                    field {
                        listView = listview(viewModel.contributorProperty.value) {
                            minHeight = 100.0
                            prefHeight = 300.0
                            isEditable = true
                            cellFormat {
                                isEditable = true
                                graphic = hbox {
                                    textfield(it) {
                                        promptText = "Contributor name"
                                        prefWidth = 320.0
                                        setOnAction {
                                            println(it.target)
                                        }
                                        /*focusedProperty().onChange {
                                            if (it) {
                                                //listView.selectionModel.select(index)
                                                textProperty().bindBidirectional(itemProperty())
                                            } else {
                                                itemProperty().unbind()
                                                *//*viewModel
                                                        .contributorProperty
                                                        .value[listView.selectionModel.selectedIndex] = text*//*
                                            }
                                        }*/
                                    }

                                    button("X") {
                                        action {
                                            viewModel.contributorProperty.value.remove(it)
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
                                viewModel.contributorProperty.value.add("")
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