package org.wa.rceditor.application.viewmodel

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDialog
import com.jfoenix.controls.JFXDialogLayout
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler
import io.reactivex.schedulers.Schedulers
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.scene.layout.StackPane
import org.wa.rceditor.application.app.MainView
import org.wa.rceditor.application.model.ProjectItem
import org.wa.rceditor.application.model.SourceItem
import org.wa.rceditor.application.view.fragments.DialogFragment
import org.wa.rceditor.application.view.fragments.MessageDialog
import org.wa.rceditor.application.view.fragments.StringListDialog
import org.wa.rceditor.domain.ValidateResourceContainer
import org.wa.rceditor.domain.create
import org.wa.rceditor.domain.open
import org.wa.rceditor.domain.save
import org.wycliffeassociates.resourcecontainer.ResourceContainer
import tornadofx.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.system.exitProcess

class MainViewModel: ViewModel() {
    private var conformsto: String by property()
    val conformstoProperty = getProperty(MainViewModel::conformsto)

    private var creator: String by property()
    val creatorProperty = getProperty(MainViewModel::creator)

    private var description: String by property()
    val descriptionProperty = getProperty(MainViewModel::description)

    private var format: String by property()
    val formatProperty = getProperty(MainViewModel::format)

    private var identifier: String by property()
    val identifierProperty = getProperty(MainViewModel::identifier)

    private var issued: LocalDate by property()
    val issuedProperty = getProperty(MainViewModel::issued)

    private var modified: LocalDate by property()
    val modifiedProperty = getProperty(MainViewModel::modified)

    private var languageDirection: String by property()
    val languageDirectionProperty = getProperty(MainViewModel::languageDirection)

    private var languageIdentifier: String by property()
    val languageIdentifierProperty = getProperty(MainViewModel::languageIdentifier)

    private var languageTitle: String by property()
    val languageTitleProperty = getProperty(MainViewModel::languageTitle)

    private var publisher: String by property()
    val publisherProperty = getProperty(MainViewModel::publisher)

    private var rights: String by property()
    val rightsProperty = getProperty(MainViewModel::rights)

    private var subject: String by property()
    val subjectProperty = getProperty(MainViewModel::subject)

    private var title: String by property()
    val titleProperty = getProperty(MainViewModel::title)

    private var type: String by property()
    val typeProperty = getProperty(MainViewModel::type)

    private var version: String by property()
    val versionProperty = getProperty(MainViewModel::version)

    private var checkingLevel: String by property()
    val checkingLevelProperty = getProperty(MainViewModel::checkingLevel)

    private val contributors = SortedFilteredList<String>()
    val contributorsProperty = SimpleListProperty(contributors)

    private val relations = SortedFilteredList<String>()
    val relationsProperty = SimpleListProperty(relations)

    private val sources = SortedFilteredList<SourceItem>()
    val sourcesProperty = SimpleListProperty(sources)

    private val checkingEntities = SortedFilteredList<String>()
    val checkingEntitiesProperty = SimpleListProperty(checkingEntities)

    private val projects = SortedFilteredList<ProjectItem>()
    val projectsProperty = SimpleListProperty(projects)

    private var directoryLoaded: Boolean by property()
    val directoryLoadedProperty = getProperty(MainViewModel::directoryLoaded)

    private var processing: Boolean by property()
    val processingProperty = getProperty(MainViewModel::processing)

    lateinit var container: ResourceContainer

    // ------------- Handlers ---------------- //

    fun handleNewDocumentSelected() {
        chooseDirectory("Create Resource Container")?.let {
            processing = true
            directoryLoaded = false

            ResourceContainer.create(it)
                    .observeOn(JavaFxScheduler.platform())
                    .subscribeOn(Schedulers.io())
                    .doOnError {
                        showPopup(MessageDialog.TYPE.ERROR, it.toString())
                    }
                    .onErrorComplete()
                    .doFinally { processing = false }
                    .subscribe {
                        container = it
                        clearViewData()
                        clearDecorators()
                        directoryLoaded = true
                    }
        }
    }

    fun handleOpenDirectorySelected() {
        chooseDirectory("Open Resource Container")?.let {
            processing = true
            directoryLoaded = false
            
            ResourceContainer.open(it)
                    .observeOn(JavaFxScheduler.platform())
                    .subscribeOn(Schedulers.io())
                    .doOnError {
                        showPopup(MessageDialog.TYPE.ERROR, it.toString())
                    }
                    .onErrorComplete()
                    .doFinally { processing = false }
                    .subscribe {
                        container = it
                        clearViewData()
                        directoryLoaded = true
                        setViewData()
                        clearDecorators()
                    }
        }
    }

    fun handleSaveDocumentSelected() {
        try {
            validate()
            saveResourceContainer()
        } catch (e: UninitializedPropertyAccessException) {
            showPopup(MessageDialog.TYPE.ERROR,
                    "First create a new resource container or open an existing one")
        }
    }

    fun handleAppQuit() {
        exitProcess(0)
    }


    // ------------ Functions -------------- //

    fun setViewData() {
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

        contributors.addAll(container.manifest.dublinCore.contributor)
        relations.addAll(container.manifest.dublinCore.relation)
        sources.addAll(container.manifest.dublinCore.source.map { SourceItem(it.identifier, it.language, it.version) })
        checkingEntities.addAll(container.manifest.checking.checkingEntity)

        projects.addAll(container.manifest.projects.map {
            ProjectItem(it.title, it.versification, it.identifier, it.sort, it.path,
                    if(it.categories.isNotEmpty()) it.categories.first() else "Unknown")
        })
    }

    fun saveResourceContainer() {
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

        container.manifest.dublinCore.contributor = contributors.toMutableList()
        container.manifest.dublinCore.relation = relations.toMutableList()
        container.manifest.dublinCore.source = sources.map { it.toSource() }.toMutableList()
        container.manifest.checking.checkingEntity = checkingEntities.toList()
        container.manifest.projects = projects.map { it.toProject() }.toList()

        if (ValidateResourceContainer().validate(container)) {
            processing = true
            ResourceContainer.save(container)
                    .observeOn(JavaFxScheduler.platform())
                    .subscribeOn(Schedulers.io())
                    .doOnError {
                        showPopup(MessageDialog.TYPE.ERROR, it.toString())
                    }
                    .onErrorComplete()
                    .doFinally { processing = false }
                    .subscribe {
                        showPopup(MessageDialog.TYPE.SUCCESS,
                                "The Resource Container has been successfully saved!")
                    }
        } else {
            showPopup(MessageDialog.TYPE.ERROR,
                    "The Resource Container has not been saved! Make sure the data filled in properly.")
        }
    }

    fun clearViewData() {
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

    /*fun showPopup(type: DialogFragment.TYPE?, message: String?) {
        find<DialogFragment>(
                mapOf(
                        DialogFragment::type to type,
                        DialogFragment::message to message
                )
        ).openModal()
    }*/

    fun showPopup(type: MessageDialog.TYPE, message: String) {
        val mainView = primaryStage.scene.findUIComponents().first() as MainView
        MessageDialog(type, message).show(mainView.stackPane)
    }
}