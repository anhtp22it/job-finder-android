package com.tpanh.jobfinder.model

data class Language(
    val name: Map<String, String>,
    val flag: String
)

object Languages {
    var myLanguages = listOf(
        Language(mapOf("common" to "English"), "🇬🇧"),
        Language(mapOf("common" to "Vietnamese"), "🇻🇳"),
        Language(mapOf("common" to "Japanese"), "🇯🇵"),
    )

    val allLanguage = listOf(
        Language(mapOf("common" to "English"), "🇬🇧"),
        Language(mapOf("common" to "Vietnamese"), "🇻🇳"),
        Language(mapOf("common" to "Japanese"), "🇯🇵"),
        Language(mapOf("common" to "Chinese"), "🇨🇳"),
        Language(mapOf("common" to "Korean"), "🇰🇷"),
        Language(mapOf("common" to "French"), "🇫🇷"),
        Language(mapOf("common" to "German"), "🇩🇪"),
        Language(mapOf("common" to "Spanish"), "🇪🇸"),
        Language(mapOf("common" to "Italian"), "🇮🇹"),
        Language(mapOf("common" to "Russian"), "🇷🇺"),
        Language(mapOf("common" to "Portuguese"), "🇵🇹"),
        Language(mapOf("common" to "Arabic"), "🇸🇦"),
        Language(mapOf("common" to "Hindi"), "🇮🇳"),
        Language(mapOf("common" to "Bengali"), "🇧🇩"),
        Language(mapOf("common" to "Urdu"), "🇵🇰"),
        Language(mapOf("common" to "Turkish"), "🇹🇷"),
        Language(mapOf("common" to "Thai"), "🇹🇭"),
        Language(mapOf("common" to "Indonesian"), "🇮🇩"),
        Language(mapOf("common" to "Malay"), "🇲🇾"),
    )
}
