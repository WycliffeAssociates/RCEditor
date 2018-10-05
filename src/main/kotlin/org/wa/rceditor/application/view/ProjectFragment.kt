package org.wa.rceditor.application.view

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXTextField
import javafx.scene.layout.VBox
import org.wa.rceditor.application.Styles
import org.wycliffeassociates.resourcecontainer.entity.Project
import tornadofx.*

class ProjectFragment: Fragment() {
    override val root = VBox()
    val project: Project? by param()

    init {
        if (project != null) {
            println(project)
        }

        with(root) {
            form {
                fieldset {
                    field {
                        this += JFXTextField().apply {
                            promptText = "Title"
                            addClass(Styles.prompt)
                            isLabelFloat = true
                        }
                    }
                    field {
                        this += JFXTextField().apply {
                            promptText = "Versification"
                            addClass(Styles.prompt)
                            isLabelFloat = true
                        }
                    }
                    field {
                        this += JFXTextField().apply {
                            promptText = "Identifier"
                            addClass(Styles.prompt)
                            isLabelFloat = true
                        }
                    }
                    field {
                        this += JFXTextField().apply {
                            promptText = "Sort"
                            addClass(Styles.prompt)
                            isLabelFloat = true
                        }
                    }
                    field {
                        this += JFXTextField().apply {
                            promptText = "Path"
                            addClass(Styles.prompt)
                            isLabelFloat = true
                        }
                    }
                    field {
                        this += JFXTextField().apply {
                            promptText = "Categories"
                            addClass(Styles.prompt)
                            isLabelFloat = true
                        }
                    }

                    field {
                        this += JFXButton("Save")
                    }
                }
            }
        }
    }
}