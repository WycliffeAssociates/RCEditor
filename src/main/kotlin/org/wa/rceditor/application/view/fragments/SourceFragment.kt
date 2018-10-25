package org.wa.rceditor.application.view.fragments

import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.ListView
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.ProjectItem
import org.wa.rceditor.application.model.SourceItem
import org.wa.rceditor.application.model.SourceItemModel
import tornadofx.*

class SourceFragment: Fragment("Source") {
    override val root = VBox()
    var listView: ListView<SourceItem> by singleAssign()

    private val model = SourceItemModel(SimpleObjectProperty(SourceItem()))

    init {
        with(root) {
            prefWidth = 620.0
            padding = insets(5.0)
            spacing = 5.0

            hbox {
                spacing = 5.0
                vbox {
                    hgrow = Priority.ALWAYS
                    label("Identifier:") {
                        addClass(Styles.boldLabel)
                    }
                    textfield(model.identifier) {
                        addClass(Styles.addItemRoot)
                        required()
                    }
                }

                vbox {
                    hgrow = Priority.ALWAYS
                    label("Language:") {
                        addClass(Styles.boldLabel)
                    }
                    textfield(model.language) {
                        addClass(Styles.addItemRoot)
                        required()
                    }
                }

                vbox {
                    hgrow = Priority.ALWAYS
                    label("Version:") {
                        addClass(Styles.boldLabel)
                    }
                    textfield(model.version) {
                        addClass(Styles.addItemRoot)
                        required()
                    }
                }

                button("Add") {
                    hgrow = Priority.ALWAYS
                    hboxConstraints {
                        marginTop = 15.0
                        padding = insets(15.0, 7.0)
                    }
                    enableWhen(model.valid)
                    action {
                        model.commit()
                        listView.items.add(model.item)
                        model.item = SourceItem()
                        model.clearDecorators()
                    }
                }
            }

            listView = listview {
                isEditable = true
                vgrow = Priority.ALWAYS
                cellFragment(SourceCell::class)
            }
        }
    }
}