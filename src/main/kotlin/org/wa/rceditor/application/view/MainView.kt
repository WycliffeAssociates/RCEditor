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
    private lateinit var projListView: ListView<ProjectItem>

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
                    tabPane = tabpane {
                        visibleWhen { viewModel.directoryLoadedProperty }
                        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
                        tab("Core") {
                            form {
                                fieldset("Dublin Core") {
                                    field("Conformsto") {
                                        textfield {
                                            bind(viewModel.conformstoProperty)
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
                                        }
                                    }
                                    field("Description") {
                                        textfield {
                                            bind(viewModel.descriptionProperty)
                                        }
                                    }
                                    field("Format") {
                                        textfield {
                                            bind(viewModel.formatProperty)
                                        }
                                    }
                                    field("Identifier") {
                                        textfield {
                                            bind(viewModel.identifierProperty)
                                        }
                                    }
                                    field("Issued") {
                                        datepicker {
                                            bind(viewModel.issuedProperty)
                                        }
                                    }
                                    field("Modified") {
                                        datepicker {
                                            bind(viewModel.modifiedProperty)
                                        }
                                    }
                                    field("Language") {
                                        textfield {
                                            promptText = "Direction"
                                            bind(viewModel.languageDirectionProperty)
                                        }
                                        textfield {
                                            promptText = "Identifier"
                                            bind(viewModel.languageIdentifierProperty)
                                        }
                                        textfield {
                                            promptText = "Title"
                                            bind(viewModel.languageTitleProperty)
                                        }
                                    }
                                    field("Publisher") {
                                        textfield {
                                            bind(viewModel.publisherProperty)
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
                                        }
                                    }
                                    field("Title") {
                                        textfield {
                                            bind(viewModel.titleProperty)
                                        }
                                    }
                                    field("Type") {
                                        textfield {
                                            bind(viewModel.typeProperty)
                                        }
                                    }
                                    field("Version") {
                                        textfield {
                                            bind(viewModel.versionProperty)
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
                                        }
                                    }
                                }
                            }
                        }

                        tab("Projects") {
                            projListView = listview(viewModel.projectsProperty.value) {
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
                            find<ProjectFragment>(mapOf(ProjectFragment::listView to projListView)).openModal()
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

