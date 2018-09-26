package org.wa.resconteditor.application.app.model

import kotlin.system.exitProcess

class MainModel {

    fun onNewDocumentSelected() {
        println("New document created")
    }

    fun onOpenDocumentSelected() {
        println("Document Opened")
    }

    fun onSaveDocumentSelected() {
        println("Document Saved")
    }

    fun onAppQuitSelected() {
        exitProcess(-1)
    }
}