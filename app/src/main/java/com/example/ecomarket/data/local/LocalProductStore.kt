package com.example.ecomarket.data.local

import android.content.Context
import com.example.ecomarket.data.model.Producto
import org.json.JSONArray
import org.json.JSONObject

object LocalProductStore {
    private const val PREFS = "eco_local_store"
    private const val KEY_USER_PRODUCTS = "user_products"

    fun load(context: Context): MutableList<Producto> {
        val sp = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val raw = sp.getString(KEY_USER_PRODUCTS, "[]") ?: "[]"
        val arr = JSONArray(raw)
        val list = mutableListOf<Producto>()
        for (i in 0 until arr.length()) {
            val o = arr.getJSONObject(i)
            list += Producto(
                id = o.getString("id"),
                nombre = o.getString("nombre"),
                categoryId = o.getString("categoryId"),
                precioCLP = o.getLong("precioCLP"),
                imagenUrl = if (o.isNull("imagenUrl")) null else o.getString("imagenUrl"),
                tag = if (o.isNull("tag")) null else o.getString("tag")
            )
        }
        return list
    }

    fun add(context: Context, p: Producto): List<Producto> {
        val list = load(context)
        list.add(p)
        save(context, list)
        return list
    }

    private fun save(context: Context, list: List<Producto>) {
        val arr = JSONArray()
        list.forEach { p ->
            val o = JSONObject().apply {
                put("id", p.id)
                put("nombre", p.nombre)
                put("categoryId", p.categoryId)
                put("precioCLP", p.precioCLP)
                put("imagenUrl", p.imagenUrl)
                put("tag", p.tag)
            }
            arr.put(o)
        }
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_USER_PRODUCTS, arr.toString())
            .apply()
    }
}
