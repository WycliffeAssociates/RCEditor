package org.wa.rceditor.application.app

import com.jfoenix.controls.*
import javafx.beans.binding.Bindings
import javafx.collections.FXCollections
import javafx.scene.layout.BorderPane
import javafx.scene.layout.StackPane
import javafx.stage.StageStyle
import org.wa.rceditor.application.Styles
import org.wa.rceditor.application.view.ContributorFragment
import org.wa.rceditor.application.view.ProjectCell
import org.wa.rceditor.application.view.ProjectFragment
import org.wa.rceditor.application.viewmodel.MainViewModel
import org.wycliffeassociates.resourcecontainer.entity.Project
import tornadofx.*


class MainView : View("Resource Container Editor") {
    override val root = BorderPane()

    private val viewModel = MainViewModel()

    private lateinit var tabPane: JFXTabPane

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

                anchorpane {
                    tabPane = JFXTabPane().apply {
                        tab("Core") {
                            form {
                                fieldset("Dublin Core") {
                                    prefWidth = 500.0
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Conformsto"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.conformstoProperty)
                                        }
                                    }
                                    field("Contributor") {
                                        label(viewModel.contributorProperty.value.joinToString(", ")) {
                                            maxWidth = 200.0
                                        }
                                        this += JFXButton("Edit").apply {
                                            action {
                                                //viewModel.handleEditContributorsClick()
                                                find<ContributorFragment>(mapOf(ContributorFragment::contributors to viewModel.contributorProperty)).openModal()
                                            }
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Creator"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.creatorProperty)
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Description"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.descriptionProperty)
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Format"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.formatProperty)
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Identifier"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.identifierProperty)
                                        }
                                    }
                                    field {
                                        this += JFXDatePicker().apply {
                                            promptText = "Issued"
                                            this.editor.addClass(Styles.prompt)
                                            bind(viewModel.issuedProperty)
                                        }
                                    }
                                    field {
                                        this += JFXDatePicker().apply {
                                            promptText = "Modified"
                                            this.editor.addClass(Styles.prompt)
                                            bind(viewModel.modifiedProperty)
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Language"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Publisher"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.publisherProperty)
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Relation"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Rights"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.rightsProperty)
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Source"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Subject"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.subjectProperty)
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Title"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.titleProperty)
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Type"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.typeProperty)
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Version"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.versionProperty)
                                        }
                                    }
                                }

                                fieldset("Checking") {
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Checking Entity"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                        }
                                    }
                                    field {
                                        this += JFXTextField().apply {
                                            promptText = "Checking Level"
                                            addClass(Styles.prompt)
                                            isLabelFloat = true
                                            bind(viewModel.checkingLevelProperty)
                                        }
                                    }
                                }
                            }
                        }

                        tab("Projects") {
                            val jfxlist = JFXListView<Project>()
                            val list = FXCollections.observableArrayList<Project>()
                            list.add(Project(
                                    title = "Genesis",
                                    sort = 1,
                                    categories = listOf(),
                                    identifier = "gen",
                                    path = "./gen.usfm",
                                    versification = "ufw"
                            ))
                            list.add(Project(
                                    title = "Exodus",
                                    sort = 2,
                                    categories = listOf(),
                                    identifier = "exo",
                                    path = "./exo.usfm",
                                    versification = "ufw"
                            ))
                            for (i in 3..66) {
                                list.add(Project(
                                        title = "Some Book",
                                        sort = i,
                                        categories = listOf(),
                                        identifier = "smb",
                                        path = "./smb.usfm",
                                        versification = "ufw"
                                ))
                            }
                            jfxlist.items = list
                            jfxlist.setCellFactory {
                                val cell = ProjectCell()
                                cell.button.action {
                                    //viewModel.handleEditProjectClick(cell.item)
                                    find<ProjectFragment>(mapOf(ProjectFragment::project to cell.item)).openModal()
                                }
                                cell
                            }

                            this += jfxlist
                        }
                    }

                    this += tabPane

                    this += JFXButton("Add Project").apply {
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

