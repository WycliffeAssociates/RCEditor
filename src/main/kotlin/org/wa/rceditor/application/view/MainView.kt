package org.wa.rceditor.application.app

import com.jfoenix.controls.*
import javafx.beans.binding.Bindings
import javafx.geometry.Pos
import javafx.scene.control.TabPane
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.model.ProjectItem
import org.wa.rceditor.application.view.fragments.*
import org.wa.rceditor.application.viewmodel.MainViewModel
import tornadofx.*


class MainView : View("Resource Container Editor") {
    override val root = BorderPane()

    private val viewModel by inject<MainViewModel>()

    private lateinit var tabPane: JFXTabPane
    private lateinit var projectListView: JFXListView<ProjectItem>
    lateinit var stackPane: StackPane

    init {
        with(root) {
            top {
                vbox {
                    hbox {
                        vboxConstraints {
                            marginTop = 10.0
                        }
                        spacing = 15.0
                        this += JFXButton("", Styles.newFileIcon()).apply {
                            tooltip("Create new resource container")
                            addClass(Styles.menuButton)
                            action {
                                viewModel.handleNewDocumentSelected()
                            }
                        }
                        this += JFXButton("", Styles.openFileIcon()).apply {
                            tooltip("Open resource container")
                            addClass(Styles.menuButton)
                            action {
                                viewModel.handleOpenDirectorySelected()
                            }
                        }
                        this += JFXButton("", Styles.saveFileIcon()).apply {
                            tooltip("Save resource container")
                            addClass(Styles.menuButton)
                            action {
                                viewModel.handleSaveDocumentSelected()
                            }
                        }
                        this += JFXButton("", Styles.quitIcon()).apply {
                            tooltip("Quit")
                            addClass(Styles.menuButton)
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
                    stackPane = this
                    this += JFXTabPane().apply {
                        tabPane = this
                        tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
                        visibleWhen { viewModel.directoryLoadedProperty }

                        tab("Core") {
                            scrollpane {
                                isFitToWidth = true
                                prefHeight = 700.0
                                form {
                                    stackpaneConstraints {
                                        marginTop = 20.0
                                    }
                                    fieldset("Dublin Core") {
                                        spacing = 10.0
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.conformstoProperty)
                                                required()

                                                promptText = "Conformsto"
                                                isLabelFloat = true
                                            }
                                        }
                                        field("Contributor") {
                                            this += JFXButton("Edit").apply {
                                                action {
                                                    JFXDialog().apply {
                                                        dialogContainer = stackPane
                                                        content = JFXDialogLayout().apply {
                                                            setHeading(label("Contributor"))
                                                            setBody(StringListDialog("Contributor name",
                                                                    viewModel.contributorsProperty))
                                                            setActions(JFXButton("Close").apply {
                                                                action { close() }
                                                            })
                                                        }
                                                        transitionType = JFXDialog.DialogTransition.LEFT
                                                        isOverlayClose = false
                                                    }.show()

                                                    /*val sf = find<ContributorFragment>()
                                                    sf.listView.items = viewModel.contributorsProperty
                                                    sf.openModal()*/
                                                }
                                            }
                                        }
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.creatorProperty)
                                                required()

                                                promptText = "Creator"
                                                isLabelFloat = true
                                            }
                                        }
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.descriptionProperty)
                                                required()

                                                promptText = "Description"
                                                isLabelFloat = true
                                            }
                                        }
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.formatProperty)
                                                required()

                                                promptText = "Format"
                                                isLabelFloat = true
                                            }
                                        }
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.identifierProperty)
                                                required()

                                                promptText = "Identifier"
                                                isLabelFloat = true
                                            }
                                        }
                                        field("Issued") {
                                            this += JFXDatePicker().apply {
                                                defaultColor = c("#94008B")
                                                bind(viewModel.issuedProperty)
                                                required()
                                            }
                                        }
                                        field("Modified") {
                                            this += JFXDatePicker().apply {
                                                defaultColor = c("#94008B")
                                                bind(viewModel.modifiedProperty)
                                                required()
                                            }
                                        }
                                        field("Language") {
                                            this += JFXTextField().apply {
                                                promptText = "Direction"
                                                isLabelFloat = true

                                                bind(viewModel.languageDirectionProperty)
                                                required()
                                            }
                                            this += JFXTextField().apply {
                                                promptText = "Identifier"
                                                isLabelFloat = true

                                                bind(viewModel.languageIdentifierProperty)
                                                required()
                                            }
                                            this += JFXTextField().apply {
                                                promptText = "Title"
                                                isLabelFloat = true

                                                bind(viewModel.languageTitleProperty)
                                                required()
                                            }
                                        }
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.publisherProperty)
                                                required()

                                                promptText = "Publisher"
                                                isLabelFloat = true
                                            }
                                        }
                                        field("Relation") {
                                            this += JFXButton("Edit").apply {
                                                action {
                                                    JFXDialog().apply {
                                                        dialogContainer = stackPane
                                                        content = JFXDialogLayout().apply {
                                                            setHeading(label("Relation"))
                                                            setBody(StringListDialog("Relation",
                                                                    viewModel.relationsProperty))
                                                            setActions(JFXButton("Close").apply {
                                                                action { close() }
                                                            })
                                                        }
                                                        transitionType = JFXDialog.DialogTransition.LEFT
                                                        isOverlayClose = false
                                                    }.show()

                                                    /*val sf = find<RelationFragment>()
                                                    sf.listView.items = viewModel.relationsProperty
                                                    sf.openModal()*/
                                                }
                                            }
                                        }
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.rightsProperty)
                                                required()

                                                promptText = "Rights"
                                                isLabelFloat = true
                                            }
                                        }
                                        field("Source") {
                                            this += JFXButton("Edit").apply {
                                                action {
                                                    JFXDialog().apply {
                                                        dialogContainer = stackPane
                                                        content = JFXDialogLayout().apply {
                                                            setHeading(label("Source"))
                                                            setBody(SourceListDialog(viewModel.sourcesProperty))
                                                            setActions(JFXButton("Close").apply {
                                                                action { close() }
                                                            })
                                                        }
                                                        transitionType = JFXDialog.DialogTransition.LEFT
                                                        isOverlayClose = false
                                                    }.show()

                                                    /*val sf = find<SourceFragment>()
                                                    sf.listView.items = viewModel.sourcesProperty
                                                    sf.openModal()*/
                                                }
                                            }
                                        }
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.subjectProperty)
                                                required()

                                                promptText = "Subject"
                                                isLabelFloat = true
                                            }
                                        }
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.titleProperty)
                                                required()

                                                promptText = "Title"
                                                isLabelFloat = true
                                            }
                                        }
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.typeProperty)
                                                required()

                                                promptText = "Type"
                                                isLabelFloat = true
                                            }
                                        }
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.versionProperty)
                                                required()

                                                promptText = "Version"
                                                isLabelFloat = true
                                            }
                                        }
                                    }

                                    fieldset("Checking") {
                                        field("Checking Entity") {
                                            this += JFXButton("Edit").apply {
                                                action {
                                                    JFXDialog().apply {
                                                        dialogContainer = stackPane
                                                        content = JFXDialogLayout().apply {
                                                            setHeading(label("Checking Entity"))
                                                            setBody(StringListDialog("Checking Entity",
                                                                    viewModel.checkingEntitiesProperty))
                                                            setActions(JFXButton("Close").apply {
                                                                action { close() }
                                                            })
                                                        }
                                                        transitionType = JFXDialog.DialogTransition.LEFT
                                                        isOverlayClose = false
                                                    }.show()

                                                    /*val sf = find<CheckingEntityFragment>()
                                                    sf.listView.items = viewModel.checkingEntitiesProperty
                                                    sf.openModal()*/
                                                }
                                            }
                                        }
                                        field {
                                            this += JFXTextField().apply {
                                                bind(viewModel.checkingLevelProperty)
                                                required()

                                                promptText = "Checking Level"
                                                isLabelFloat = true
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        tab("Projects") {
                            stackpane {
                                this += JFXListView<ProjectItem>().apply {
                                    stackpaneConstraints {
                                        alignment = Pos.TOP_CENTER
                                        marginTop = 5.0
                                    }

                                    projectListView = this
                                    items = viewModel.projectsProperty

                                    paddingBottom = 5.0
                                    isEditable = true
                                    cellFragment(ProjectCell::class)
                                }

                                this += JFXButton("", Styles.addIcon()).apply {
                                    stackpaneConstraints {
                                        alignment = Pos.BOTTOM_RIGHT
                                        marginBottom = 15.0
                                        marginRight = 20.0
                                    }
                                    addClass(Styles.addButton)
                                    action {
                                        val dialog = JFXDialog()
                                        val projectDialog = NewProjectDialog(projectListView, dialog)
                                        dialog.apply {
                                            dialogContainer = stackPane
                                            content = JFXDialogLayout().apply {
                                                setHeading(label("Project"))
                                                setBody(projectDialog)
                                                setActions(JFXButton("Add").apply {
                                                    action {
                                                        projectDialog.commitAll()
                                                    }
                                                }, JFXButton("Cancel").apply {
                                                    action {
                                                        close()
                                                    }
                                                })
                                            }
                                            transitionType = JFXDialog.DialogTransition.LEFT
                                            isOverlayClose = false
                                        }.show()

                                        //find<ProjectFragment>(mapOf(ProjectFragment::listView to projectListView)).openModal()
                                    }
                                    visibleWhen {
                                        Bindings.equal(1, tabPane.selectionModel.selectedIndexProperty())
                                    }
                                    tooltip("Add project")
                                }
                            }
                        }
                    }

                    progressbar {
                        visibleWhen { viewModel.processingProperty }
                    }
                }
            }
        }
    }
}