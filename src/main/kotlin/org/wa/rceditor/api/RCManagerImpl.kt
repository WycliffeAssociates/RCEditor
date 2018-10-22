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
        chooseDirectory("Open Resource Container").apply {
            if (this != null) {
                return try {
                    Maybe.fromCallable {
                        ResourceContainer.load(this)
                    }
                } catch (e: Exception) {
                    println(e.message)
                    Maybe.empty()
                }
            }
        }
        return Maybe.empty()
    }

    override fun create(): Maybe<ResourceContainer> {
        chooseDirectory("Create Resource Container").apply {
            if (this != null) {
                return  try {
                    Maybe.fromCallable {
                        ResourceContainer.create(this) {
                            this.manifest = Manifest(DublinCore(), listOf(), Checking())
                        }
                    }
                } catch (e: java.lang.Exception) {
                    println(e.message)
                    Maybe.empty()
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