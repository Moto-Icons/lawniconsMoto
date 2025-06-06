package app.lawnchair.lawnicons.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.dp
import app.lawnchair.lawnicons.ui.components.core.SimpleListRow
import app.lawnchair.lawnicons.ui.theme.LawniconsTheme
import app.lawnchair.lawnicons.ui.util.PreviewLawnicons
import app.lawnchair.lawnicons.ui.util.visitUrl
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ContributorRow(
    name: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
    profileUrl: String? = null,
    socialUrl: String? = null,
    description: String? = null,
    divider: Boolean = true,
    background: Boolean = false,
    first: Boolean = false,
    last: Boolean = false,
) {
    val context = LocalContext.current
    val url = profileUrl ?: socialUrl
    val onClick = if (url != null) {
        { context.visitUrl(url) }
    } else {
        null
    }

    SimpleListRow(
        modifier = modifier,
        background = background,
        first = first,
        last = last,
        divider = divider,
        label = name,
        description = description,
        onClick = onClick,
        startIcon = {
            if (LocalInspectionMode.current) {
                Icon(Icons.Rounded.Star, contentDescription = null)
            } else {
                AsyncImage(
                    contentDescription = name,
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(data = photoUrl)
                        .crossfade(enable = true)
                        .build(),
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                )
            }
        },
    )
}

@PreviewLawnicons
@Composable
private fun ContributorRowPreview() {
    LawniconsTheme {
        ContributorRow(
            name = "User",
            photoUrl = "https://lawnchair.app/images/lawnchair.png",
            description = "The Lawnchair Logo",
        )
    }
}
