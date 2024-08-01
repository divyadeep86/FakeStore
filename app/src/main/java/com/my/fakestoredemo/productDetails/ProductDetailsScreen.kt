package com.my.fakestoredemo.productDetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.my.fakestoredemo.common.utils.TestTags

@Composable
fun ProductDetailsScreen(id:Int) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .testTag(TestTags.HomeScreen),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Product details screen: $id")
    }

}