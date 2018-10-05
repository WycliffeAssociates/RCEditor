package org.wa.rceditor.application.model

import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.wycliffeassociates.resourcecontainer.entity.Language
import org.wycliffeassociates.resourcecontainer.entity.Project
import org.wycliffeassociates.resourcecontainer.entity.Source
import tornadofx.*
import java.time.LocalDate
import kotlin.system.exitProcess

class MainModel {

    // --------------- Properties ------------------ //

    private var conformsto: String by property()
    val conformstoProperty = getProperty(MainModel::conformsto)

    private var contributor: ObservableList<String> by property()
    val contributorProperty = getProperty(MainModel::contributor)

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

    private var language: Language by property()
    val languageProperty = getProperty(MainModel::language)

    private var publisher: String by property()
    val publisherProperty = getProperty(MainModel::publisher)

    private var relation: MutableList<String> by property()
    val relationProperty = getProperty(MainModel::relation)

    private var rights: String by property()
    val rightsProperty = getProperty(MainModel::rights)

    private var source: List<Source> by property()
    val sourceProperty = getProperty(MainModel::source)

    private var subject: String by property()
    val subjectProperty = getProperty(MainModel::subject)

    private var title: String by property()
    val titleProperty = getProperty(MainModel::title)

    private var type: String by property()
    val typeProperty = getProperty(MainModel::type)

    private var version: String by property()
    val versionProperty = getProperty(MainModel::version)

    private var checkingEntity: MutableList<String> by property()
    val checkingEntityProperty = getProperty(MainModel::checkingEntity)

    private var checkingLevel: String by property()
    val checkingLevelProperty = getProperty(MainModel::checkingLevel)

    private var projects: List<Project> by property()
    val projectsProperty = getProperty(MainModel::projects)

    init {
        contributor = FXCollections.observableArrayList()
//        contributor.add("efsefsefsefsef")
//        contributor.add("sklefjlskjefls")
//        contributor.add("seulsieflslisef")
    }

    // -------------- Functions --------------- //

    fun onNewDocumentSelected() {
        println("New document created")
    }

    fun onOpenDocumentSelected() {
        println("Document Opened")
    }

    fun onSaveDocumentSelected() {
        println(conformsto)

        println("Document Saved")
    }

    fun onAppQuitSelected() {
        exitProcess(-1)
    }

    fun onEditContributorsClick() {
        println("open contributors list editor dialog")
    }

    fun onEditProjectClick(project: Project) {
        println(project)
    }

    fun onAddProjectClick() {
        println("Open Add Project editor dialog")
    }
}