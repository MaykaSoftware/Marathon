package com.sportunity.marathon.presentation.events

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sportunity.marathon.R
import com.sportunity.marathon.data.mapper.toDate
import com.sportunity.marathon.domain.model.Marathon
import com.sportunity.marathon.ui.theme.MarathonTheme

@Composable
fun MarathonItem(
    modifier: Modifier = Modifier,
    marathonEvent: Marathon,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.clickable { onClick() }.height(256.dp).padding(horizontal = 24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Image(
                modifier = modifier
                    .weight(1f)
                    .fillMaxWidth(),
                painter = painterResource(id = marathonEvent.imageUrl),
                contentDescription = stringResource(id = R.string.app_name),
                contentScale = ContentScale.Crop,

                )

            Column(
                modifier = modifier.weight(1f).padding(12.dp)
            ) {
                Text(
                    text = marathonEvent.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = marathonEvent.city ?: "",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = marathonEvent.dateFrom?.toDate() ?: "",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

    }
}

@Preview
@Composable
fun MarathonEventPreview() {
    MarathonTheme {
        MarathonItem(
            marathonEvent = Marathon(
                1,
                "Nijmegen",
                "Nederland",
                "07-11-2023",
                0,
                "Wij gaan rennen!",

//                "7 heuvelenloop",
//                "Gelderland"
            ),
            onClick = {}
        )
    }
}