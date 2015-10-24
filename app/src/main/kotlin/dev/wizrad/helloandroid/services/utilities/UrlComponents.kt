package dev.wizrad.helloandroid.services.utilities

class UrlComponents<T>(vararg val components: T) {

    public var separator = ","

    override fun toString(): String {
        return components.joinToString(separator)
    }

}
