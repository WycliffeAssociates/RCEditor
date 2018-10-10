package org.wa.rceditor.application.viewmodel

import org.wa.rceditor.application.model.MainModel
import org.wycliffeassociates.resourcecontainer.entity.Project
import tornadofx.*

class MainViewModel: ViewModel() {
    private val model = MainModel()

    // ------------- Properties -------------- //

    var conformstoProperty = bind { model.conformstoProperty }
    var contributorProperty = bind { model.contributorProperty }
    var creatorProperty = bind { model.creatorProperty }
    var descriptionProperty = bind { model.descriptionProperty }
    var formatProperty = bind { model.formatProperty }
    var identifierProperty = bind { model.identifierProperty }
    var issuedProperty = bind { model.issuedProperty }
    var modifiedProperty = bind { model.modifiedProperty }
    var languageDirectionProperty = bind { model.languageDirectionProperty }
    var languageIdentifierProperty = bind { model.languageIdentifierProperty }
    var languageTitleProperty = bind { model.languageTitleProperty }
    var publisherProperty = bind { model.publisherProperty }
    var relationProperty = bind { model.relationProperty }
    var rightsProperty = bind { model.rightsProperty }
    var sourceProperty = bind { model.sourceProperty }
    var subjectProperty = bind { model.subjectProperty }
    var titleProperty = bind { model.titleProperty }
    var typeProperty = bind { model.typeProperty }
    var versionProperty = bind { model.versionProperty }
    var checkingEntityProperty = bind { model.checkingEntityProperty }
    var checkingLevelProperty = bind { model.checkingLevelProperty }
    var projectsProperty = bind { model.projectsProperty }


    // ------------ Functions -------------- //

    fun handleNewDocumentSelected() {
        model.onNewDocumentSelected()
    }

    fun handleOpenDocumentSelected() {
        model.onOpenDocumentSelected()
    }

    fun handleSaveDocumentSelected() {
        commit()
        model.onSaveDocumentSelected()
    }

    fun handleAppQuit() {
        model.onAppQuitSelected()
    }

    fun handleEditContributorsClick() {
        model.onEditContributorsClick()
    }

    fun handleEditProjectClick(project: Project) {
        model.onEditProjectClick(project)
    }

    fun handleAddProjectClick() {
        model.onAddProjectClick()
    }
}