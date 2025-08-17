package example.project.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

class MainScreen {
    @Composable
    fun Content(){

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            items(
                viewmodel.dogList,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                ) {
                    AsyncImage(
                        model = it.message,
                        contentDescription = "Песик",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable(onClick = viewmodel::loadTwoDogs),
                        contentScale = ContentScale.Fit,
                    )
                    Text(
                        text = it.breed,
                        modifier = Modifier.align(Alignment.TopStart),
                        color = Color(0xFFFF2222)

                    )
                }

            }
            item {
                Button(onClick = viewmodel::loadTwoDogs) {
                    Text(
                        text = "Загрузить"
                    )

                }
            }
        }
    }
}