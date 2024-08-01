package com.my.fakestoredemo.common.uiComponents


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.my.fakestoredemo.ui.theme.FakeStoreDemoTheme


@Composable
fun AppScrollableTabRow(
    titles: List<String>,
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    if(titles.isNotEmpty()){
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(4.dp)
                        .background(color = Color.Blue)
                )

            },
            divider = {}
        ) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    text = { Text(title) }
                )
            }
        }
    }



}

@Preview(showBackground = true)
@Composable
fun PreviewCounterChip() {
    FakeStoreDemoTheme {
        AppScrollableTabRow(
            titles = listOf("Tab1", "Tab2", "Tab3"),
            selectedTabIndex = 0,
            onTabSelected = { }
        )
    }
}