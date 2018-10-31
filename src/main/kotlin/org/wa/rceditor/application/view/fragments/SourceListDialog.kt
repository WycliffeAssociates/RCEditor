package org.wa.rceditor.application.view.fragments

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXListView
import com.jfoenix.controls.JFXTextField
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.SourceItem
import org.wa.rceditor.application.model.SourceItemModel
import tornadofx.*

class SourceListDialog(listItems: ObservableList<SourceItem>): VBox() {
    var listView: JFXListView<SourceItem> by singleAssign()
    private val model = SourceItemModel(SimpleObjectProperty(SourceItem()))

    init {
        prefWidth = 620.0
        padding = tornadofx.insets(5.0)
        spacing = 5.0

        hbox {
            vboxConstraints {
                marginTop = 10.0
            }
            spacing = 5.0

            this += JFXTextField().apply {
                hgrow = Priority.ALWAYS
                bind(model.identifier)
                addClass(Styles.addItemRoot)
                required()

                promptText = "Identifier"
                isLabelFloat = true

                action { commitAll() }
            }

            this += JFXTextField().apply {
                hgrow = Priority.ALWAYS
                bind(model.language)
                addClass(Styles.addItemRoot)
                required()

                promptText = "Language"
                isLabelFloat = true

                action { commitAll() }
            }

            this += JFXTextField().apply {
                hgrow = Priority.ALWAYS
                bind(model.version)
                addClass(Styles.addItemRoot)
                required()

                promptText = "Version"
                isLabelFloat = true

                action { commitAll() }
            }

            this += JFXButton("Add").apply {
                hgrow = Priority.ALWAYS
                prefWidth = 90.0
                action { commitAll() }
            }
        }

        this += JFXListView<SourceItem>().apply {
            vboxConstraints { marginTop = 10.0 }
            minHeight = 400.0
            items = listItems
            listView = this
            isEditable = true
            vgrow = Priority.ALWAYS
            cellFragment(fragment = SourceCell::class)
        }
    }

    fun commitAll() {
        if (model.isValid) {
            model.commit()
            listView.items.add(model.item)
            model.item = SourceItem()
            model.clearDecorators()
        }
    }
}