package com.cbellmont.ejemplodescargainternet


data class iTunes(
    var wrapperType: String,
    var kind: String,
    var artistId: Int,
    var collectionId: Int,
    var trackId: Int,
    var artistName: String,
    var collectionName: String,
    var trackName: String,
    var collectionCensoredName: String,
    var trackCensoredName: String,
    var artistViewUrl: String,
    var collectionViewUrl: String,
    var trackViewUrl: String,
    var previewUrl: String,
    var artworkUrl30: String,
    var artworkUrl60: String,
    var artworkUrl100: String,
    var collectionPrice: Float,
    var trackPrice: Float,
    var releaseDate: String,
    var collectionExplicitness: String,
    var trackExplicitness: String,
    var discCount: Int,
    var discNumber: Int,
    var trackCount: Int,
    var trackNumber: Int,
    var trackTimeMillis: Long,
    var pais: String,
    var moneda: String,
    var primaryGenreName: String,
    var isStreamable: Boolean){

    override fun toString(): String {
        return "\nCanción: $trackName\nAlbum:$collectionName\nArtista:$artistName\n================="
    }
}

