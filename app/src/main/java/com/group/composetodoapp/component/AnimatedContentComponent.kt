package com.group.composetodoapp.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable

@Composable
fun <T>AnimatedContentComponent(
    state: T,
    animationState: ToDoAnimationState = ToDoAnimationState.None,
    content: @Composable (T) -> Unit) {
    AnimatedContent(
        targetState = state,
        transitionSpec = {
            when (animationState) {
                ToDoAnimationState.IconAnimation -> {
                    fadeIn(
                        animationSpec = spring(stiffness = Spring.StiffnessLow)
                    ) togetherWith fadeOut(
                        animationSpec = spring(stiffness = Spring.StiffnessLow)
                    )
                }
                ToDoAnimationState.ContainerAnimation -> {
                    fadeIn() togetherWith fadeOut()
                }
                ToDoAnimationState.None -> {
                    EnterTransition.None togetherWith ExitTransition.None
                }
            }
        },
        label = ""
    ) { targetState ->
        content(targetState)
    }
}

enum class ToDoAnimationState {
    IconAnimation,
    ContainerAnimation,
    None
}