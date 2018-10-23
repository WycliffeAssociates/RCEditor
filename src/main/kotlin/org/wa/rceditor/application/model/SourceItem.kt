package org.wa.rceditor.application.model

import javafx.beans.property.ObjectProperty
import org.wycliffeassociates.resourcecontainer.entity.Source
import tornadofx.*

class SourceItem(identifier: String, language: String, version: String) {
    private var identifier: String by property(identifier)
    val identifierProperty = getProperty(SourceItem::identifier)

    private var language: String by property(language)
    val languageProperty = getProperty(SourceItem::language)

    private var version: String by property(version)
    val versionProperty = getProperty(SourceItem::version)

    fun toSource() = Source(identifier, language, version)
}

class SourceItemModel(property: ObjectProperty<SourceItem>) :
        ItemViewModel<SourceItem>(itemProperty = property) {
    val identifier = bind(autocommit = true) { item?.identifierProperty }
    val language = bind(autocommit = true) { item?.languageProperty }
    val version = bind(autocommit = true) { item?.versionProperty }
}