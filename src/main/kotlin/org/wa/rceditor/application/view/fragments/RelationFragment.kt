package org.wa.rceditor.application.view.fragments

import com.jfoenix.controls.JFXListView
import com.jfoenix.controls.JFXTextField
import javafx.event.EventHandler
import javafx.scene.control.ListView
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import tornadofx.*

class RelationFragment: Fragment("Relation") {
    override val root = VBox()
    var listView: JFXListView<String> by singleAssign()

    init {
        with(root) {
            prefWidth = 400.0
            padding = insets(5.0)
            spacing = 5.0

            this += JFXTextField().apply {
                vboxConstraints { marginTop = 10.0 }
                addClass(Styles.addItemRoot)
                promptText = "Relation"
                isLabelFloat = true
                action {
                    if (text.trim().isNotEmpty()) {
                        listView.items.add(text.trim())
                        clear()
                    }
                }
            }

            this += JFXListView<String>().apply {
                vboxConstraints { marginTop = 10.0 }
                minHeight = 400.0
                listView = this
                isEditable = true
                vgrow = Priority.ALWAYS
                cellFragment(StringCell::class)
                onEditCommit = EventHandler<ListView.EditEvent<String>> {
                    items[it.index] = it.newValue
                }
            }
        }
    }
}