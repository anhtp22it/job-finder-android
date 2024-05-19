package com.tpanh.jobfinder.navigation

enum class JobFinderScreen(val route: String) {
    OnBoarding("OnBoarding"),
    Login("Login"),
    SignUp("SignUp"),
    ForgotPassword("ForgotPassword"),
    ChangePassword("ChangePassword"),
    Success("Success"),
    Verify("Verify"),
    Home("Home"),
    SearchJob("SearchJob"),
    JobDescription("JobDescription"),
    Filter("Filter"),
    AddEducation("AddEducation"),
    AddSkill("AddSkill"),
    AddResume("AddResume"),
    AddWorkExperience("AddWorkExperience"),
    UploadCv("UploadCv"),
    ViewProfile("ViewProfile"),
    EditProfile("EditProfile"),
    AboutMe("AboutMe"),
    Language("Language"),
    AddLanguage("AddLanguage"),
    Setting("Setting"),
    PostJob("PostJob"),
    SaveJob("SaveJob"),
    MyApplications("MyApplications"),
    MyApplication("MyApplication"),
    Specialization("Specialization"),
    MyJobs("MyJobs"),
    ApplyJobDesc("ApplyJobDesc");

    companion object {
        fun fromRoute(route: String): JobFinderScreen? {
            return values().find { it.route in route }
        }
    }
}