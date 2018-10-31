package org.wa.rceditor.application

import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.effect.DropShadow
import javafx.scene.effect.Effect
import javafx.scene.paint.Color
import javafx.scene.shape.StrokeType
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val itemRoot by cssclass()
        val closeIcon by cssclass()
        val addItemRoot by cssclass()
        val boldLabel by cssclass()
        val menuIcon by cssclass()
        val addIcon by cssclass()
        val menuButton by cssclass()
        val addButton by cssclass()

        val contentLabel by cssid()

        fun closeIcon() = MaterialIconView(MaterialIcon.CLOSE).apply {
            glyphSize = 22
            addClass(closeIcon)
        }

        fun newFileIcon() = MaterialIconView(MaterialIcon.CREATE_NEW_FOLDER).apply {
            glyphSize = 32
            addClass(menuIcon)
        }

        fun openFileIcon() = MaterialIconView(MaterialIcon.FOLDER_OPEN).apply {
            glyphSize = 32
            addClass(menuIcon)
        }

        fun saveFileIcon() = MaterialIconView(MaterialIcon.SAVE).apply {
            glyphSize = 32
            addClass(menuIcon)
        }

        fun quitIcon() = MaterialIconView(MaterialIcon.EXIT_TO_APP).apply {
            glyphSize = 32
            addClass(menuIcon)
        }

        fun addIcon() = MaterialIconView(MaterialIcon.ADD).apply {
            glyphSize = 32
            addClass(addIcon)
        }
    }

    init {
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
        closeIcon {
            fill = c("#cc5053")
            fontWeight = FontWeight.BOLD
            cursor = Cursor.HAND

            and(hover) {
                fill = c("#af0100")
            }
        }
        menuIcon {
            fill = c("#000")
            cursor = Cursor.HAND

            and(hover) {
                fill = c("#f00")
            }
        }
        menuButton {
            cursor = Cursor.HAND
        }
        addIcon {
            fill = c("#fff")
        }
        addButton {
            backgroundColor += c("#94008B")
            backgroundRadius += box(25.px)
            borderRadius += box(25.px)
            minHeight = 50.px
            minWidth = 50.px
            maxHeight = 50.px
            maxWidth = 50.px
            cursor = Cursor.HAND
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
        tabHeaderBackground {
            backgroundColor += c("#94008B")
        }
        /*listCell {
            and(selected) {
                backgroundColor += c("#94008B")
            }
        }*/
    }
}