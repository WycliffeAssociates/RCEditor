package org.wa.rceditor.application.view.fragments

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXListView
import com.jfoenix.controls.JFXTextField
import javafx.collections.ObservableList
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import tornadofx.*

class StringListDialog(prompt: String = "Add value", listItems: ObservableList<String>): VBox() {
    var listView: JFXListView<String> by singleAssign()
    var titleField: JFXTextField by singleAssign()

    init {
        prefWidth = 400.0
        spacing = 15.0

        hbox {
            this += JFXTextField().apply {
                hgrow = Priority.ALWAYS
                addClass(Styles.addItemRoot)
                titleField = this
                promptText = prompt
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
                    if (titleField.text.trim().isNotEmpty()) {
                        listView.items.add(titleField.text.trim())
                        clear()
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
    }
}