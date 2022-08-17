package com.tenyitamas.android.pokemonfinder.presentation.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tenyitamas.android.pokemonfinder.R
import com.tenyitamas.android.pokemonfinder.domain.model.PokemonInfo
import com.tenyitamas.android.pokemonfinder.ui.theme.LocalSpacing

@Composable
fun PokemonDetailHeader(
    modifier: Modifier = Modifier,
    pokemon: PokemonInfo
) {

    val spacing = LocalSpacing.current

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .padding(spacing.spaceExtraSmall)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(15.dp)
            )
            .background(MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(spacing.spaceSmall))

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemon.getImageUrl())
                .crossfade(1000)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.ic_launcher_foreground)
                .build(),
            contentDescription = pokemon.name,
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(spacing.spaceSmall))

        Text(
            text = pokemon.name.uppercase(),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(
                horizontal = LocalSpacing.current.spaceExtraSmall
            )
        )

        Spacer(modifier = Modifier.height(spacing.spaceSmall))

    }
}