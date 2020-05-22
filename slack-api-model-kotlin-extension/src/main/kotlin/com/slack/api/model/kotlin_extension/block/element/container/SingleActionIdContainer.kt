package com.slack.api.model.kotlin_extension.block.element.container

import com.slack.api.model.kotlin_extension.block.element.dsl.ActionIdDsl

class SingleActionIdContainer : ActionIdDsl {
    var underlying: String? = null

    override fun actionId(id: String) {
        underlying = id
    }
}