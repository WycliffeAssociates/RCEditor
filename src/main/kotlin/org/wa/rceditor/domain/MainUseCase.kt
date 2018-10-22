package org.wa.rceditor.domain

import io.reactivex.Completable
import io.reactivex.Maybe
import org.wycliffeassociates.resourcecontainer.ResourceContainer

class MainUseCase(private var RCManager: RCManager) {
    fun openResourceContainer(): Maybe<ResourceContainer> {
        return RCManager.open()
    }

    fun createResourceContainer(): Maybe<ResourceContainer> {
        return RCManager.create()
    }

    fun saveResourceContainer(container: ResourceContainer): Completable {
        return RCManager.save(container)
    }
}