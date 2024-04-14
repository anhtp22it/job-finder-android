package com.tpanh.jobfinder.model

data class Skill(
    val id: Int,
    val name: String,
)

object Skills {
    val skills = listOf(
        Skill(1, "Leadership"),
        Skill(2, "Teamwork"),
        Skill(3, "Visioner"),
        Skill(3, "Target oriented"),
        Skill(4, "Consistent"),
        Skill(5, "Good communication skills"),
        Skill(6, "English")
    )

    val skillSearch = listOf(
        Skill(1, "Leadership"),
        Skill(2, "Teamwork"),
        Skill(3, "Visioner"),
        Skill(3, "Target oriented"),
        Skill(4, "Consistent"),
        Skill(5, "Good communication skills"),
        Skill(6, "English"),
        Skill(7, "Java"),
        Skill(8, "Kotlin"),
        Skill(9, "Swift"),
        Skill(10, "Objective-C"),
        Skill(11, "C++"),
        Skill(12, "C#"),
        Skill(13, "Python"),
        Skill(14, "JavaScript"),
        Skill(15, "HTML"),
        Skill(16, "CSS"),
        Skill(17, "SQL"),
        Skill(18, "PHP"),
        Skill(19, "Ruby"),
        Skill(20, "Go"),
        Skill(21, "Rust"),
        Skill(22, "Scala"),
        Skill(23, "TypeScript"),
        Skill(24, "Dart"),
        Skill(25, "Shell"),
        Skill(26, "PowerShell"),
        Skill(27, "Bash"),
        Skill(28, "Perl"),
        Skill(29, "Lua"),
        Skill(30, "R"),
        Skill(31, "Matlab"),
    )
}
