package com.sportunity.marathon.presentation.events

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sportunity.marathon.data.local.entity.events.toDate
import com.sportunity.marathon.domain.model.Marathon
import com.sportunity.marathon.ui.theme.MarathonTheme

@Composable
fun MarathonItem(
    marathonEvent: Marathon,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
//            AsyncImage(
//                model = marathonEvent.imageUrl,
//                contentDescription = marathonEvent.description,
//                modifier = Modifier
//                    .weight(1f)
//                    .height(150.dp)
//            )
            
            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(3f)
            ){
                Text(
                    text = marathonEvent.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = marathonEvent.city?: "",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = marathonEvent.dateFrom?.toDate()?:"",
                    style = MaterialTheme.typography.titleSmall,
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
                "08-11-2023",
//                "Wij gaan rennen!",
//                "",
//                "7 heuvelenloop",
//                "Gelderland"
            )
        )
    }
}