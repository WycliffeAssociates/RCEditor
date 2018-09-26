package org.wa.resconteditor.application.app.viewmodel

import org.wa.resconteditor.application.app.model.MainModel

class MainViewModel {
    private val model = MainModel()

    fun handleNewDocumentSelected() {
        model.onNewDocumentSelected()
    }

    fun handleOpenDocumentSelected() {
        model.onOpenDocumentSelected()
    }

    fun handleSaveDocumentSelected() {
        model.onSaveDocumentSelected()
    }

    fun handleAppQuit() {
        model.onAppQuitSelected()
    }
}