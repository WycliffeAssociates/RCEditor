package org.wa.rceditor.application.view

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXListView
import javafx.collections.ObservableList
import javafx.scene.layout.VBox
import tornadofx.*

class ContributorFragment: Fragment() {
    override val root = VBox()
    val contributors: ObservableList<String>? by param()

    init {
        if (contributors != null) {
            println(contributors)
        }

        with(root) {
            prefWidth = 400.0
            prefHeight = 600.0
            form {
                fieldset {
                    field {
                        val jfxlist = JFXListView<String>()
                        jfxlist.apply {
                            minHeight = 100.0
                            maxHeight = 300.0
                            items = contributors
                            setCellFactory {
                                val cell = StringCell()
                                cell.button.action {
                                    contributors?.remove(cell.item)
                                }
                                cell
                            }
                        }

                        this += jfxlist
                    }
                }

                fieldset {
                    field {
                        vboxConstraints {
                            marginTop = 20.0
                        }
                        this += JFXButton("Add").apply {
                            action {
                                contributors?.add("")
                            }
                        }
                        this += JFXButton("Save").apply {
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