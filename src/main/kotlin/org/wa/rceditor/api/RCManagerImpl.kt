package org.wa.rceditor.api

import io.reactivex.Completable
import io.reactivex.Maybe
import org.wa.rceditor.domain.RCManager
import org.wycliffeassociates.resourcecontainer.ResourceContainer
import org.wycliffeassociates.resourcecontainer.entity.Checking
import org.wycliffeassociates.resourcecontainer.entity.DublinCore
import org.wycliffeassociates.resourcecontainer.entity.Manifest
import tornadofx.*

class RCManagerImpl: RCManager {
    override fun open(): Maybe<ResourceContainer> {
        val file = chooseDirectory("Open Resource Container")
        if (file != null) {
            return Maybe.fromCallable {
                ResourceContainer.load(file)
            }
        }
        return Maybe.empty()
    }

    override fun create(): Maybe<ResourceContainer> {
        val file = chooseDirectory("Create Resource Container")
        if (file != null) {
            return Maybe.fromCallable {
                ResourceContainer.create(file) {
                    this.manifest = Manifest(DublinCore(), listOf(), Checking())
                }
            }
        }
        return Maybe.empty()
    }

    override fun save(container: ResourceContainer): Completable {
        return Completable.fromCallable {
            container.write()
        }
    }
}