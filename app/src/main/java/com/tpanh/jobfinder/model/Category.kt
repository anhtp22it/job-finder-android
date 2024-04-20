package com.tpanh.jobfinder.model

import com.tpanh.jobfinder.R

data class Category(
    var id: String,
    var category: String,
    var image: Int,
    var subCategories: Set<String>
)

object CategoryData {
    val categories = listOf(
        Category(
            "1",
            "Design",
            R.drawable.design,
            setOf("Graphic Design", "UI/UX Design", "Illustration", "3D Design")
        ),
        Category(
            "2",
            "Finance",
            R.drawable.ic_finance,
            setOf("Accounting", "Financial Planning", "Financial Analysis", "Bookkeeping")
        ),
        Category(
            "3",
            "Education",
            R.drawable.ic_education,
            setOf("Teaching", "Curriculum Development", "Instructional Design", "E-Learning")
        ),
        Category(
            "Restaurant",
            "Business",
            R.drawable.ic_restaurant,
            setOf("Business Development", "Business Analysis", "Business Planning", "Business Strategy")
        ),
        Category(
            "5",
            "Health",
            R.drawable.ic_health,
            setOf("Nursing", "Medical Assistant", "Healthcare Management", "Healthcare Administration")
        ),
        Category(
            "6",
            "Programmer",
            R.drawable.ic_programmer,
            setOf("Software Development", "Web Development", "Mobile Development", "QA Testing")
        ),
    )
}