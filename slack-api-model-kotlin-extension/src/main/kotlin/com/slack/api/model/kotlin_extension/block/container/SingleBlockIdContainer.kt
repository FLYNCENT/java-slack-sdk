package com.slack.api.model.kotlin_extension.block.container

import com.slack.api.model.kotlin_extension.block.dsl.BlockIdDsl

class SingleBlockIdContainer : BlockIdDsl {
    var underlying: String? = null

    override fun blockId(id: String) {
        underlying = id
    }
}