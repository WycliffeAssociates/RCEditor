package org.wa.rceditor.application.app

import javafx.beans.binding.Bindings
import javafx.geometry.Pos
import javafx.scene.control.TabPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Priority
import org.wa.rceditor.application.view.fragments.*
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*


class MainView : View("Resource Container Editor") {
    override val root = BorderPane()

    private val viewModel by inject<MainViewModel>()

    private lateinit var tabPane: TabPane

    init {
        with(root) {
            //setMinSize(1024.0, 768.0)

            top {
                menubar {
                    menu("File") {
                        item("New") {
                            action {
                                viewModel.handleNewDocumentSelected()
                            }
                        }
                        item("Open") {
                            action {
                                viewModel.handleOpenDocumentSelected()
                            }
                        }
                        item("Save") {
                            action {
                                viewModel.handleSaveDocumentSelected()
                            }
                        }
                        item("Quit") {
                            action {
                                viewModel.handleAppQuit()
                            }
                        }
                    }
                }
            }

            left {
                borderpaneConstraints {
                    paddingHorizontal = 20.0
                }

                stackpane {
                    hbox {
                        stackpaneConstraints {
                            margin = insets(200.0)
                        }

                        removeWhen { viewModel.directoryLoadedProperty }
                        spacing = 10.0
                        button("New") {
                            action {
                                viewModel.handleNewDocumentSelected()
                            }
                        }
                        button("Open") {
                            action {
                                viewModel.handleOpenDocumentSelected()
                            }
                        }
                    }

                    anchorpane {
                        visibleWhen { viewModel.directoryLoadedProperty }
                        tabPane = tabpane {
                            tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
                            tab("Core") {
                                form {
                                    fieldset("Dublin Core") {
                                        prefWidth = 500.0
                                        field("Conformsto") {
                                            textfield {
                                                bind(viewModel.conformstoProperty)
                                            }
                                        }
                                        field("Contributor") {
                                            button("Edit") {
                                                action {
                                                    find<ContributorFragment>().openModal()
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
                                                    find<RelationFragment>().openModal()
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
                                                    find<SourceFragment>().openModal()
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
                                                    find<CheckingEntityFragment>().openModal()
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
                                listview(viewModel.projects()) {
                                    paddingBottom = 30.0
                                    isEditable = true
                                    cellFragment(ProjectItemFragment::class)
                                }
                            }
                        }

                        button("Add Project") {
                            action {
                                find<ProjectFragment>().openModal()
                            }
                            anchorpaneConstraints {
                                rightAnchor = 2.0
                                topAnchor = 2.0
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
}

