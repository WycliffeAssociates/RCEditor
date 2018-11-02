package org.wa.rceditor.application.view.fragments

import com.jfoenix.controls.*
import javafx.beans.property.SimpleObjectProperty
import javafx.collections.ObservableList
import javafx.scene.layout.Priority
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.SourceItem
import org.wa.rceditor.application.model.SourceItemModel
import tornadofx.*

class SourceListDialog(listItems: ObservableList<SourceItem>): JFXDialog() {
    var listView: JFXListView<SourceItem> by singleAssign()
    var identifierTextField: JFXTextField by singleAssign()
    private val model = SourceItemModel(SimpleObjectProperty(SourceItem()))

    init {
        content = JFXDialogLayout().apply {
            setHeading(label("Source"))
            setBody(vbox {
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
                        identifierTextField = this
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
            })
            setActions(JFXButton("Close").apply {
                action { close() }
            })
        }
        transitionType = JFXDialog.DialogTransition.LEFT
        isOverlayClose = false

        setOnDialogOpened {
            identifierTextField.requestFocus()
        }
    }

    fun commitAll() {
        if (model.isValid) {
            model.commit()
            listView.items.add(model.item)
            model.item = SourceItem()
            model.clearDecorators()
            identifierTextField.requestFocus()
        }
    }
}