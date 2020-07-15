package org.wa.rceditor.application

import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import org.kordamp.ikonli.Ikon
import org.kordamp.ikonli.javafx.FontIcon
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

        fun closeIcon() = FontIcon("gmi-close").apply {
            addClass(closeIcon)
            iconSize = 22
        }

        fun newFileIcon() = FontIcon("gmi-create-new-folder").apply {
            addClass(menuIcon)
            iconSize = 32
        }

        fun openFileIcon() = FontIcon("gmi-folder-open").apply {
            addClass(menuIcon)
            iconSize = 32
        }

        fun saveFileIcon() = FontIcon("gmi-save").apply {
            addClass(menuIcon)
            iconSize = 32
        }

        fun quitIcon() = FontIcon("gmi-exit-to-app").apply {
            addClass(menuIcon)
            iconSize = 32
        }

        fun addIcon() = FontIcon("gmi-add").apply {
            addClass(addIcon)
            iconSize = 32
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
            prefWidth = 600.px

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
                    closeIcon {
                        fill = c("#fff")
                        and(hover) {
                            fill = c("#ff0")
                        }
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
                closeIcon {
                    fill = c("#fff")
                    and(hover) {
                        fill = c("#ff0")
                    }
                }
            }
        }
    }
}
