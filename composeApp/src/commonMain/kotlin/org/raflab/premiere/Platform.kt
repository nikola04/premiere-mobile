package org.raflab.premiere

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform