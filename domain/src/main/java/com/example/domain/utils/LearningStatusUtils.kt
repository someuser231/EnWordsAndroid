package com.example.domain.utils

import com.example.domain.models.LearningStatusModel

object LearningStatusUtils {
    val learningStatuses = listOf(
        LearningStatusModel(0, "unlearned"),
        LearningStatusModel(1, "first_meeting"),
        LearningStatusModel(2, "in_process"),
        LearningStatusModel(3, "learned"),
    )
}