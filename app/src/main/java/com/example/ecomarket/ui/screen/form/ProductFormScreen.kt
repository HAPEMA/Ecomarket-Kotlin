package com.example.ecomarket.ui.screen.form
// Android / Core
import android.graphics.Bitmap
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
// agrega este import:
import android.content.Context

// Activity Result APIs
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

// Compose base
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

// Compose UI
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

// ✅ ICONOS (requieren la dependencia material-icons-extended)
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon

// ✅ TECLADO / IME (para KeyboardOptions)
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

// ✅ Imagen (Coil para carga de imágenes)
import coil.compose.AsyncImage

// ✅ Proyecto
import com.example.ecomarket.data.local.LocalProductStore
import com.example.ecomarket.data.model.Producto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(
    onSaved: (Producto) -> Unit,
    onCancel: () -> Unit = {}
) {
    val ctx = LocalContext.current

    var nombre by rememberSaveable { mutableStateOf("") }
    var categoryId by rememberSaveable { mutableStateOf("") }
    var precio by rememberSaveable { mutableStateOf("") }

    // Guardamos una URI local (file:// o content://) de la imagen seleccionada
    var imagenLocal by rememberSaveable { mutableStateOf<String?>(null) }
    var tag by rememberSaveable { mutableStateOf("") }

    // Validaciones
    val nombreOk = nombre.trim().length >= 3
    val catOk = categoryId.trim().isNotEmpty()
    val precioNum = precio.filter { it.isDigit() }
    val precioOk = precioNum.isNotEmpty() && precioNum.toLong() > 0L
    val formOk = nombreOk && catOk && precioOk

    // Cámara (TakePicturePreview => Bitmap)
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bmp: Bitmap? ->
        bmp?.let {
            val uri = saveBitmapToCache(ctx, it)
            imagenLocal = uri.toString() // "file://..."
        }
    }

    // Galería (GetContent => Uri)
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imagenLocal = uri?.toString()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Nuevo producto") }) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Preview de imagen si existe
            imagenLocal?.let { src ->
                AsyncImage(
                    model = Uri.parse(src),
                    contentDescription = "Foto del producto",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }

            // Acciones de imagen
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { cameraLauncher.launch(null) }) {
                    Icon(Icons.Default.CameraAlt, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("Cámara")
                }
                OutlinedButton(onClick = { galleryLauncher.launch("image/*") }) {
                    Icon(Icons.Default.Image, contentDescription = null)
                    Spacer(Modifier.width(6.dp))
                    Text("Galería")
                }
            }

            OutlinedTextField(
                value = nombre, onValueChange = { nombre = it },
                label = { Text("Nombre") },
                isError = !nombreOk && nombre.isNotEmpty(),
                supportingText = {
                    if (!nombreOk && nombre.isNotEmpty()) Text("Mínimo 3 caracteres")
                },
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = categoryId, onValueChange = { categoryId = it },
                label = { Text("CategoryId (ej: cuadradas)") },
                isError = !catOk && categoryId.isNotEmpty(),
                supportingText = {
                    if (!catOk && categoryId.isNotEmpty()) Text("Obligatorio")
                },
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = precio, onValueChange = { precio = it.filter { ch -> ch.isDigit() } },
                label = { Text("Precio CLP") },
                isError = !precioOk && precio.isNotEmpty(),
                supportingText = {
                    if (!precioOk && precio.isNotEmpty()) Text("Ingrese un número > 0")
                },
                singleLine = true,

            )

            OutlinedTextField(
                value = tag, onValueChange = { tag = it },
                label = { Text("Tag visible (opcional)") },
                singleLine = true, modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedButton(onClick = onCancel) { Text("Cancelar") }
                Button(
                    onClick = {
                        val nuevo = Producto(
                            id = System.currentTimeMillis().toString(),
                            nombre = nombre.trim(),
                            categoryId = categoryId.trim(),
                            precioCLP = precioNum.toLong(),
                            imagenUrl = imagenLocal, // file:// o content://
                            tag = tag.trim().ifEmpty { null }
                        )
                        LocalProductStore.add(ctx, nuevo)
                        onSaved(nuevo)
                    },
                    enabled = formOk
                ) { Text("Guardar") }
            }
        }
    }
}

/** Guarda un Bitmap en cache y devuelve su Uri file:// */
private fun saveBitmapToCache(ctx: Context, bmp: Bitmap): Uri {
    val dir = File(ctx.cacheDir, "images").apply { mkdirs() }
    val file = File(dir, "p_${System.currentTimeMillis()}.jpg")
    FileOutputStream(file).use { out ->
        bmp.compress(Bitmap.CompressFormat.JPEG, 90, out)
        out.flush()
    }
    return Uri.fromFile(file)
}

