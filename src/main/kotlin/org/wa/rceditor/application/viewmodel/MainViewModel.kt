package org.wa.rceditor.application.viewmodel

import javafx.collections.ObservableList
import org.wa.rceditor.application.model.*
import org.wycliffeassociates.resourcecontainer.entity.Project
import tornadofx.*

class MainViewModel: ViewModel() {
    private val model = MainModel()

    // ------------- Properties -------------- //

    var conformstoProperty = bind { model.conformstoProperty }
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
    var rightsProperty = bind { model.rightsProperty }
    var subjectProperty = bind { model.subjectProperty }
    var titleProperty = bind { model.titleProperty }
    var typeProperty = bind { model.typeProperty }
    var versionProperty = bind { model.versionProperty }
    var checkingLevelProperty = bind { model.checkingLevelProperty }

    var directoryLoadedProperty = bind { model.directoryLoadedProperty }

    // ------------ Functions -------------- //

    fun contributors(): ObservableList<ContributorItem> {
        return model.contributors()
    }

    fun addContributor(text: String) {
        model.addContributor(text)
    }

    fun removeContributor(item: ContributorItem) {
        model.removeContributor(item)
    }

    fun relations(): ObservableList<RelationItem> {
        return model.relations()
    }

    fun addRelation(text: String) {
        model.addRelation(text)
    }

    fun removeRelation(item: RelationItem) {
        model.removeRelation(item)
    }

    fun sources(): ObservableList<SourceItem> {
        return model.sources()
    }

    fun addSource(identifier: String, language: String, version: String) {
        model.addSource(identifier, language, version)
    }

    fun removeSource(item: SourceItem) {
        model.removeSource(item)
    }

    fun checkingEntities(): ObservableList<CheckingEntityItem> {
        return model.checkingEntities()
    }

    fun addCheckingEntity(text: String) {
        model.addCheckingEntity(text)
    }

    fun removeCheckingEntity(item: CheckingEntityItem) {
        model.removeCheckingEntity(item)
    }

    fun projects(): ObservableList<ProjectItem> {
        return model.projects()
    }

    fun addProject(title: String, versification: String, identifier: String, sort: Int, path: String, category: String) {
        model.addProject(title, versification, identifier, sort, path, category)
    }

    fun removeProject(item: ProjectItem) {
        model.removeProject(item)
    }

    // ------------- Handlers ---------------- //

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
}