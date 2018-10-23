package org.wa.rceditor.application.view.fragments

import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class RelationFragment: Fragment("Relation") {
    override val root = VBox()
    private val viewModel by inject<MainViewModel>()

    init {
        with(root) {
            prefWidth = 400.0
            padding = insets(5.0)
            spacing = 5.0

            textfield {
                addClass(Styles.addItemRoot)
                promptText = "Relation"
                action {
                    viewModel.addRelation(text)
                    clear()
                }
            }

            listview(viewModel.relationsProperty.value) {
                isEditable = true
                vgrow = Priority.ALWAYS
                cellFragment(RelationCell::class)
            }
        }
    }
}