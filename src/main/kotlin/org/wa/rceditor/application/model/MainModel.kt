package org.wa.rceditor.application.model

import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.collections.ObservableList
import org.wa.rceditor.api.RCManagerImpl
import org.wa.rceditor.domain.MainUseCase
import org.wycliffeassociates.resourcecontainer.ResourceContainer
import tornadofx.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

class MainModel {

    // --------------- Properties ------------------ //

    private var conformsto: String by property()
    val conformstoProperty = getProperty(MainModel::conformsto)

    private var creator: String by property()
    val creatorProperty = getProperty(MainModel::creator)

    private var description: String by property()
    val descriptionProperty = getProperty(MainModel::description)

    private var format: String by property()
    val formatProperty = getProperty(MainModel::format)

    private var identifier: String by property()
    val identifierProperty = getProperty(MainModel::identifier)

    private var issued: LocalDate by property()
    val issuedProperty = getProperty(MainModel::issued)

    private var modified: LocalDate by property()
    val modifiedProperty = getProperty(MainModel::modified)

    private var languageDirection: String by property()
    val languageDirectionProperty = getProperty(MainModel::languageDirection)

    private var languageIdentifier: String by property()
    val languageIdentifierProperty = getProperty(MainModel::languageIdentifier)

    private var languageTitle: String by property()
    val languageTitleProperty = getProperty(MainModel::languageTitle)

    private var publisher: String by property()
    val publisherProperty = getProperty(MainModel::publisher)

    private var rights: String by property()
    val rightsProperty = getProperty(MainModel::rights)

    private var subject: String by property()
    val subjectProperty = getProperty(MainModel::subject)

    private var title: String by property()
    val titleProperty = getProperty(MainModel::title)

    private var type: String by property()
    val typeProperty = getProperty(MainModel::type)

    private var version: String by property()
    val versionProperty = getProperty(MainModel::version)

    private var checkingLevel: String by property()
    val checkingLevelProperty = getProperty(MainModel::checkingLevel)

    private val contributors = SortedFilteredList<ContributorItem>()
    private val relations = SortedFilteredList<RelationItem>()
    private val sources = SortedFilteredList<SourceItem>()
    private val checkingEntities = SortedFilteredList<CheckingEntityItem>()
    private val projects = SortedFilteredList<ProjectItem>()

    private var directoryLoaded: Boolean by property()
    val directoryLoadedProperty = getProperty(MainModel::directoryLoaded)

    lateinit var container: ResourceContainer

    // -------------- Functions --------------- //

    fun contributors(): ObservableList<ContributorItem> {
        return contributors
    }

    fun addContributor(text: String) {
        contributors.add(ContributorItem(text))
    }

    fun removeContributor(item: ContributorItem) {
        contributors.remove(item)
    }

    fun relations(): ObservableList<RelationItem> {
        return relations
    }

    fun addRelation(text: String) {
        relations.add(RelationItem(text))
    }

    fun removeRelation(item: RelationItem) {
        relations.remove(item)
    }

    fun sources(): ObservableList<SourceItem> {
        return sources
    }

    fun addSource(identifier: String, language: String, version: String) {
        sources.add(SourceItem(identifier, language, version))
    }

    fun removeSource(item: SourceItem) {
        sources.remove(item)
    }

    fun checkingEntities(): ObservableList<CheckingEntityItem> {
        return checkingEntities
    }

    fun addCheckingEntity(text: String) {
        checkingEntities.add(CheckingEntityItem(text))
    }

    fun removeCheckingEntity(item: CheckingEntityItem) {
        checkingEntities.remove(item)
    }

    fun projects(): ObservableList<ProjectItem> {
        return projects
    }

    fun addProject(title: String, versification: String, identifier: String, sort: Int, path: String, category: String) {
        projects.add(ProjectItem(title, versification, identifier, sort, path, category))
    }

    fun removeProject(item: ProjectItem) {
        projects.remove(item)
    }

    // ---------- Handlers --------- //

    fun onNewDocumentSelected() {
        MainUseCase(RCManagerImpl()).createResourceContainer()
                .observeOn(JavaFxScheduler.platform())
                .subscribeOn(Schedulers.io())
                /*.onErrorReturn {
                    println(it.message)
                }*/
                .subscribe {
                    container = it
                    clearData()
                    directoryLoaded = true
                }
    }

    fun onOpenDocumentSelected() {
        MainUseCase(RCManagerImpl()).openResourceContainer()
                .observeOn(JavaFxScheduler.platform())
                .subscribeOn(Schedulers.io())
                /*.onErrorReturn {
                    println(it.message)
                }*/
                .subscribe {
                    container = it
                    clearData()
                    directoryLoaded = true
                    loadRecourceContainer()
                }
    }

    fun onSaveDocumentSelected() {
        saveResourceContainer()
    }

    fun onAppQuitSelected() {
        exitProcess(-1)
    }

