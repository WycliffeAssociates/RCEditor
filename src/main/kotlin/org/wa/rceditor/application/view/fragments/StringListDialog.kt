package org.wa.rceditor.application.view.fragments

import com.jfoenix.controls.*
import javafx.collections.ObservableList
import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import tornadofx.*

class StringListDialog(title: String, listItems: ObservableList<String>): JFXDialog() {
    var listView: JFXListView<String> by singleAssign()
    var textField: JFXTextField by singleAssign()

    init {
        content = JFXDialogLayout().apply {
            setHeading(label(title))
            setBody(vbox {
                spacing = 15.0

                hbox {
                    this += JFXTextField().apply {
                        hgrow = Priority.ALWAYS
                        addClass(Styles.addItemRoot)
                        textField = this
                        this.promptText = title
                        isLabelFloat = true
                        action {
                            if (text.trim().isNotEmpty()) {
                                listView.items.add(text.trim())
                                clear()
                            }
                        }
                        isFocusTraversable = true
                    }
                    this += JFXButton("Add").apply {
                        action {
                            if (textField.text.trim().isNotEmpty()) {
                                listView.items.add(textField.text.trim())
                                textField.clear()
                            }
                        }
                    }
                }

                this += JFXListView<String>().apply {
                    minHeight = 400.0
                    items = listItems
                    listView = this
                    isEditable = true
                    vgrow = Priority.ALWAYS
                    cellFragment(fragment = StringCell::class)
                    setOnEditCommit {
                        items[it.index] = it.newValue
                    }
                }
            })
            setActions(JFXButton("Close").apply {
                action { close() }
            })
        }
        transitionType = JFXDialog.DialogTransition.LEFT
        isOverlayClose = false

        setOnDialogOpened {
            textField.requestFocus()
        }
    }
}