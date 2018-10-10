package org.wa.rceditor.application.view

import javafx.geometry.Pos
import javafx.scene.control.ListView
import javafx.scene.layout.VBox
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class CheckingEntityFragment: Fragment("Checking Entity") {
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
                        listView = listview(viewModel.checkingEntityProperty.value) {
                            minHeight = 100.0
                            prefHeight = 300.0
                            cellFormat {
                                graphic = hbox {
                                    textfield(it) {
                                        promptText = "Checking entity"
                                        prefWidth = 320.0
                                        focusedProperty().onChange {
                                            if (it) {
                                                listView.selectionModel.select(index)
                                            } else {
                                                viewModel
                                                        .checkingEntityProperty
                                                        .value[listView.selectionModel.selectedIndex] = text
                                            }
                                        }
                                    }

                                    button("X") {
                                        action {
                                            viewModel.checkingEntityProperty.value.remove(it)
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
                                viewModel.checkingEntityProperty.value.add("")
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