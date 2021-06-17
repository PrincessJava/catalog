package ru.petproject.catalog.model.enum

import java.io.Serializable


//todo create bundle
enum class Message(val formatMessage: String) {
    CATEGORY_ALREADY_EXISTS("Category %s already exists"),
    CATEGORY_HAS_CHILDREN("Category %s has subcategories or contains products");

    companion object {
        fun getFormattedMessage(message: Message, name: String): String {
            return String.format(message.formatMessage, name)
        }
    }
}

//fun getMessageForLocale(messageKey: String?, locale: Locale?): String? {
//    return ResourceBundle.getBundle("messages", locale)
//        .getString(messageKey)
//}
