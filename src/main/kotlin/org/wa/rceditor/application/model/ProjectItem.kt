package org.wa.rceditor.application.model

import javafx.beans.property.ObjectProperty
import javafx.beans.property.SimpleIntegerProperty
import org.wycliffeassociates.resourcecontainer.entity.Project
import tornadofx.*
import java.util.*

class ProjectItem(
        title: String,
        versification: String,
        identifier: String,
        sort: Int,
        path: String,
        category: String) {

    val id: UUID = UUID.randomUUID()

    private var title: String by property(title)
    val titleProperty = getProperty(ProjectItem::title)

    private var versification: String by property(versification)
    val versificationProperty = getProperty(ProjectItem::versification)

    private var identifier: String by property(identifier)
    val identifierProperty = getProperty(ProjectItem::identifier)

    val sortProperty = SimpleIntegerProperty(sort)
    var sort by sortProperty

    /*private var sort: Int by property(sort)
    val sortProperty = getProperty(ProjectItem::sort)*/

    private var path: String by property(path)
    val pathProperty = getProperty(ProjectItem::path)

    private var category: String by property(category)
    val categoryProperty = getProperty(ProjectItem::category)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as ProjectItem

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun toProject() = Project(
        title,
        versification,
        identifier,
        sort,
        path,
        listOf(category)
    )
}

class ProjectItemModel(property: ObjectProperty<ProjectItem>) :
        ItemViewModel<ProjectItem>(itemProperty = property) {
    val title = bind(autocommit = true) { item?.titleProperty }
    val versification = bind(autocommit = true) { item?.versificationProperty }
    val identifier = bind(autocommit = true) { item?.identifierProperty }
    val sort = bind(autocommit = true) { item?.sortProperty }
    val path = bind(autocommit = true) { item?.pathProperty }
    val category = bind(autocommit = true) { item?.categoryProperty }
}