package org.wa.rceditor.application.view.fragments

import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*

class ContributorFragment: Fragment("Contributor") {
    override val root = VBox()
    private val viewModel by inject<MainViewModel>()

    init {
        with(root) {
            prefWidth = 400.0
            padding = insets(5.0)
            spacing = 5.0

            textfield {
                addClass(Styles.addItemRoot)
                promptText = "Contributor name"
                action {
                    if (text.trim().isNotEmpty()) {
                        viewModel.addContributor(text.trim())
                        clear()
                    }
                }
            }
            listview(viewModel.contributors()) {
                isEditable = true
                vgrow = Priority.ALWAYS
                cellFragment(ContributorCell::class)
            }
        }
    }
}