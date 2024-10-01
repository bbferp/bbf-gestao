package org.bff.erp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform