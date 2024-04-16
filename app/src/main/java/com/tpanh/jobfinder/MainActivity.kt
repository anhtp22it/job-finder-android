package com.tpanh.jobfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.tpanh.jobfinder.navigation.JobFinderNavigation
import com.tpanh.jobfinder.sreens.AddEducation
import com.tpanh.jobfinder.sreens.AddResume
import com.tpanh.jobfinder.sreens.AddSkill
import com.tpanh.jobfinder.sreens.EditProfile
import com.tpanh.jobfinder.sreens.LanguageScreen
import com.tpanh.jobfinder.ui.theme.JobFinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobFinderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EditProfile(navigateToSetting = {  })
//                    JobFinderNavigation()
                }
            }
        }
    }
}