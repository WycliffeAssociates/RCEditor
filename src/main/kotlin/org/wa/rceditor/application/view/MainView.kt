package org.wa.rceditor.application.app

import javafx.beans.binding.Bindings
import javafx.geometry.Pos
import javafx.scene.control.ListView
import javafx.scene.control.TabPane
import javafx.scene.layout.BorderPane
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.ProjectItem
import org.wa.rceditor.application.view.fragments.*
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*


class MainView : View("Resource Container Editor") {
    override val root = BorderPane()

    private val viewModel by inject<MainViewModel>()

    private lateinit var tabPane: TabPane
    private lateinit var projectListView: ListView<ProjectItem>

    init {
        with(root) {
            top {
                vbox {
                    hbox {
                        vboxConstraints {
                            marginTop = 10.0
                        }
                        spacing = 5.0
                        button(graphic = Styles.newFileIcon()) {
                            tooltip("Create new resource container")
                            action {
                                viewModel.handleNewDocumentSelected()
                            }
                        }
                        button(graphic = Styles.openFileIcon()) {
                            tooltip("Open resource container")
                            action {
                                viewModel.handleOpenDirectorySelected()
                            }
                        }
                        button(graphic = Styles.saveFileIcon()) {
                            tooltip("Save resource container")
                            action {
                                viewModel.handleSaveDocumentSelected()
                            }
                        }
                        button(graphic = Styles.quitIcon()) {
                            tooltip("Quit")
                            action {
                                viewModel.handleAppQuit()
                            }
                        }
                    }

                    separator {
                        vboxConstraints {
                            marginTop = 5.0
                        }
                    }
                }
            }

            left {
                borderpaneConstraints {
                    paddingHorizontal = 20.0
                }
                stackpane {
                    tabpane {
                        tabPane = this
                        visibleWhen { viewModel.directoryLoadedProperty }
                        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
                        tab("Core") {
                            form {
                                fieldset("Dublin Core") {
                                    field("Conformsto") {
                                        textfield {
                                            bind(viewModel.conformstoProperty)
                                            required()
                                        }
                                    }
                                    field("Contributor") {
                                        button("Edit") {
                                            action {
                                                val sf = find<ContributorFragment>()
                                                sf.listView.items = viewModel.contributorsProperty
                                                sf.openModal()
                                            }
                                        }
                                    }
                                    field("Creator") {
                                        textfield {
                                            bind(viewModel.creatorProperty)
                                            required()
                                        }
                                    }
                                    field("Description") {
                                        textfield {
                                            bind(viewModel.descriptionProperty)
                                            required()
                                        }
                                    }
                                    field("Format") {
                                        textfield {
                                            bind(viewModel.formatProperty)
                                            required()
                                        }
                                    }
                                    field("Identifier") {
                                        textfield {
                                            bind(viewModel.identifierProperty)
                                            required()
                                        }
                                    }
                                    field("Issued") {
                                        datepicker {
                                            bind(viewModel.issuedProperty)
                                            required()
                                        }
                                    }
                                    field("Modified") {
                                        datepicker {
                                            bind(viewModel.modifiedProperty)
                                            required()
                                        }
                                    }
                                    field("Language") {
                                        textfield {
                                            promptText = "Direction"
                                            bind(viewModel.languageDirectionProperty)
                                            required()
                                        }
                                        textfield {
                                            promptText = "Identifier"
                                            bind(viewModel.languageIdentifierProperty)
                                            required()
                                        }
                                        textfield {
                                            promptText = "Title"
                                            bind(viewModel.languageTitleProperty)
                                            required()
                                        }
                                    }
                                    field("Publisher") {
                                        textfield {
                                            bind(viewModel.publisherProperty)
                                            required()
                                        }
                                    }
                                    field("Relation") {
                                        button("Edit") {
                                            action {
                                                val sf = find<RelationFragment>()
                                                sf.listView.items = viewModel.relationsProperty
                                                sf.openModal()
                                            }
                                        }
                                    }
                                    field("Rights") {
                                        textfield {
                                            bind(viewModel.rightsProperty)
                                            required()
                                        }
                                    }
                                    field("Source") {
                                        button("Edit") {
                                            action {
                                                val sf = find<SourceFragment>()
                                                sf.listView.items = viewModel.sourcesProperty
                                                sf.openModal()
                                            }
                                        }
                                    }
                                    field("Subject") {
                                        textfield {
                                            bind(viewModel.subjectProperty)
                                            required()
                                        }
                                    }
                                    field("Title") {
                                        textfield {
                                            bind(viewModel.titleProperty)
                                            required()
                                        }
                                    }
                                    field("Type") {
                                        textfield {
                                            bind(viewModel.typeProperty)
                                            required()
                                        }
                                    }
                                    field("Version") {
                                        textfield {
                                            bind(viewModel.versionProperty)
                                            required()
                                        }
                                    }
                                }

                                fieldset("Checking") {
                                    field("Checking Entity") {
                                        button("Edit") {
                                            action {
                                                val sf = find<CheckingEntityFragment>()
                                                sf.listView.items = viewModel.checkingEntitiesProperty
                                                sf.openModal()
                                            }
                                        }
                                    }
                                    field("Checking Level") {
                                        textfield {
                                            bind(viewModel.checkingLevelProperty)
                                            required()
                                        }
                                    }
                                }
                            }
                        }

                        tab("Projects") {
                            listview(viewModel.projectsProperty.value) {
                                projectListView = this
                                paddingBottom = 5.0
                                isEditable = true
                                cellFragment(ProjectCell::class)
                            }
                        }
                    }

                    progressbar {
                        visibleWhen { viewModel.processingProperty }
                    }

                    button("Add Project") {
                        stackpaneConstraints {
                            alignment = Pos.TOP_RIGHT
                            marginTop = 5.0
                            marginRight = 5.0
                        }
                        action {
                            find<ProjectFragment>(mapOf(ProjectFragment::listView to projectListView)).openModal()
                        }
                        visibleWhen {
                            Bindings.equal(1, tabPane.selectionModel.selectedIndexProperty())
                        }
                    }
                }
            }
        }
    }
}

