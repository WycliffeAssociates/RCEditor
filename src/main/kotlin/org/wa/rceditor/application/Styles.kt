package org.wa.rceditor.application

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val promptColor by cssproperty<Color>("-fx-prompt-text-fill")

        val heading by cssclass()
        val prompt by cssclass()
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
        select(form) {
            padding = box(15.px)
        }
        prompt {
            promptColor.value = c("#622")
        }
    }
}