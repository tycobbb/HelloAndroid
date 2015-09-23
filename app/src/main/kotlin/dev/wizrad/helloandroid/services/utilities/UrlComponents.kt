package dev.wizrad.helloandroid.services.utilities

public class UrlComponents<T>(vararg val components: T) {

    public var separator = ","

    override fun toString(): String {
        return this.components.joinToString(this.separator)
    }

}