    fun loadRecourceContainer() {
        // TODO load on background thread

        conformsto = container.manifest.dublinCore.conformsTo
        creator = container.manifest.dublinCore.creator
        description = container.manifest.dublinCore.description
        format = container.manifest.dublinCore.format
        identifier = container.manifest.dublinCore.identifier
        issued = LocalDate.parse(container.manifest.dublinCore.issued, DateTimeFormatter.ISO_DATE)
        modified = LocalDate.parse(container.manifest.dublinCore.modified, DateTimeFormatter.ISO_DATE)
        languageDirection = container.manifest.dublinCore.language.direction
        languageIdentifier = container.manifest.dublinCore.language.identifier
        languageTitle = container.manifest.dublinCore.language.title
        publisher = container.manifest.dublinCore.publisher
        rights = container.manifest.dublinCore.rights
        subject = container.manifest.dublinCore.subject
        title = container.manifest.dublinCore.title
        type = container.manifest.dublinCore.type
        version = container.manifest.dublinCore.version
        checkingLevel = container.manifest.checking.checkingLevel

        container.manifest.dublinCore.contributor.forEach {
            addContributor(it)
        }

        container.manifest.dublinCore.relation.forEach {
            addRelation(it)
        }

        container.manifest.dublinCore.source.forEach {
            addSource(it.identifier, it.language, it.version)
        }

        container.manifest.checking.checkingEntity.forEach {
            addCheckingEntity(it)
        }

        container.manifest.projects.forEach {
            addProject(it.title, it.versification, it.identifier, it.sort, it.path,
                    if(it.categories.isNotEmpty()) it.categories.first() else "Unknown")
        }
    }

    fun saveResourceContainer() {
        if (validateData()) {
            container.manifest.dublinCore.conformsTo = conformsto
            container.manifest.dublinCore.creator = creator
            container.manifest.dublinCore.description = description
            container.manifest.dublinCore.format = format
            container.manifest.dublinCore.identifier = identifier
            container.manifest.dublinCore.issued = issued.toString()
            container.manifest.dublinCore.modified = modified.toString()
            container.manifest.dublinCore.language.direction = languageDirection
            container.manifest.dublinCore.language.identifier = languageIdentifier
            container.manifest.dublinCore.language.title = languageTitle
            container.manifest.dublinCore.publisher = publisher
            container.manifest.dublinCore.rights = rights
            container.manifest.dublinCore.subject = subject
            container.manifest.dublinCore.title = title
            container.manifest.dublinCore.type = type
            container.manifest.dublinCore.version = version
            container.manifest.checking.checkingLevel = checkingLevel

            container.manifest.dublinCore.contributor = contributors.map { it.text }.toMutableList()
            container.manifest.dublinCore.relation = relations.map { it.text }.toMutableList()
            container.manifest.dublinCore.source = sources.map { it.toSource() }.toMutableList()
            container.manifest.checking.checkingEntity = checkingEntities.map { it.text }.toList()
            container.manifest.projects = projects.map { it.toProject() }.toList()

            MainUseCase(RCManagerImpl()).saveResourceContainer(container)
                    .observeOn(JavaFxScheduler.platform())
                    .subscribeOn(Schedulers.io())
                    /*.onErrorReturn {
                        println(it.message)
                    }*/
                    .subscribe {
                        println("Saved")
                    }

            // TODO show success popup
        }
        else {
            // TODO show error popup
        }
    }

    fun validateData(): Boolean {
        if (conformsto.isNullOrEmpty()
                || creator.isNullOrEmpty()
                || description.isNullOrEmpty()
                || format.isNullOrEmpty()
                || identifier.isNullOrEmpty()
                || issued == null
                || modified == null
                || languageDirection.isNullOrEmpty()
                || languageIdentifier.isNullOrEmpty()
                || languageTitle.isNullOrEmpty()
                || publisher.isNullOrEmpty()
                || rights.isNullOrEmpty()
                || subject.isNullOrEmpty()
                || title.isNullOrEmpty()
                || type.isNullOrEmpty()
                || version.isNullOrEmpty()
                || checkingLevel.isNullOrEmpty()
                || contributors.isEmpty()
                || relations.isEmpty()
                || sources.isEmpty()
                || checkingEntities.isEmpty()
                || projects.isEmpty()) {
            return false
        }

        return true
    }

    fun clearData() {
        conformsto = ""
        creator = ""
        description = ""
        format = ""
        identifier = ""
        issued = LocalDate.now()
        modified = LocalDate.now()
        languageDirection = ""
        languageIdentifier = ""
        languageTitle = ""
        publisher = ""
        rights = ""
        subject = ""
        title = ""
        type = ""
        version = ""
        checkingLevel = ""
        contributors.clear()
        relations.clear()
        sources.clear()
        checkingEntities.clear()
        projects.clear()
    }
}