package org.wa.rceditor.domain

import io.reactivex.Completable
import io.reactivex.Maybe
import org.wycliffeassociates.resourcecontainer.ResourceContainer
import org.wycliffeassociates.resourcecontainer.entity.Checking
import org.wycliffeassociates.resourcecontainer.entity.DublinCore
import org.wycliffeassociates.resourcecontainer.entity.Manifest
import java.io.File

fun ResourceContainer.Companion.open(file: File): Maybe<ResourceContainer> {
    if (file != null) {
        return Maybe.fromCallable {
            ResourceContainer.load(file)
        }
    }
    return Maybe.empty()
}

fun ResourceContainer.Companion.create(file: File): Maybe<ResourceContainer> {
    if (file != null) {
        return Maybe.fromCallable {
            ResourceContainer.create(file) {
                this.manifest = Manifest(DublinCore(), listOf(), Checking())
            }
        }
    }
    return Maybe.empty()
}

fun ResourceContainer.Companion.save(container: ResourceContainer): Completable {
    return Completable.fromCallable {
        container.write()
    }
}