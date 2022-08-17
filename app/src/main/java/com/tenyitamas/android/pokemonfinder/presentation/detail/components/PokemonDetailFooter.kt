package com.tenyitamas.android.pokemonfinder.presentation.detail.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.tenyitamas.android.pokemonfinder.R
import com.tenyitamas.android.pokemonfinder.presentation.list.PokemonListEvent
import com.tenyitamas.android.pokemonfinder.ui.theme.LocalSpacing

@Composable
fun PokemonDetailFooter(
    weight: Int,
    height: Int,
    modifier: Modifier = Modifier
) {

    val spacing = LocalSpacing.current

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            enabled = false,
            onClick = {},
            modifier = Modifier
                .padding(spacing.spaceMedium)
                .clip(RoundedCornerShape(50)),
            shape = RoundedCornerShape(0.5f),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = Color.White.copy(alpha = 0.95f)
            )
        ) {
            Text(
                text = stringResource(id = R.string.weight).plus(": $weight"),
                color = Color.White.copy(alpha = 0.95f)
            )
        }

        Spacer(modifier = Modifier.width(spacing.spaceMedium))

        Button(
            enabled = false,
            onClick = {},
            modifier = Modifier
                .padding(spacing.spaceMedium)
                .clip(RoundedCornerShape(50)),
            shape = RoundedCornerShape(0.5f),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = Color.White.copy(alpha = 0.95f)
            )
        ) {
            Text(
                text = stringResource(id = R.string.height).plus(": $height"),
                color = Color.White.copy(alpha = 0.95f)
            )
        }
    }

}