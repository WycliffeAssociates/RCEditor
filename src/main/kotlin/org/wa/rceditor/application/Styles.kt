package org.wa.rceditor.application

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val promptColor by cssproperty<Color>("-fx-prompt-text-fill")

        val heading by cssclass()
        val prompt by cssclass()
        val itemRoot by cssclass()
        val closeIcon by cssclass()
        val contentLabel by cssid()
        val addItemRoot by cssclass()
        val boldLabel by cssclass()

        fun closeIcon() = FontAwesomeIconView(FontAwesomeIcon.CLOSE).apply {
            glyphSize = 22
            addClass(closeIcon)
        }
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
        closeIcon {
            fill = c("#cc9a9a")

            and(hover) {
                fill = c("#af5b5e")
            }
        }
        itemRoot {
            padding = box(8.px)
            button {
                backgroundColor += c("transparent")
                padding = box(-2.px)
            }
            alignment = Pos.CENTER_LEFT
        }
        contentLabel {
            fontSize = 1.2.em
        }
        addItemRoot {
            padding = box(.5.em)
            textField {
                prefWidth = 200.px
            }
        }
        boldLabel {
            fontWeight = FontWeight.BOLD
        }
    }
}