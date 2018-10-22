package org.wa.rceditor.domain

import io.reactivex.Completable
import io.reactivex.Maybe
import org.wycliffeassociates.resourcecontainer.ResourceContainer

interface RCManager {
    fun open(): Maybe<ResourceContainer>
    fun create(): Maybe<ResourceContainer>
    fun save(container: ResourceContainer): Completable
}