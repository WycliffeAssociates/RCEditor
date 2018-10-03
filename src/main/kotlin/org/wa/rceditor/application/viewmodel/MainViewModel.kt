package org.wa.rceditor.application.viewmodel

import org.wa.rceditor.application.model.MainModel

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

    fun handleEditContributorsClick() {
        model.onEditContributorsClick()
    }
}