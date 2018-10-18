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
                        textfield(project?.title) {  }
                    }
                    field("Versification") {
                        textfield(project?.versification) {  }
                    }
                    field("Identifier") {
                        textfield(project?.identifier) {  }
                    }
                    field("Sort") {
                        textfield(project?.sort.toString()) {  }
                    }
                    field("Path") {
                        textfield(project?.path) {  }
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