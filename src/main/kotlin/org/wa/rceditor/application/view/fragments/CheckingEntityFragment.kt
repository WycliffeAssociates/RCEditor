package org.wa.rceditor.application.view.fragments

import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class CheckingEntityFragment: Fragment("Checking Entity") {
    override val root = VBox()
    private val viewModel by inject<MainViewModel>()

    init {
        with(root) {
            prefWidth = 400.0
            padding = insets(5.0)
            spacing = 5.0

            textfield {
                addClass(Styles.addItemRoot)
                promptText = "Checking Entity"
                action {
                    viewModel.addCheckingEntity(text)
                    clear()
                }
            }

            listview(viewModel.checkingEntities()) {
                isEditable = true
                cellFragment(CheckingEntityItemFragment::class)
            }
        }
    }
}