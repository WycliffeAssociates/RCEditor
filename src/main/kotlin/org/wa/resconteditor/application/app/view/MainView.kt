package org.wa.rceditor.application.app

import javafx.scene.control.TabPane
import javafx.scene.layout.BorderPane
import org.wa.resconteditor.application.app.viewmodel.MainViewModel
import tornadofx.*

class MainView : View("Resource Container Editor") {
    override val root = BorderPane()

    private val viewModel = MainViewModel()

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

                tabpane {
                    tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE

                    tab("Core") {
                        form {
                            fieldset("Dublin Core") {
                                field("Conformsto") {
                                    textfield()
                                }
                                field("Contributor") {
                                    textfield()
                                }
                                field("Creator") {
                                    textfield()
                                }
                                field("Description") {
                                    textfield()
                                }
                                field("Format") {
                                    textfield()
                                }
                                field("Identifier") {
                                    textfield()
                                }
                                field("Issued") {
                                    datepicker()
                                }
                                field("Modified") {
                                    datepicker()
                                }
                                field("Language") {
                                    textfield()
                                }
                                field("Publisher") {
                                    textfield()
                                }
                                field("Relation") {
                                    textfield()
                                }
                                field("Rights") {
                                    textfield()
                                }
                                field("Source") {
                                    textfield()
                                }
                                field("Subject") {
                                    textfield()
                                }
                                field("Title") {
                                    textfield()
                                }
                                field("Type") {
                                    textfield()
                                }
                                field("Version") {
                                    textfield()
                                }
                            }

                            fieldset("Checking") {
                                field("Checking Entity") {
                                    textfield()
                                }
                                field("Checking Level") {
                                    textfield()
                                }
                            }
                        }
                    }

                    tab("Projects") {
                        form {
                            fieldset("Genesis") {
                                field("Title") {
                                    textfield()
                                }
                                field("Versification") {
                                    textfield()
                                }
                                field("Identifier") {
                                    textfield()
                                }
                                field("Sort") {
                                    textfield()
                                }
                                field("Path") {
                                    textfield()
                                }
                                field("Categories") {
                                    textfield()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}