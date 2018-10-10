package org.wa.rceditor.application.view

import javafx.scene.layout.VBox
import org.wycliffeassociates.resourcecontainer.entity.Project
import tornadofx.*

class ProjectFragment: Fragment("Project") {
    override val root = VBox()
    val project: Project? by param()

    init {
        if (project != null) {
            println(project)
        }

        with(root) {
            form {
                fieldset {
                    field("Title") {
                        textfield {  }
                    }
                    field("Versification") {
                        textfield {  }
                    }
                    field("Identifier") {
                        textfield {  }
                    }
                    field("Sort") {
                        textfield {  }
                    }
                    field("Path") {
                        textfield {  }
                    }
                    field("Categories") {
                        textfield {  }
                    }

                    field {
                        button("Save") {  }
                    }
                }
            }
        }
    }
}