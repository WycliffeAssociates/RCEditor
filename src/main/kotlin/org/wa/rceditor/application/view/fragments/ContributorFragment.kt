package org.wa.rceditor.application.view.fragments

import javafx.event.EventHandler
import javafx.scene.control.ListView
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import tornadofx.*

class ContributorFragment: Fragment("Contributor") {
    override val root = VBox()
    var listView: ListView<String> by singleAssign()

    init {
        with(root) {
            prefWidth = 500.0
            padding = insets(5.0)
            spacing = 5.0

            textfield {
                addClass(Styles.addItemRoot)
                promptText = "Contributor name"
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
                setOnEditCommit {
                    items[it.index] = it.newValue
                }
            }
        }
    }
}