package com.tpanh.jobfinder.model

enum class Workplace(val text: String, val description: String) {
    ON_SITE("On-site", "Employees come to work"),
    HYBRID("Hybrid", "Employees work directly on site or off site"),
    REMOTE("Remote", "Employees working off site"),
}