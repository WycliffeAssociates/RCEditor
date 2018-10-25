package org.wa.rceditor.application.view.fragments

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.scene.control.ListView
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import tornadofx.*

class RelationFragment: Fragment("Relation") {
    override val root = VBox()
    var listView: ListView<StringProperty> by singleAssign()

    init {
        with(root) {
            prefWidth = 400.0
            padding = insets(5.0)
            spacing = 5.0

            textfield {
                addClass(Styles.addItemRoot)
                promptText = "Relation"
                action {
                    if (text.trim().isNotEmpty()) {
                        listView.items.add(SimpleStringProperty(text.trim()))
                        clear()
                    }
                }
            }

            listView = listview {
                isEditable = true
                vgrow = Priority.ALWAYS
                cellFragment(StringCell::class)
            }
        }
    }
}