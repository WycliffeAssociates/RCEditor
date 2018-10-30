package org.wa.rceditor.application.view.fragments

import javafx.event.EventHandler
import javafx.scene.control.ListView
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import tornadofx.*

class CheckingEntityFragment: Fragment("Checking Entity") {
    override val root = VBox()
    var listView: ListView<String> by singleAssign()

    init {
        with(root) {
            prefWidth = 400.0
            padding = insets(5.0)
            spacing = 5.0

            textfield{
                addClass(Styles.addItemRoot)
                action {
                    if (text.trim().isNotEmpty()) {
                        listView.items.add(text.trim())
                        clear()
                    }
                }
            }

            listView = listview {
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