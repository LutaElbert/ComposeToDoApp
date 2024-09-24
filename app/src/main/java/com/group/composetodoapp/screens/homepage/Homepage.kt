package com.group.composetodoapp.screens.homepage

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ViewList
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ViewModule
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.composetodoapp.R
import com.group.composetodoapp.component.AnimatedContentComponent
import com.group.composetodoapp.component.NavigationDrawerComponent
import com.group.composetodoapp.component.ScaffoldComponent
import com.group.composetodoapp.component.ToDoAnimationState
import com.group.composetodoapp.ui.theme.ComposeToDoAppTheme
import kotlinx.coroutines.launch

@Composable
fun Homepage() {
    ScaffoldComponent { modifier ->
        MainContent(modifier)
    }
}

@Composable
fun MainContent(modifier: Modifier = Modifier) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        NavigationDrawerComponent(drawerState) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                SearchBox(
                    onToggleClicked = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                ToggleListIcon()

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {
                    items(10) {
                        RowItem()
                    }
                }
            }
        }
    }
}

@Composable
private fun RowItem() {
    var isOverflowing by rememberSaveable { mutableStateOf(false) }
    var isShowMoreClicked by rememberSaveable { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(8.dp))

    Row(
        modifier = Modifier
            .wrapContentSize()
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .border(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Image(
                    modifier = Modifier
                        .size(56.dp)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.onSurface,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clip(RoundedCornerShape(16.dp)),
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentScale = ContentScale.Crop,
                    contentDescription = "PriorityColor"
                )

                RowItemContent(
                    modifier = Modifier.weight(1f),
                    isShowMoreClicked = isShowMoreClicked
                ) { overflow ->
                    isOverflowing = overflow
                }

                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More",
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }

            AnimatedContentComponent(state = isShowMoreClicked, animationState = ToDoAnimationState.ContainerAnimation) {
                if (isOverflowing || isShowMoreClicked) {
                    Icon(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                isShowMoreClicked = !isShowMoreClicked
                            },
                        imageVector = if (isShowMoreClicked) {
                            Icons.Default.ArrowDropUp
                        } else {
                            Icons.Default.ArrowDropDown
                        },
                        contentDescription = "ArrowDropDown",
                        tint = MaterialTheme.colorScheme.tertiary
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun RowItemContent(
    modifier: Modifier = Modifier,
    isShowMoreClicked: Boolean,
    isOverflowing: (Boolean) -> Unit
) {

    val maxLines = if (isShowMoreClicked) {
        Int.MAX_VALUE
    } else {
        2
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Hugas plato",
            style = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = " kay pahugason man kay pahugason man kay pahugason man kay pahugason mankay pahugason man ".trim(),
            style = TextStyle(
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            ),
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { textLayoutResult ->
                isOverflowing.invoke(textLayoutResult.hasVisualOverflow)
            }
        )
    }
}

@Composable
private fun ToggleListIcon() {
    var listIconToggleState by rememberSaveable {
        mutableStateOf(false)
    }

    val imageVectorState = if (listIconToggleState) {
        Icons.Filled.ViewModule
    } else {
        Icons.AutoMirrored.Filled.ViewList
    }

    Row(
        modifier = Modifier
            .padding(end = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedContentComponent(
            state = imageVectorState,
            animationState = ToDoAnimationState.IconAnimation
        ) { targetState ->
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        listIconToggleState = !listIconToggleState
                    },
                imageVector = targetState,
                contentDescription = "List",
                tint = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun SearchBox(modifier: Modifier = Modifier, onToggleClicked: () -> Unit) {
    var fieldValue by rememberSaveable { mutableStateOf("") }
    val isValidField by rememberSaveable(fieldValue) { mutableStateOf(fieldValue.isNotEmpty()) }
    val addATaskField = if (isValidField) {
        colorResource(id = R.color.black500)
    } else {
        colorResource(id = R.color.beige500)
    }

    Spacer(modifier = Modifier.height(16.dp))

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onToggleClicked.invoke()
                    },
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",
                tint = MaterialTheme.colorScheme.tertiary
            )

            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp),
                value = fieldValue,
                onValueChange = {
                    fieldValue = it
                },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = addATaskField
                ),
                decorationBox = { innerTextField ->
                    if (fieldValue.isEmpty()) {
                        Text(
                            text = stringResource(id = R.string.search_placeholder),
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        )
                    }
                    innerTextField()
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomepagePreview() {
    ComposeToDoAppTheme(
        darkTheme = true
    ) {
        Homepage()
    }
}