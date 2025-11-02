package example.project.presentation.mainScreen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import example.project.presentation.DogCard
import kotlinx.coroutines.launch

class MainScreen {

    @Composable
    fun Content() {
        val viewModel = hiltViewModel<MainScreenViewModel>()
        val scope = rememberCoroutineScope()
        val pagerState = rememberPagerState { viewModel.dogs.size }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xFFFAF9F9),
        ) { padding ->
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentPadding = PaddingValues(0.dp),
            ) {
                val dog = viewModel.dogs.getOrNull(it)

                if (dog != null) {
                    DogCard(dog) {
                        viewModel.loadDog {
                            scope.launch {
                                pagerState.animateScrollToPage(viewModel.dogs.lastIndex)
                            }
                        }
                    }
                }
            }
        }
    }
}