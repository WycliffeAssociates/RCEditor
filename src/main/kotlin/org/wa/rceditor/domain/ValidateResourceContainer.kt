package org.wa.rceditor.domain

import org.wycliffeassociates.resourcecontainer.ResourceContainer
import org.wycliffeassociates.resourcecontainer.entity.Checking
import org.wycliffeassociates.resourcecontainer.entity.Language
import org.wycliffeassociates.resourcecontainer.entity.Project
import org.wycliffeassociates.resourcecontainer.entity.Source

class ValidateResourceContainer {

    fun validateConformsTo(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateCreator(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateDescription(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateFormat(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateIdentifier(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateIssued(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateModified(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateLanguage(value: Language): Boolean {
        return value.title.isNotEmpty()
            .and(value.identifier.isNotEmpty())
            .and(value.direction.isNotEmpty())
    }

    fun validatePublisher(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateRights(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateSubject(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateTitle(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateType(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateVersion(value: String): Boolean {
        return value.isNotEmpty()
    }

    fun validateChecking(value: Checking): Boolean {
        return value.checkingLevel.isNotEmpty()
            .and(value.checkingEntity.isNotEmpty()
                .and(value.checkingEntity.filter {
                    it.isNotEmpty()
                }.size == value.checkingEntity.size)
            )
    }

    fun validateContributor(value: List<String>): Boolean {
        return value.isNotEmpty()
            .and(value.filter {
                it.isNotEmpty()
            }.size == value.size)
    }

    fun validateRelation(value: List<String>): Boolean {
        return value.isNotEmpty()
            .and(value.filter {
                it.isNotEmpty()
            }.size == value.size)
    }

    fun validateSource(value: List<Source>): Boolean {
        return value.isNotEmpty()
            .and(value.size == value.size)
            .and(value.filter {
                it.identifier.isNotEmpty()
                    .and(
                        it.language.isNotEmpty()
                            .and(it.version.isNotEmpty())
                    )
            }.size == value.size)
    }

    fun validateProjects(value: List<Project>): Boolean {
        return value.isNotEmpty()
            .and(value.size == value.size)
            .and(value.filter {
                it.title.isNotEmpty()
                    .and(it.identifier.isNotEmpty()
                        .and(it.path.isNotEmpty()
                            .and(it.sort in 1..66)
                            .and(it.versification.isNotEmpty()
                                .and(it.categories.isNotEmpty()
                                    .and(it.categories.filter {
                                        it.isNotEmpty()
                                    }.size == it.categories.size)
                                )
                            )
                        )
                    )
            }.size == value.size)
    }

    fun validate(rc: ResourceContainer): Boolean {
        return validateConformsTo(rc.manifest.dublinCore.conformsTo)
            .and(validateCreator(rc.manifest.dublinCore.creator))
            .and(validateDescription(rc.manifest.dublinCore.description))
            .and(validateFormat(rc.manifest.dublinCore.format))
            .and(validateIdentifier(rc.manifest.dublinCore.identifier))
            .and(validateIssued(rc.manifest.dublinCore.issued))
            .and(validateModified(rc.manifest.dublinCore.modified))
            .and(
                validateLanguage(rc.manifest.dublinCore.language)
                    .and(validatePublisher(rc.manifest.dublinCore.publisher))
                    .and(validateRights(rc.manifest.dublinCore.rights))
                    .and(validateSubject(rc.manifest.dublinCore.subject))
                    .and(validateTitle(rc.manifest.dublinCore.title))
                    .and(validateType(rc.manifest.dublinCore.type))
                    .and(validateVersion(rc.manifest.dublinCore.version))
                    .and(validateChecking(rc.manifest.checking))
                    .and(validateContributor(rc.manifest.dublinCore.contributor))
                    .and(validateRelation(rc.manifest.dublinCore.relation))
                    .and(validateSource(rc.manifest.dublinCore.source))
                    .and(validateProjects(rc.manifest.projects))
            )
    }
}
