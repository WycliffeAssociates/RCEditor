package org.wa.rceditor.application

import de.jensd.fx.glyphs.materialicons.MaterialIcon
import de.jensd.fx.glyphs.materialicons.MaterialIconView
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.paint.Color
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
        val dialogBody by cssclass("jfx-layout-body")
        val unfocusedColor by cssproperty<MultiValue<Color>>("-jfx-unfocus-color")

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
        }
        boldLabel {
            fontWeight = FontWeight.BOLD
        }
        tabHeaderBackground {
            backgroundColor += c("#94008B")
        }
        dialogBody {
            padding = box(10.px)
            prefWidth = 500.px

            listView {
                listCell {
                    label {
                        textFill = c("#000000")
                    }
                }
                listCell and selected {
                    label {
                        textFill = c("#ffffff")
                    }
                }
            }
        }
        listView {
            listCell and selected {
                backgroundColor += c("#f8e3f8")
            }
        }
        listView and focused {
            listCell and selected {
                backgroundColor += c("#94008b")
                textFill = c("#ffffff")
                textField {
                    textFill = c("#ffffff")
                    promptTextFill = c("#ffe300")
                    fontWeight = FontWeight.BOLD
                    unfocusedColor.value += c("#ffe300")
                }
            }
        }
    }
}